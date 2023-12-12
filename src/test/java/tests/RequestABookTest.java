package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.RequestABookPage;
import pages.TestBase;
import utilities.ExcelUtility;
import utilities.MyListener;
import utilities.RetryAnalyzer;

@Listeners(MyListener.class)
public class RequestABookTest extends TestBase {
		HomePage hp;
		LoginPage lp;
		RequestABookPage rp;
		
		Logger logger = LogManager.getLogger(RequestABookTest.class);
		//public static int RowNum=1;
		@BeforeTest
		public void start_browser()
		{
			OpenBrowser();
			hp = new HomePage(driver);
			lp = new LoginPage(driver);
			rp = new RequestABookPage(driver);
		}

		
		@Test(dataProvider="LoginDetails", priority=1,retryAnalyzer=RetryAnalyzer.class)
		public void test_login(String email, String password) throws IOException, InterruptedException
		{
			logger.info("Testing Login");
			hp.clickLogin();
			lp.user_login(email,password);
			String uname=lp.get_uname();
			
			//Assert.assertEquals(uname, email);
			Assert.assertNotEquals(uname, "My Account");
			//lp.user_logout();
		}
		
		
		  @DataProvider(name="LoginDetails") public Object[][] datasupplier() throws
		  EncryptedDocumentException, IOException {
		  Object[] [] input = ExcelUtility.getTestData("Sheet2"); 
		  return input;
		  
		  }
		  
		  @Test(dataProvider="BookDetails",priority = 2)
			public void requestbook(String isbn, String bookTitle, String Author, String Phone) throws InterruptedException {
			  logger.info("Testing Request a Book");
				hp.click_RequestBook();
				Thread.sleep(1000);
				rp.enter_details(isbn, bookTitle, Author, Phone);
				rp.click_submit();
				String actual = "This book is already present";
				String expected = rp.get_message();
				Assert.assertNotEquals(actual,expected,"Book Already Present");
				
				String actual1 = "You have requested for this book before.";
				String expected1 = rp.get_message();
				Assert.assertNotEquals(actual1,expected1,"Book Already Requested");
			}
		  
		  @DataProvider(name="BookDetails") 
		  public Object[][] datasupplier1() throws EncryptedDocumentException, IOException {
		  Object[] [] input = ExcelUtility.getTestData("Sheet6"); 
		  return input;
		  
		  }
		@AfterTest
		public void close_browser() throws InterruptedException
		{
			lp.user_logout();
			driver.close();
		}
}

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

import pages.FictionBooksPage;
import pages.HomePage;
import pages.LoginPage;
import pages.TestBase;
import utilities.ExcelUtility;
import utilities.ExcelWrite;
import utilities.MyListener;
import utilities.RetryAnalyzer;

@Listeners(MyListener.class)
public class FictionBooksTest extends TestBase {

	HomePage hp;
	LoginPage lp;
	FictionBooksPage fp;
	Logger logger = LogManager.getLogger(FictionBooksTest.class);
	ExcelWrite w = new ExcelWrite();
	public static int RowNum=1;
	@BeforeTest
	public void start_browser()
	{
		OpenBrowser();
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
		fp = new FictionBooksPage(driver);
	}

	
	@Test(dataProvider="LoginDetails",retryAnalyzer=RetryAnalyzer.class, priority=1)
	public void test_login(String email, String password) throws IOException, InterruptedException
	{
		hp.clickLogin();
		lp.user_login(email,password);
		String uname=lp.get_uname();
		
		//Assert.assertEquals(uname, email);
		try {
			Assert.assertNotEquals(uname,"My Account");
			//w.writeExcel("C:\\Users\\USER\\Desktop\\TestData3.xlsx", "Sheet2", RowNum, 2, "PASS");
		
		}
		catch(Exception e)
		{
			//w.writeExcel("C:\\Users\\USER\\Desktop\\TestData3.xlsx", "Sheet2", RowNum, 2, "FAIL");
		}
		//RowNum=RowNum+1;
		//lp.user_logout();
	}
	
	
	  @DataProvider(name="LoginDetails") public Object[][] datasupplier() throws
	  EncryptedDocumentException, IOException {
	  
	  Object[] [] input = ExcelUtility.getTestData("Sheet2"); return input;
	  
	  }
	  
		@Test(priority=2)
		public void ClickFiction_CheckTitle() throws InterruptedException
		{
			logger.info("Testing FictionBooks Page Functionality");
			hp.click_fiction();
			String ExpectedTitle = "Fiction Books";
			String ActualTitle = driver.getTitle();
			Assert.assertEquals(ExpectedTitle, ActualTitle, "Title is Same");
			//lp.user_logout();
		}
	 

		@Test(priority=3)
		public void book_click() throws InterruptedException 
		{
			fp.click_book();
			//lp.user_logout();
		}
		
		@Test(priority=4)
		public void buynow_click() throws InterruptedException 
		{
			fp.Cart();
			lp.user_logout();
		}

	@AfterTest
	public void close_browser()
	{
		driver.close();
	}
	
}

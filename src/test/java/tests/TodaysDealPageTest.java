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
import pages.TestBase;
import pages.TodaysDealPage;
import utilities.ExcelUtility;
import utilities.MyListener;
import utilities.RetryAnalyzer;

@Listeners(MyListener.class)
public class TodaysDealPageTest extends TestBase {
	
	HomePage hp;
	LoginPage lp;
	TodaysDealPage tp;
	Logger logger = LogManager.getLogger(TodaysDealPageTest.class);
	
	@BeforeTest
	public void start_browser()
	{
		OpenBrowser();
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
		tp = new TodaysDealPage(driver);
	}

	
	@Test(dataProvider="LoginDetails",priority=1,retryAnalyzer=RetryAnalyzer.class)
	public void test_login(String email, String password) throws IOException, InterruptedException
	{
		hp.clickLogin();
		lp.user_login(email,password);
		String uname=lp.get_uname();
		
		//Assert.assertEquals(uname, email);
		Assert.assertNotEquals(uname, "My Account");
		//lp.user_logout();
	}
	
	@Test(priority=2,retryAnalyzer=RetryAnalyzer.class)
	public void dealstoday() throws InterruptedException, IOException
	{ 
		logger.info("Testing TodaysDeal Page Functionality");
		tp.todaysdeallog();
		//Assert.fail();

	}
	
	@Test(dataProvider="SearchData",priority=3)
	public void search(String search) throws InterruptedException
	{
		hp.searchitem(search);
		String actualsearch=search;
		String Expectedsearch="Beyond The Story: 10-Year Record of BTS";
		Assert.assertEquals(actualsearch, Expectedsearch, "Assert not same search item");
		hp.wishlistsearch();
		Thread.sleep(1500);
		lp.user_logout();
	}
	 @DataProvider(name="SearchData") 
	 public Object[][] datasupplier1() throws EncryptedDocumentException, IOException {
	  
	  Object[] [] input = ExcelUtility.getTestData("Sheet3"); return input;
	  
	  }
	
	  @DataProvider(name="LoginDetails") public Object[][] datasupplier() throws
	  EncryptedDocumentException, IOException {
	  
	  Object[] [] input = ExcelUtility.getTestData("Sheet2"); return input;
	  
	  }

	  @AfterTest
		public void close_browser()
		{
			driver.close();
		}

}

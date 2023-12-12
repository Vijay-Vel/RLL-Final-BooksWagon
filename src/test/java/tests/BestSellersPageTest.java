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

import pages.AwardWinnersPage;
import pages.BestSellersPage;
import pages.HomePage;
import pages.LoginPage;
import pages.TestBase;
import utilities.ExcelUtility;
import utilities.MyListener;
import utilities.RetryAnalyzer;

@Listeners(MyListener.class)
public class BestSellersPageTest extends TestBase {

	HomePage hp;
	LoginPage lp;
	AwardWinnersPage ap;
	BestSellersPage bp;
	Logger logger = LogManager.getLogger(BestSellersPageTest.class);
	@BeforeTest
	public void start_browser()
	{
		OpenBrowser();
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
		ap = new AwardWinnersPage(driver);
		bp = new BestSellersPage(driver);
	}

	
	@Test(dataProvider="LoginDetails", priority=1,retryAnalyzer=RetryAnalyzer.class)
	public void test_login(String email, String password) throws IOException, InterruptedException
	{
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
	  
	  @Test(priority=2)
	  public void click_BestSellers() {
		  logger.info("Testing BestSeller Page Functionality");
		  hp.click_BestSellers();
		  ap.sortaward();
	  }
	  
	  @Test(priority=3)
	  public void click_book() throws InterruptedException {
		  Thread.sleep(1000);
		  bp.click_book();
		  
	  }
	  
	  @AfterTest
		public void close_browser()
		{
			driver.close();
		}
		
}

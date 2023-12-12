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
import utilities.ExcelUtility;
import utilities.ExcelWrite;
import utilities.MyListener;


@Listeners(MyListener.class)
public class LoginPageTest extends TestBase {
	
	HomePage hp;
	LoginPage lp;
	ExcelWrite w = new ExcelWrite();
	static int RowNum=1;
	
	Logger logger = LogManager.getLogger(LoginPageTest.class);
	@BeforeTest
	public void start_browser()
	{
		OpenBrowser();
		hp = new HomePage(driver);
		lp = new LoginPage(driver);
	}

	@Test(priority=1)
	public void Test_LoginLink() throws InterruptedException {
		hp.clickLogin();
		String LoginLink=driver.getCurrentUrl();
		Assert.assertEquals(LoginLink, "https://www.bookswagon.com/login", "Login Link Same");
	}
	
	@Test(priority=2)
	public void Test_Logo() {
		lp.click_logo();
		String HomePageLink = driver.getCurrentUrl();
		Assert.assertEquals(HomePageLink, "https://www.bookswagon.com/", "Home Page Link Same");
	}
	@Test(dataProvider="LoginDetails", priority=3)
	public void test_login(String email, String password) throws IOException, InterruptedException
	{
		hp.clickLogin();
		lp.user_login(email,password);
		String uname=lp.get_uname();
		logger.info("Testing Login Functionality");
		//Assert.assertEquals(uname, email);
		try {
			Assert.assertNotEquals(uname,"My Account");
			//w.writeExcel("C:\\Users\\USER\\Desktop\\TestData3.xlsx", "Sheet1", RowNum, 2, "FAIL");
		
		}
		catch(Exception e)
		{
			logger.error("Login Failed");
			//w.writeExcel("C:\\Users\\USER\\Desktop\\TestData3.xlsx", "Sheet1", RowNum, 2, "PASS");
		}
		RowNum=RowNum+1;
		lp.user_logout();
	}
	
	
	  @DataProvider(name="LoginDetails") public Object[][] datasupplier() throws
	  EncryptedDocumentException, IOException {
	  
	  Object[] [] input = ExcelUtility.getTestData("Sheet1"); return input;
	  
	  }
	  

	@AfterTest
	public void close_browser()
	{
		driver.close();
	}
	
	

}

package pages;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import utilities.ScreenshotUtility;

public class TestBase {

	public static WebDriver driver;
	public static ExtentReports extentreports;
	public static ExtentTest extentTest;

	public static void  OpenBrowser()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.bookswagon.com/");
}
	public static void closebrowser()
	{
		driver.close();
	}
	
	@BeforeTest
	public void getnameMethod(ITestContext context)
	{
	extentTest=	extentreports.createTest(context.getName());
	}

	@BeforeSuite  
	public void InitalizeExtentReport() throws IOException
	{
	ExtentSparkReporter sparkreporter = new ExtentSparkReporter("MyReport4.html");
	File file = new File("src/test/resources/extent-report-config.json");
	sparkreporter.loadJSONConfig(file);
	//sparkreporter.config().setTheme(Theme.DARK);
	//sparkreporter.config().setReportName("Test Results for BooksWagon Application");
	//sparkreporter.config().setCss(".detail-body img+.title{background:#cf0000}");
	extentreports = new ExtentReports();
	extentreports.attachReporter(sparkreporter);
	// on the report display more information about OS, browser, java etc
	extentreports.setSystemInfo("OS", System.getProperty("os.name"));
	extentreports.setSystemInfo("JAVA", System.getProperty("java.version"));
	 extentreports.setSystemInfo("Host Name", System.getProperty("user.name"));
	 extentreports.setSystemInfo("Browser Name", "Chrome");
	 extentreports.setSystemInfo("Application URL", "https://www.bookswagon.com/");

	}

	@AfterSuite
	public void generateReports() throws IOException
	{
	extentreports.flush();
	Desktop.getDesktop().browse(new File("MyReport4.html").toURI());
	}


	@AfterMethod
	public void generateTestStatus(Method m,ITestResult result) throws IOException
	{
	if(result.getStatus() == ITestResult.FAILURE )
	{
        String screenshotPath = ScreenshotUtility.takeScreenshot();
        //extentTest.fail("Test Case failed check screenshot "+extentTest.addScreenCaptureFromPath(screenshotPath,"Failed Test Screenshots"));
        extentTest.fail(m.getName() + " is Failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath, "Failed Test Screenshot").build());
	}
	else if (result.getStatus() == ITestResult.SUCCESS) {
		extentTest.pass(m.getName() + " is Passed");
	}
	}
}

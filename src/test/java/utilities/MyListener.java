package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListener implements ITestListener{
	
ScreenshotUtility s = new ScreenshotUtility();
	
	public void onTestStart(ITestResult result) {
		System.out.println("Test method has been started");
	}

	public void onTestSuccess(ITestResult result) {

		System.out.println("Test method is success");
		
	}

	public void onTestFailure(ITestResult result) {
		
		System.out.println("Test method is failure");
		
		/*
		 * try { ScreenshotUtility.takeScreenshot(); } catch (IOException e) { // TODO
		 * Auto-generated e.printStackTrace(); }
		 */
		 
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test method is sipped");
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println("Test method is failure due to timeout");
	}

	public void onStart(ITestContext context) {
		
		System.out.println("Testing has started");
		
	}

	public void onFinish(ITestContext context) {
		
		System.out.println("Testing has ended");
	}


}

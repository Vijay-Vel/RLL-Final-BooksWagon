package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FictionBooksPage extends TestBase {
	
	@FindBy (xpath="(//div[@class='text-center position-relative blackhover'])[1]")
	WebElement TouchBook;
	
	@FindBy(xpath="//input[@class='btn themebackground text-white']")
	WebElement BuyNow;
	
	@FindBy(xpath="(//span[@class='itemcount'])[1]")
	WebElement Cart;

	public FictionBooksPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
	}
	
	public void click_book() throws InterruptedException {
		Thread.sleep(1500);
		TouchBook.click();
		BuyNow.click();
		
	}
	
	public void Cart() throws InterruptedException
	{
		Cart.click();
		Thread.sleep(1500);
	}
}

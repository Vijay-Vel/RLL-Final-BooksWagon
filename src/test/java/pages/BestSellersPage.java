package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BestSellersPage extends TestBase {
	
	@FindBy(xpath="//div[@id='listpromoproduct']/descendant::a[1]")
	WebElement Book;
	
	@FindBy(xpath="//div[@id='ctl00_phBody_ProductDetail_divaction']/descendant::input[1]")
	WebElement Cart;
	
	public BestSellersPage(WebDriver driver) {
		PageFactory.initElements(driver, this);	
		
	}

	public void click_book() {
		Book.click();
		Cart.click();
	}
}

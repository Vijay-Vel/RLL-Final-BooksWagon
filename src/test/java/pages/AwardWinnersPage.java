package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AwardWinnersPage extends TestBase {
	
	@FindBy(xpath="//select[@id='ddlSort']")
	WebElement sort;
	
	@FindBy(xpath="//select[@id='ddlSort']/option[4]")
	WebElement Discount;
	
	@FindBy(xpath="//div[@id='listpromoproduct']/descendant::a")
	WebElement Book;
	
	@FindBy(xpath="//div[@id='ctl00_phBody_ProductDetail_divaction']/descendant::input[1]")
	WebElement Cart;
	
	public AwardWinnersPage(WebDriver driver) {
		PageFactory.initElements(driver, this);	
		
	}
	
	public void sortaward() {
		sort.click();
		Discount.click();
	}
	
	public void buybook() throws InterruptedException {
		Thread.sleep(1000);
		Book.click();
		Cart.click();
		
	}
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TodaysDealPage extends TestBase {

	@FindBy(xpath=("//li[@id='ctl00_li4']"))
	WebElement todaysdeal;
	@FindBy(xpath=("(//img[@class='card-img-top bklazy'])[1]"))
	WebElement selectingtodaysdeal;
	@FindBy(xpath=("//input[@value='Buy Now']"))
	WebElement buynowtodaysdeal;

	public TodaysDealPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
	}

	public void todaysdeallog() {

		todaysdeal.click();
		selectingtodaysdeal.click();
		buynowtodaysdeal.click();
	
	}
}

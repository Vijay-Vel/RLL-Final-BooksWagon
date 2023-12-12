package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase{
	
	Actions a = new Actions(driver);
	
	@FindBy(xpath="//div[@class='col-sm-5 d-flex align-items-center justify-content-end']/descendant::span[3]")
	WebElement MyAccount;
	
	@FindBy(xpath="//div[@class='userpopup']/descendant::a[1]")
	WebElement LoginBtn;
	
	@FindBy(linkText="Fiction Books")
	WebElement FictionLink;
	
	@FindBy(xpath=("//input[@class='inputbar']"))
	static WebElement searchbox1;
	
	@FindBy(xpath=("//input[@name='btnTopSearch']"))
	static WebElement searchbutton;
	
	@FindBy(xpath="//input[@value='Add to Wishlist']")      
	static WebElement wishlist1;
	
	@FindBy(linkText = "Request a Book")
	WebElement requestButton;
	
	@FindBy(xpath="//li[@id='ctl00_liAW']/a")
	WebElement awardWinners;
	
	@FindBy(linkText="Best sellers")
	WebElement BestSellers;
	
	@FindBy(linkText="Box Sets")
	static WebElement BoxSets;
	
	@FindBy(linkText=("New Arrivals"))
	static WebElement NewArrivals;
	
    public void click_RequestBook() {		
    	requestButton.click();
        
    }
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
	}
	public void	clickLogin() throws InterruptedException {
		
		a.moveToElement(MyAccount).perform();
		Thread.sleep(1500);
		
		LoginBtn.click();
		
	}
	
	public void click_fiction() {
		FictionLink.click();
	}
	

	public void searchitem(String search)
	{
		searchbox1.sendKeys(search);
		searchbutton.click();
	}
	public void wishlistsearch()
	{
		wishlist1.click();
		//assertTrue(true);
	}
	
	public void	clickaward() {
		awardWinners.click();
	}
	
	public void click_BestSellers() {
		BestSellers.click();
	}

	public void clickBoxSets()
	{
		BoxSets.click();
	}
	
	public void NewArrivals()
	{
		NewArrivals.click();
	}
}

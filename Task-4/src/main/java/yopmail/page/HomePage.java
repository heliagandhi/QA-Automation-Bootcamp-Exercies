package yopmail.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
	@FindBy(xpath = "//input[@id='login']")
	private WebElement username;
	
	@FindBy(xpath = "//button[@title='Check Inbox @yopmail.com']")
	private WebElement nextBtn;
	
	
	public HomePage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
		PageFactory.initElements(driver.get(), this);
	}
	
	public void inputUsername(String user) {
		username.sendKeys(user);
	}
	
	public void clickBtn() {
		nextBtn.click();
	}
}

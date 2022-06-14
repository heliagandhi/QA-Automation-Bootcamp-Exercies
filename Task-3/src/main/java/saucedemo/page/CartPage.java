package saucedemo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage{
	@FindBy(xpath = "//button[@id='checkout']")
	private WebElement checkout;
	
	@FindBy(xpath = "//span[@class='title']")
	private WebElement cartText;

	public CartPage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
		PageFactory.initElements(driver.get(), this);
	}
	
	public void clickCheckout() {
		checkout.click();
	}
	
	public String getCartText() {
		return cartText.getText();
	}
}

package saucedemo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CatalogPage extends BasePage{
	@FindBy(xpath = "//span[@class='title']")
	private WebElement catalogText;
	
	@FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-fleece-jacket']")
	private WebElement addCart;
	
	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	private WebElement cart;

	public CatalogPage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
		PageFactory.initElements(driver.get(), this);
	}
	
	public String getCatalogText() {
		return catalogText.getText();
	}
	
	public void clickAddCart() {
		addCart.click();
	}
	
	public void clickCart() {
		cart.click();
	}
}

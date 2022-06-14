package saucedemo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage{
	//CHECKOUT: YOUR INFORMATION
		@FindBy(xpath = "//input[@id='first-name']")
		private WebElement firstName;
		
		@FindBy(xpath = "//input[@id='last-name']")
		private WebElement lastName;
		
		@FindBy(xpath = "//input[@id='postal-code']")
		private WebElement postalCode;
		
		@FindBy(xpath = "//input[@id='continue']")
		private WebElement continueBtn;
		
		@FindBy(xpath = "//span[@class='title']")
		private WebElement informationText;
		
		//CHECKOUT: OVERVIEW
		@FindBy(xpath = "//span[@class='title']")
		private WebElement overviewText;
		
		@FindBy(xpath = "//button[@id='finish']")
		private WebElement finishBtn;
		
		//CHECKOUT: COMPLETE!
		@FindBy(xpath = "//h2[normalize-space()='THANK YOU FOR YOUR ORDER']")
		private WebElement completeText;
		
		public CheckoutPage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
			super(driver, explicitWait);
			PageFactory.initElements(driver.get(), this);
		}
		
		//CHECKOUT: YOUR INFORMATION
		public void checkOutPage(String fName, String lName, String pCode) {
			firstName.sendKeys(fName);
			lastName.sendKeys(lName);
			postalCode.sendKeys(pCode);
			continueBtn.click();
		}

		public void inputFirstName(String fName) {
			firstName.sendKeys(fName);
		}

		public void inputLastName(String lName) {
			lastName.sendKeys(lName);
		}
		
		public void inputPostalCode(String pCode) {
			postalCode.sendKeys(pCode);
		}

		public void clickContinueButton() {
			continueBtn.click();
		}
		
		public String getInformationText() {
			return informationText.getText();
		}
		
		//CHECKOUT: OVERVIEW
		public String getOverviewText() {
			return overviewText.getText();
		}
		
		public void clickFinishButton() {
			finishBtn.click();
		}
		
		//CHECKOUT: COMPLETE!
		public String getCompleteText() {
			return completeText.getText();
		}
}

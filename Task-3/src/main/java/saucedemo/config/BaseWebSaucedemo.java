package saucedemo.config;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseWebSaucedemo {
	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private ThreadLocal<WebDriverWait> explicitWait = new ThreadLocal<WebDriverWait>();

	@BeforeMethod
	public void openUrl() {

		WebDriverManager.chromedriver().setup();
		driver.set(new ChromeDriver());
		explicitWait.set(new WebDriverWait(driver.get(), Duration.ofSeconds(60)));
		driver.get().manage().window().maximize();
		driver.get().get("https://www.saucedemo.com/");

	}

	@AfterMethod
	public void close() {

		driver.get().quit();

	}

	public ThreadLocal<WebDriver> getDriver() {
		return driver;
	}

	public void setDriver(ThreadLocal<WebDriver> driver) {
		this.driver = driver;
	}

	public ThreadLocal<WebDriverWait> getExplicitWait() {
		return explicitWait;
	}

	public void setExplicitWait(ThreadLocal<WebDriverWait> explicitWait) {
		this.explicitWait = explicitWait;
	}
}

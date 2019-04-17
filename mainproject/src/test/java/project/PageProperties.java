package project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageProperties {

	protected WebDriver driver;
	protected WebDriverWait wait;

	public PageProperties(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 45, 500);
	}

	public WebDriver setPropertyWindow() {
		driver.manage().window().maximize();
		return driver;
	}

}

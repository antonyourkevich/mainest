package project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VKLoginPage {
	private String userTelNumber = "";
	private String password = "";
	
	@FindBy(xpath = ".//input[@id='index_email']")
	private WebElement loginField;

	@FindBy(xpath = ".//input[@id='index_pass']")
	private WebElement passwordField;

	@FindBy(xpath = ".//button[@id=\"index_login_button\"]")
	private WebElement loginButton;

	@FindBy(xpath = ".//div[@class='top_profile_name']")
	private WebElement logoutField;
	
	public VKLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void login(WebDriver driver) {
		loginField.clear();
		loginField.sendKeys(userTelNumber);
		passwordField.clear();
		passwordField.sendKeys(password);
		loginButton.click();
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(logoutField));
	}
}

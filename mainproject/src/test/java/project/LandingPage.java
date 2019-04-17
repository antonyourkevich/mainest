package project;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LandingPage extends PageProperties {
	@FindBy(id = "mailbox:login")
	private WebElement loginField;

	@FindBy(id = "mailbox:password")
	private WebElement passwordField;

	@FindBy(xpath = "(//div[contains(@class,'checkbox__box') and ancestor::div[@id='b-letters']and not(ancestor::div[contains(@style, 'display: none;')])])")
	public List<WebElement> checkboxList;

	public LandingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void openPage(String Url) {
		driver.get(Url);
	}

	public void enterLoginAndPassword(String email, String password) {

		enterText(loginField, email);
		enterText(passwordField, password);
		passwordField.submit();
		
	}

	public void loginToMailRuAccount(String Url, String login, String password) {
		openPage(Url);
		enterLoginAndPassword(login, password);
	}

	public void enterText(WebElement element, String text) {

		element.sendKeys(text);
	}
}

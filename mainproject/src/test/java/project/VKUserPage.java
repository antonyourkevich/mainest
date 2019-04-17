package project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VKUserPage {

	@FindBy(xpath = ".//a[text()='Лена Юркевич']")
	private WebElement authorNickname;

	@FindBy(xpath = ".//div[text()=\"Lena's text\"]")
	private WebElement postText;

	@FindBy(xpath = ".//div[text()=\"Lena's EDITED text\"]")
	private WebElement editedPostText;

	public VKUserPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public boolean isPostPresent(WebDriver driver) {
		new WebDriverWait(driver, 6).until(ExpectedConditions.visibilityOf(authorNickname));
		return postText.isDisplayed();
	}

	public boolean isPostEdited(WebDriver driver) {
		new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(editedPostText));
		return editedPostText.isDisplayed();
	}

}

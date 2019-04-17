package project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage
{
    private static final int LINK_PRESENSE_TIMEOUT = 10;

	private WebDriver driver;

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='mailbox:submit']/input")
    private WebElement buttonEnter;

    @FindBy(xpath = "//*[@id='PH_logoutLink']")
    private WebElement logoutLink;

    @FindBy(xpath = "//*[@id='mailbox:error']")
    public WebElement logoutLinkError;

    public LoginPage(WebDriver webdriver)
    {
        PageFactory.initElements(webdriver, this);
        this.driver = webdriver;
    }

    public void enterLoginAndPass(String login, String password)
    {
        loginField.clear();
        loginField.sendKeys(login);
        passwordField.clear();
        passwordField.sendKeys(password);

    }

    public void clickEnterButton()
    {
        buttonEnter.click();
	}

	public boolean logoutLinkPresents() {
		return (new WebDriverWait(driver, LINK_PRESENSE_TIMEOUT)).until(ExpectedConditions.visibilityOf(logoutLink))
				.isDisplayed();
	}

	public boolean logoutLinkErrorPresents() {
		return (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(logoutLinkError)).isDisplayed();
	}
}

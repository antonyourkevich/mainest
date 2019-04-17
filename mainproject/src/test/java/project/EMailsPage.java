package project;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EMailsPage extends PageProperties {

	@FindBy(xpath = ".//a[@data-name='link' and not(ancestor::div[contains(@style, 'display: none;')])]")
	private List<WebElement> lettersList;

	@FindBy(xpath = "(//div[contains(@class,'checkbox__box') and ancestor::div[@id='b-letters']and not(ancestor::div[contains(@style, 'display: none;')])])")
	public List<WebElement> checkboxList;

	@FindBy(xpath = ".//span[contains(text(),'в папку «Спам».')]")
	public WebElement addedToSpamAlert;

	@FindBy(xpath = ".//span[contains(text(),'в папку «Входящие»')]")
	public WebElement moveFromSpamToInboxAlert;

	@FindBy(xpath = ".//div[@class='b-datalist b-datalist_letters b-datalist_compact b-datalist_letters_compact b-datalist_letters_compact_from']//div[1]//div[1]//a[1]//div[1]//div[1]//div[1]")
	public WebElement checkbox;

	@FindBy(xpath = "(.//div[@data-name=\"flagged\"])[1]")
	public WebElement allFlagedLettersButton;

	@FindBy(xpath = "(.//div[contains(@class, 'b-checkbox__box')])[1]")
	public WebElement checkAll;

	@FindBy(xpath = "(.//div[@data-name='spam'])[1]")
	public WebElement spamButton;

	@FindBy(xpath = "(.//div[@data-name='noSpam'])[1]")
	public WebElement noSpamButton;

	@FindBy(xpath = ".//div[@id='b-nav_folders']//span[text()='Спам']")
	public WebElement spamFolder;

	@FindBy(xpath = "(.//a[@data-name='compose'])[1]")
	private WebElement writeLetterButton;

	@FindBy(xpath = "(.//*[@data-original-name='To'])[1]")
	private WebElement letterRecipient;

	@FindBy(xpath = "//*[@name='Subject']")
	private WebElement topicLetter;

	@FindBy(xpath = ".// td/iframe")
	public WebElement iframeTextMessage;

	String textMessageField = ".//body [@id='tinymce']";

	@FindBy(xpath = "(.//div[@data-name='send'])[1]")
	public WebElement sendButton;

	@FindBy(xpath = ".//div[@class='message-sent__title']")
	private WebElement messageSentTitle;

	@FindBy(xpath = ".//a[@data-name='link'and not(ancestor::div[contains(@style, 'display: none;')])]//div[contains(@class, 'b-flag_yes')]//b")
	public List<WebElement> flagsList;

	@FindBy(xpath = "(.//div[contains(@class, 'b-dropdown_more')])[1]")
	public WebElement moreButtons;

	@FindBy(xpath = "(.//a[@data-name='flag_no'])[1]")
	public WebElement removeFlags;

	public EMailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickCheckbox(int index) {
		try {
			checkboxList.get(index).click();
		} catch (IndexOutOfBoundsException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void clickSpamButton() {
		spamButton.click();
	}

	public void clickNoSpamButton() {
		noSpamButton.click();
	}

	public void moveLetterToSpam(int index) {
		clickCheckbox(index);
		clickSpamButton();
	
	}

	public void extractLetterFromSpam(int index) {
		clickCheckbox(index);
		clickNoSpamButton();
		
	}

	public boolean isAddedToSpamAlertPresent() {
		return addedToSpamAlert.isDisplayed();
	}

	public boolean isMoveFromSpamAlertPresent() {
		return moveFromSpamToInboxAlert.isDisplayed();
	}

	public void goToSpam() {
		spamFolder.click();
	
	}

	public void sendLetter(List<String> sendToEmails, String topic, String message) {
		writeLetterButton.click();
		sendToEmails.forEach(email -> letterRecipient.sendKeys(email));
		topicLetter.sendKeys(topic);
		enterTextOfMessage(message);
		sendButton.click();
		
	}

	public void enterTextOfMessage(String message) {
		driver.switchTo().frame(iframeTextMessage).findElement(By.xpath(textMessageField)).sendKeys(message);
		driver.switchTo().defaultContent();
	}

	public String getMessageSentTitle() {
		return messageSentTitle.getText();
	}

	public List<WebElement> getLettersList() {
		return lettersList;
	}

	public List<WebElement> getCheckBoxList() {
		return checkboxList;
	}

	public void markFlagOfFirst(int count) {
		List<WebElement> letters = lettersList;
		for (int row = 0; row < count; row++) {
			(letters.get(row)).findElement(By.xpath(".//div[@data-act='flag']")).click();
		}
	}

	public void removeAllFlag() {
		allFlagedLettersButton.click();
		checkAll.click();
		moreButtons.click();
		removeFlags.click();
	}

	public List<WebElement> getMarkTheFlag() {
		return flagsList;
	}
}

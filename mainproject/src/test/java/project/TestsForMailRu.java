package project;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestsForMailRu {

	private WebDriver driver;
	private EMailsPage eMailsPage;
	private LandingPage landingPage;
	private static final String Url = "https://mail.ru/";
	private static final String email = "login";
	private static final String password = "pass";

	@BeforeClass
	public void beforeClass() {
		System.out.println("Test class TestsForMailRu is started.");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("Test class TestsForMailRu is ended.");

	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		landingPage = new LandingPage(driver);
		eMailsPage = new EMailsPage(driver);
		landingPage.setPropertyWindow();
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

	@Test(priority = 4)
	public void testMoveToSpamFolder() {
		landingPage.loginToMailRuAccount(Url, email, password);
		Assert.assertNotNull(eMailsPage.getLettersList(), "There is no letters!");
		int indexOfLetter = 0;
		eMailsPage.moveLetterToSpam(indexOfLetter);
		Assert.assertTrue(eMailsPage.isAddedToSpamAlertPresent(), "Letter is not added to Spam folder!");
	}

	
	@Test(priority = 5)
	public void testExtractionFromSpamFolder() {
		landingPage.loginToMailRuAccount(Url, email, password);
		eMailsPage.goToSpam();
		Assert.assertNotNull(eMailsPage.getLettersList(), "There is no letters!");
		int indexOfLetter = 0;
		eMailsPage.extractLetterFromSpam(indexOfLetter);
		Assert.assertTrue(eMailsPage.isMoveFromSpamAlertPresent(), "Letter is moved from Spam folder!");
	}

	
	@Test(priority = 1)
	public void testSendMessageToSomeAddresses() {
		landingPage.loginToMailRuAccount(Url, email, password);
		String[] sendToEmails = { "shpeter94@mail.ru;", "test@test.com;", "test1@test.com" };
		String topic = "Topic";
		String text = "Test text for email.";
		eMailsPage.sendLetter(Arrays.asList(sendToEmails), topic, text);
		String expectedMessage = "Ваше письмо отправлено. Перейти во Входящие";
		Assert.assertEquals(expectedMessage, eMailsPage.getMessageSentTitle());
	}

	@Test(priority = 2)
	public void testFlagging3Letters() {
		landingPage.loginToMailRuAccount(Url, email, password);
		Assert.assertTrue(eMailsPage.getCheckBoxList().size() > 3, "Not enough letters to flag!");
		int numberMarked = 3;
		eMailsPage.markFlagOfFirst(numberMarked);
		Assert.assertEquals(eMailsPage.flagsList.size(), numberMarked);
	}

	@Test(priority = 3)
	public void deselectAllFlagsTest() {
		landingPage.loginToMailRuAccount(Url, email, password);
		eMailsPage.removeAllFlag();
		Assert.assertFalse(eMailsPage.getMarkTheFlag().size() > 0);
	}

}

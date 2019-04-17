package project;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test_VKWallPostEditDelete {
	private VKLoginPage vKloginPage;
	private VKUserPage vKUserPage;
	private HttpClient client;
	private WebDriver driver;
	private URIBuilder builder;
	private HttpGet request;
	private HttpResponse response;
	private static String postText = "Lena's text";
	private static String editedPostText = "Lena's EDITED text";
	private static String postId;
	private static String responseCode;

	@Before
	public void setUp() {
		client = HttpClientBuilder.create().build();
		driver = new ChromeDriver();
		vKloginPage = new VKLoginPage(driver);
		vKUserPage = new VKUserPage(driver);
		driver.get("https://vk.com/");
		vKloginPage.login(driver);
		driver.navigate().to("https://vk.com/XXXXXXXXXXX");

	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void checkPostIsPresent() throws ClientProtocolException, IOException, URISyntaxException {
		builder = new URIBuilder("https://api.vk.com/method/wall.post?");
		builder.setParameter("access_token",
				"2007d5e92f8e4e5ca15823cb1fae848a68ac534dd03c205308c33916bdab61c5818bf732f162d7d36297f")
				.setParameter("owner_id", "XXXXXXXXXXX").setParameter("message", postText)
				.setParameter("attachments", "photo15919970_456239031").setParameter("v", "5.92");
		request = new HttpGet(builder.build());
		response = client.execute(request);
		postId = (EntityUtils.toString(response.getEntity())).replaceAll("[^0-9]", "");
		Assert.assertTrue(vKUserPage.isPostPresent(driver));
	}

	@Test
	public void checkPostIsEdited() throws ClientProtocolException, IOException, URISyntaxException {
		builder = new URIBuilder("https://api.vk.com/method/wall.edit?");
		builder.setParameter("access_token",
				"2007d5e92f8e4e5ca15823cb1fae848a68ac534dd03c205308c33916bdab61c5818bf732f162d7d36297f")
				.setParameter("owner_id", "XXXXXXXXXXX").setParameter("post_id", postId)
				.setParameter("message", editedPostText).setParameter("attachments", "photo15919970_377416426")
				.setParameter("v", "5.92");
		request = new HttpGet(builder.build());
		response = client.execute(request);
		System.out.println(EntityUtils.toString(response.getEntity()));
		Assert.assertTrue(vKUserPage.isPostEdited(driver));
	}

	@Test
	public void checkPostIsDeleted() throws ClientProtocolException, IOException, URISyntaxException {
		builder = new URIBuilder("https://api.vk.com/method/wall.delete?");
		builder.setParameter("access_token",
				"2007d5e92f8e4e5ca15823cb1fae848a68ac534dd03c205308c33916bdab61c5818bf732f162d7d36297f")
				.setParameter("owner_id", "XXXXXXXXXXX").setParameter("post_id", postId).setParameter("v", "5.92");
		request = new HttpGet(builder.build());
		response = client.execute(request);
		responseCode = (EntityUtils.toString(response.getEntity())).replaceAll("[^0-9]", "");
		Assert.assertEquals(Integer.parseInt(responseCode), 1);
	}

}

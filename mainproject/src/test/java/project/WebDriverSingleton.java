package project;

import org.openqa.selenium.WebDriver;

public class WebDriverSingleton {
	private static  Singleton  instance  =  new Singleton();
	private WebDriverSingleton()
	{
		
	}
	
	public static Singleton getInstance()
	{
		return instance;
	}

	public static WebDriver getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	

}

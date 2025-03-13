package com.tutorailsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop; //can be access in other classes by declaring it as public
	public Properties dataprop;
	
	//constructor
	public Base() {
		 prop = new Properties();
		 File propFile = new File(System.getProperty("user.dir") + File.separator + "src"
                 + File.separator + "main" + File.separator + "java"
                 + File.separator + "com" + File.separator + "tutorialsninja"
                 + File.separator + "qa" + File.separator + "config" + File.separator + "config.properties");
		 
		 dataprop = new Properties();
		 
		 File dataPropFile = new File(System.getProperty("user.dir") + File.separator + "src"
                 + File.separator + "main" + File.separator + "java"
                 + File.separator + "com" + File.separator + "tutorialsninja"
                 + File.separator + "qa" + File.separator + "testdata" + File.separator + "testdata.properties");
		 try {
		 FileInputStream datafis = new FileInputStream(dataPropFile);
		 dataprop.load(datafis);
		 }
		 catch(Throwable e) {
			 e.printStackTrace();
		 }
		 
		try {
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
	}
		catch(Throwable e) {
			e.printStackTrace();
		}
			
		}
	public WebDriver  initializeBrowserAndOpenApplication(String browserName) {
   
		if(browserName.equalsIgnoreCase("chrome")){
			driver = new ChromeDriver();
			
		}
		else if(browserName.equalsIgnoreCase("edge")){
			driver = new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIme));
		driver.get(prop.getProperty("url"));
		return driver;
		
	}

}

package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener{
	ExtentReports extentReports;
	ExtentTest extentTest ;

	@Override
	public void onStart(ITestContext context) {
	 extentReports = ExtentReporter.generateExtentReport();
	 System.out.println("I am in listener start method");
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName()+" started executing" );
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		 
		extentTest.log(Status.PASS, result.getName()+" got sucessfully executed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
	
	
	//retreiving driver 
	WebDriver driver = null;
	try {
		driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
	} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	String destinationScreenshotPath= Utilities.captureScreenshot(driver, result.getTestName());
	extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
	System.out.println("Extent Report Path:12 ");
	extentTest.log(Status.INFO, result.getThrowable());
	extentTest.log(Status.FAIL, result.getName()+"got failed");
	System.out.println("I am in listener start method");
	
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+"got Skipped");
		
	
	}

	

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Flushing Extent Report...");
	    
		extentReports.flush();
		 System.out.println("Extent Report Flushed.");
		 //to get path of extent report 
		 String pathOfExtentReport= System.getProperty("user.dir")+File.separator+"test-output"+File.separator+"ExtentReports"+File.separator+"extentReport.html";
		 File extentReport = new File(pathOfExtentReport);
		 //Automatically open extent report 
		 
		 try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}

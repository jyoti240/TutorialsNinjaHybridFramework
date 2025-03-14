package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
public static   ExtentReports generateExtentReport() {
	ExtentReports extentReport = new ExtentReports();
	
	File extentReportFile= new File(System.getProperty("user.dir")+File.separator+"test-output"+File.separator+"ExtentReports"+File.separator+"extentReport.html");
    ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
	
	sparkReporter.config().setTheme(Theme.DARK);
	sparkReporter.config().setReportName("TutorialsNinja Test Automation Result Report");
	sparkReporter.config().setDocumentTitle("TN  Automation  Report");
	sparkReporter.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");
	
	extentReport.attachReporter(sparkReporter);
	
	Properties configProp = new Properties();
	
	File configPropFile = new File(System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "main" + File.separator + "java"
            + File.separator + "com" + File.separator + "tutorialsninja"
            + File.separator + "qa" + File.separator + "config" + File.separator + "config.properties");
	try {
	FileInputStream fisConfigProp = new FileInputStream(configPropFile);
	configProp.load(fisConfigProp);
	}
	catch(Throwable e) {
		e.printStackTrace();
	}
	
	
	extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
	extentReport.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
	extentReport.setSystemInfo("Email", configProp.getProperty("validEmail"));
	extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
	extentReport.setSystemInfo("Operating System", System.getProperty("os.name"))	;
	extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
	extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
	
	return extentReport;
	
	
	
}
}
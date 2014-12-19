package com.example.tests;

import java.io.FileReader;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.example.fw.ApplicationManager;

public class TestBase {
	
	protected static ApplicationManager app;
	
	@BeforeTest
	@Parameters({"configFile"})
	public void setUp(@Optional String configFile) throws Exception {
		if (configFile == null) {
		 configFile = System.getProperty("configFile");
		}
		if (configFile == null) {
			 configFile = System.getenv("configFile");
		}
		if (configFile == null) {
			 configFile = "application.properties";
		}
		Properties props = new Properties();
		props.load(new FileReader(configFile));
		app = ApplicationManager.getInstance(props);	
	  }

//	@AfterTest
	public void tearDown() throws Exception {
		ApplicationManager.getInstance(null).stop();
	  }
}
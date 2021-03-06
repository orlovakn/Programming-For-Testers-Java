package com.example.tests;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.example.fw.ApplicationManager;

public class TestBase {
	
	protected static ApplicationManager app;
	private int checkCounter;
	private int checkFrequency;
	
	@BeforeTest
	public void setUp() throws Exception {
		String configFile = System.getProperty("configFile", "application.properties");
		Properties properties = new Properties();
		properties.load(new FileReader(new File(configFile)));
		app = new ApplicationManager(properties);	
		checkCounter = 0;
		checkFrequency = Integer.parseInt(properties.getProperty("check.frequency", "0"));
	  }

	protected boolean wantToCheck() {
		checkCounter++;
		if (checkCounter > checkFrequency)  {
			checkCounter = 0;
			return true;
		}
		else {
			return false;
		}
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		app.stop();
	  }
	
   public static List<Object[]> wrapGroupsForDataProvider(List<GroupData> groups) {
		List<Object[]> list = new ArrayList<Object[]>();
		for (GroupData group: groups) {
			list.add(new Object[]{group});
		}
		return list;
	}
	
   public static List<Object[]> wrapContactsForDataProvider(List<ContactData> contacts) {
		List<Object[]> list = new ArrayList<Object[]>();
		for (ContactData contact: contacts) {
			list.add(new Object[]{contact});
		}
		return list;
	}
}
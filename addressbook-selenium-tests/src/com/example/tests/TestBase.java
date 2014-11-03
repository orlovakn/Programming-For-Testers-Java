package com.example.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.example.fw.ApplicationManager;

public class TestBase {
	
	protected static ApplicationManager app;
	
	@BeforeTest
	public void setUp() throws Exception {
		app = new ApplicationManager();	
	  }

	@AfterTest
	public void tearDown() throws Exception {
		app.stop();
	  }
	
	@DataProvider
	public Iterator<Object[]> randomValidGroupGenerator() {
 	   List<Object[]> list = new ArrayList<>();
 	   for (int i = 0; i < 5; i++) {
 		   GroupData group = new GroupData()
 		   .withName(generateRandomString())
 		   .withHeader(generateRandomString())
 		   .withFooter(generateRandomString());
 		   list.add(new Object[]{group});
 	   }
 	   return list.iterator();
	}
	
	public String generateRandomString() {
	 	   Random rnd = new Random();
	 	  if (rnd.nextInt(3) == 0){
	 		  return "";
	 		   } else {
	 		  return "test" +  rnd.nextInt();
	 		   }
	}
	
	  @DataProvider
	   public Iterator<Object[]> randomValidContactsGenerator() {
			List<Object[]> list = new ArrayList<Object[]>();
			   for (int i = 0; i < 5; i++) {
				   Random rnd2 = new Random(), rnd3 = new Random(), rnd4 = new Random();
		 		   ContactData contact = new ContactData();
		 		   	contact.firstname = generateRandomString();
		 		    contact.lastname = generateRandomString();
		 		    contact.address = generateRandomString();
		 		    contact.home = generateRandomString();
		 		    contact.mobile = generateRandomString();
		 		    contact.work = generateRandomString();
		 		    contact.email = generateRandomString();
		 		    contact.email2 = generateRandomString();
		 		    String[] bdays = {"-","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		 		    contact.bday = bdays[rnd2.nextInt(31)];
		 	        String[] months = {"-","January","February","March","April","May","June","July","August","September","October","November","December" };
		 		    contact.bmonth = months[rnd3.nextInt(13)];
		 		    contact.byear = Integer.toString(rnd4.nextInt(10000));	        
		 		    contact.address2 = generateRandomString();
		 		    contact.phone2 = generateRandomString();
		 		   list.add(new Object[]{contact});
		 	   }
			return list.iterator();
		}
}
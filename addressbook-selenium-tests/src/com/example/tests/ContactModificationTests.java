package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
	
	@Test
	public void modifySomeContact() {
		app.getNavigationHelper().openMainPage();
		
		// save old state
		List<ContactData> oldList = app.getContactHelper().getContacts();	
		
		//actions
		app.getContactHelper().initContactModification(2, 7);
		ContactData contact = new ContactData();
		contact.firstname = "newname";
		contact.lastname = "newlastname";
		contact.address = "newaddress";
		contact.mobile = "123456";
		contact.bday = "3";
		contact.bmonth = "October";
		contact.byear = "1984";
		contact.email = "1abc@test.com";
		contact.email2 = "2abc@test.com";
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitContactModification();
	    app.getContactHelper().returnToHomePage();
	    
	    //save new state
	 		List<ContactData> newList = app.getContactHelper().getContacts();	

	 	//compare states	
	 	oldList.remove(0);
	 	oldList.add(contact);
	 	Collections.sort(oldList);
	 	assertEquals(newList, oldList);
	}
}

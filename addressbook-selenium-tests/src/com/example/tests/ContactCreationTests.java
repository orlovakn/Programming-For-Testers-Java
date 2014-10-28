package com.example.tests;

import java.util.Collections;
import java.util.List;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{
	
  @Test(dataProvider = "randomValidContactsGenerator")
  public void testContactCreationWithValidData(ContactData contact) throws Exception {
	app.getNavigationHelper().openMainPage();
	
    // save old state
	List<ContactData> oldList = app.getContactHelper().getContacts();
	
	// actions
	app.getContactHelper().initContactCreation();
	app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
    
    //save new state
	List<ContactData> newList = app.getContactHelper().getContacts();	

    //compare states	
	oldList.add(contact);
	Collections.sort(oldList);
	assertEquals(newList, oldList);
  }
}
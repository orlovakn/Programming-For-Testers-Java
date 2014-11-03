package com.example.tests;

import com.example.utils.SortedListOf;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class ContactCreationTests extends TestBase{
	
  @Test(dataProvider = "randomValidContactsGenerator")
  public void testContactCreationWithValidData(ContactData contact) throws Exception {	
    // save old state
	SortedListOf<ContactData> oldList = app.getContactHelper().getContacts();
	
	// actions
	app.getContactHelper().createContact(contact);
    
    //save new state
	SortedListOf<ContactData> newList = app.getContactHelper().getContacts();	

    //compare states	
   assertThat(newList, equalTo(oldList.withAdded(contact))); 
  }
}
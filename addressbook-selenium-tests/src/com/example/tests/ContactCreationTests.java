package com.example.tests;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{
  @Test
  public void testNonEmptyContactCreation() throws Exception {
	app.getNavigationHelper().openMainPage();
	
    // save old state
	List<ContactData> oldList = app.getContactHelper().getContacts();	
	// actions
	app.getContactHelper().initContactCreation();
    ContactData contact = new ContactData();
    contact.firstname = "ContactFirstName";
    contact.lastname = "ContactLastName";
    contact.address = "Lenina Street";
    contact.home = "Saint Petersburg";
    contact.mobile = "+7123456789";
    contact.work = "Google";
    contact.email = "contact@testemail1";
    contact.email2 = "contact@testemail2";
    contact.bday = "3";
    contact.bmonth = "February";
    contact.byear = "1985";
    contact.group = "gr";
    contact.address2 = "TestAddress";
    contact.phone2 = "Monte Carlo";
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
  
  @Test
  public void testEmptyContactCreation() throws Exception {
	app.getNavigationHelper().openMainPage();
	
    // save old state
	List<ContactData> oldList = app.getContactHelper().getContacts();	
	
	//actions
    ContactData contact = new ContactData("", "", "", "", "", "", "", "", "-", "-", "", "[none]", "", "");
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
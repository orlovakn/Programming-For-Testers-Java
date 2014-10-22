package com.example.tests;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{
  @Test
  public void testNonEmptyContactCreation() throws Exception {
	app.getNavigationHelper().openMainPage();
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
  }
  
  @Test
  public void testEmptyContactCreation() throws Exception {
	app.getNavigationHelper().openMainPage();
	app.getContactHelper().initContactCreation();
    ContactData contact = new ContactData("", "", "", "", "", "", "", "", "-", "-", "", "[none]", "", "");
	app.getContactHelper().fillContactForm(contact);
	app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
  }
}


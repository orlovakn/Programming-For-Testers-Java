package com.example.tests;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
	
	@Test
	public void modifySomeContact() {
		app.getNavigationHelper().openMainPage();
		app.getContactHelper().initContactModification(2, 7);
		ContactData contact = new ContactData();
		contact.firstname = "new name";
		contact.lastname = "new lastname";
		contact.address = "new address";
		contact.mobile = "123456";
		contact.bday = "3";
		contact.bmonth = "October";
		contact.byear = "1984";
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitContactModification();
	    app.getContactHelper().returnToHomePage();
	}
}

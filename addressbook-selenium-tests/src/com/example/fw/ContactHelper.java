package com.example.fw;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;

public class ContactHelper extends HelperBase {
	
	public static boolean CREATION = true;
	public static boolean MODIFICATION = false;

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}
	
	private List<ContactData> cachedContacts; 

	public List<ContactData> getContacts() {
	if (cachedContacts == null) {
	 rebuildCache();
	}
	return cachedContacts;
	}
	
	private void rebuildCache() {
		cachedContacts = new ArrayList<ContactData>(); 
		List<WebElement> rows = driver.findElements(By.name("entry"));

		for (WebElement row : rows) {
			ContactData contact = new ContactData();
			contact.lastname = row.findElement(By.xpath(".//td[2]")).getText();
			contact.firstname = row.findElement(By.xpath(".//td[3]")).getText();
			cachedContacts.add(contact);
		}
	}

	public void deleteContact(int row, int column) {
		 selectContactByRowColumn(row, column);
		 click(By.xpath("//input[@name='update'][@value='Delete']"));
		 cachedContacts = null;
	}
	
	public void initContactCreation() {
	   click(By.linkText("add new"));
	}

	public void fillContactForm(ContactData contact, boolean formType) {
	   type(By.name("firstname"), contact.firstname);
	   type(By.name("lastname"), contact.lastname);
	   type(By.name("address"), contact.address);
	   type(By.name("home"), contact.home);
	   type(By.name("mobile"), contact.mobile);
	   type(By.name("work"), contact.work);
	   type(By.name("email"), contact.email);
	   type(By.name("email2"), contact.email2);
	   selectByText(By.name("bday"), contact.bday);
	   selectByText(By.name("bmonth"), contact.bmonth);
	   type(By.name("byear"), contact.byear);
	   if (formType == CREATION) {
	  // selectByText(By.name("new_group"), contact.group);
	   } else {
		   if (driver.findElements(By.name("new_group")).size() !=0) {
			   throw new Error("Group selector exists in contact modification form");
		   }
	   }
	   type(By.name("address2"), contact.address2);
	   type(By.name("phone2"), contact.phone2);
	}

	public void submitContactCreation() {
	  click(By.name("submit"));
	  cachedContacts = null;
	}

	public void returnToHomePage() {
	  click(By.linkText("home"));
	}

	private void selectContactByRowColumn(int row, int column) {
		click(By.xpath("//table[@id='maintable']/tbody/tr[" + row + "]/td[" + column + "]/a/img[@alt='Edit']"));
	}

	public void initContactModification(int row, int column) {
	   selectContactByRowColumn(row, column);	
	}

	public void submitContactModification() {
	  click(By.xpath("//input[@name='update'][@value='Update']"));
	  cachedContacts = null;
	}
}
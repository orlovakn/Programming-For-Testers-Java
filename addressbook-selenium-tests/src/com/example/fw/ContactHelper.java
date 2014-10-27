package com.example.fw;

import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;
import com.example.tests.GroupData;
import com.example.tests.TestBase;

public class ContactHelper extends HelperBase {

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	public void initContactCreation() {
	   click(By.linkText("add new"));
	}

	public void fillContactForm(ContactData contact) {
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
	   selectByText(By.name("new_group"), contact.group);
	   type(By.name("address2"), contact.address2);
	   type(By.name("phone2"), contact.phone2);
	}

	public void submitContactCreation() {
	  click(By.name("submit"));
	}

	public void returnToHomePage() {
	  click(By.linkText("home"));
	}

	public void deleteContact(int row, int column) {
		 selectContactByRowColumn(row, column);
		 click(By.xpath("//input[@name='update'][@value='Delete']"));
	}

	private void selectContactByRowColumn(int row, int column) {
		click(By.xpath("//table[@id='maintable']/tbody/tr[" + row + "]/td[" + column + "]/a/img[@alt='Edit']"));
	}

	public void initContactModification(int row, int column) {
	   selectContactByRowColumn(row, column);	
	}

	public void submitContactModification() {
	  click(By.xpath("//input[@name='update'][@value='Update']"));
	}

	public List<ContactData> getContacts() {
    	List<ContactData> contacts = new ArrayList<ContactData>(); 
    	List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
    	for (WebElement checkbox : checkboxes) {
			ContactData contact = new ContactData();
			
			String title = checkbox.getAttribute("title");
			String fullname = title.substring("Select (".length(), title.length()- ")".length());
			int spaceindex = fullname.indexOf(" ");
			if (spaceindex > 0) {
			 contact.firstname = fullname.substring(0, spaceindex);	
			} 
			else {
			contact.firstname = "";	
			}
			//System.out.println(contact.firstname);
			if ((fullname.length() - spaceindex)>0) { 
			contact.lastname = fullname.substring(spaceindex + 1);
			}
			else {
			contact.lastname = "";
			}
			
			String accept = checkbox.getAttribute("accept");
			int semicolonindex = accept.indexOf(";");
			
			if (semicolonindex>0) {
			contact.email = accept.substring(0, semicolonindex);
			}
			else {
			contact.email = "";
			}
			if ((accept.length() - semicolonindex) > 0) {
				contact.email2 = accept.substring(semicolonindex+1);
			}
			else {
			contact.email2 = "";	
			}
			contacts.add(contact);
		}
    	return contacts;
	}

}

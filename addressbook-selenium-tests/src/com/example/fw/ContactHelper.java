package com.example.fw;

import java.util.ArrayList;
import java.util.List;

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
	
	// new getContacts method
	public List<ContactData> getContacts() {
		
	List<ContactData> contacts = new ArrayList<ContactData>(); 
	List<WebElement> rows = driver.findElements(By.name("entry"));

	for (WebElement row : rows) {
		ContactData contact = new ContactData();
		contact.lastname = row.findElement(By.xpath(".//td[2]")).getText();
		contact.firstname = row.findElement(By.xpath(".//td[3]")).getText();
	    contacts.add(contact);
	}
	return contacts;
	}
	
	/* Old get Contacts() method
	    public List<ContactData> getContacts() {
    	List<ContactData> contacts = new ArrayList<ContactData>(); 
    	
    	//Finding how many rows are in the table
    	WebElement ResultNumber = driver.findElement(By.xpath("//div[@id='content']/label/strong/span[@id='search_count']"));
    	Integer Rows = Integer.valueOf(ResultNumber.getText());
    	
    	for (int i = 2; i < Rows + 2; i++)
    	{
    		ContactData contact = new ContactData();
    		WebElement lastname = driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + i + "]/td[2]"));
    		WebElement firstname = driver.findElement(By.xpath("//table[@id='maintable']/tbody/tr[" + i + "]/td[3]"));
    		contact.firstname = firstname.getText();
    	//	System.out.println(contact.firstname);
    		contact.lastname = lastname.getText();
    		contacts.add(contact);
        }    		
    	return contacts;
	}*/
}
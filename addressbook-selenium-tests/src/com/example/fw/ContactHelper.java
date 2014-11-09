package com.example.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;
import com.example.utils.SortedListOf;

public class ContactHelper extends HelperBase {
	
	public static boolean CREATION = true;
	public static boolean MODIFICATION = false;

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}
	
	private SortedListOf<ContactData> cachedContacts; 

	public SortedListOf<ContactData> getContacts() {
	if (cachedContacts == null) {
	 rebuildCache();
	}
	return cachedContacts;
	}
	
	private void rebuildCache() {
		cachedContacts = new SortedListOf<ContactData>(); 
		manager.navigateTo().mainPage();
		List<WebElement> rows = driver.findElements(By.name("entry"));
		for (WebElement row : rows) {
			String lastname = row.findElement(By.xpath(".//td[2]")).getText();
			String firstname = row.findElement(By.xpath(".//td[3]")).getText();
			cachedContacts.add(new ContactData().withLastName(lastname).withFirstName(firstname));
		}
	}
	
	public ContactHelper createContact(ContactData contact) {
		manager.navigateTo().mainPage();
	   	initContactCreation();
	    fillContactForm(contact, CREATION);
	    submitContactCreation();
	    returnToHomePage();
	    return this;
	}
	
	public ContactHelper deleteContact(int row, int column) {
		 selectContactByRowColumn(row, column);
		 submitContactDeletion();
		 returnToHomePage();
		 cachedContacts = null;
		 return this;
	}
	
	public ContactHelper modifyContact(int i, int j, ContactData contact) {
		initContactModification(i, j);
		fillContactForm(contact, MODIFICATION);
		submitContactModification();
		returnToHomePage();
		return this;
	}
	
	public boolean getComparisonOfContactDataOnMainAndPrintPages() {
		 Integer numberOfContacts = getNumberOfContacts();
		 String[] contactMain = new String[numberOfContacts];
		 String[] contactPrint = new String[numberOfContacts];
				
		 contactPrint = getPrintedPhonesContactData();
		 contactMain = getContactsFullName();
			
			for (int i = 0; i < numberOfContacts; i++) {
				if (!contactMain[i].equals(contactPrint[i])) {
					return false;
					}
				}
		  return true;
		}
		
		public boolean getComparisonOfContactNumber() {
			return getNumberOfContacts() == getNumberOfPrintedPhones();
		}
//------------------------------------------------------------------------------------------------------------------------
	public ContactHelper initContactCreation() {
	   click(By.linkText("add new"));
	   return this;
	}

	public ContactHelper fillContactForm(ContactData contact, boolean formType) {
	   type(By.name("firstname"), contact.getFirstName());
	   type(By.name("lastname"), contact.getLastName());
	   type(By.name("address"), contact.getAddress());
	   type(By.name("home"), contact.getHome());
	   type(By.name("mobile"), contact.getMobile());
	   type(By.name("work"), contact.getWork());
	   type(By.name("email"), contact.getEmail());
	   type(By.name("email2"), contact.getEmail2());
	   selectByText(By.name("bday"), contact.getBday());
	   selectByText(By.name("bmonth"), contact.getBmonth());
	   type(By.name("byear"), contact.getByear());
	   if (formType == CREATION) {
	  // selectByText(By.name("new_group"), contact.group);
	   } else {
		   if (driver.findElements(By.name("new_group")).size() !=0) {
			   throw new Error("Group selector exists in contact modification form");
		   }
	   }
	   type(By.name("address2"), contact.getAddress2());
	   type(By.name("phone2"), contact.getPhone2());
	   return this;
	}

	public ContactHelper submitContactCreation() {
	  click(By.name("submit"));
	  cachedContacts = null;
	  return this;
	}

	public ContactHelper returnToHomePage() {
	  click(By.linkText("home"));
	  return this;
	}

	private void selectContactByRowColumn(int row, int column) {
		click(By.xpath("//table[@id='maintable']/tbody/tr[" + row + "]/td[" + column + "]/a/img[@alt='Edit']"));
	}

	public ContactHelper initContactModification(int row, int column) {
	   selectContactByRowColumn(row, column);
	   return this;
	}

	public ContactHelper submitContactModification() {
	  click(By.xpath("//input[@name='update'][@value='Update']"));
	  cachedContacts = null;
	  return this;
	}
	
	private void submitContactDeletion() {
		click(By.xpath("//input[@name='update'][@value='Delete']"));
	}

	public String[] getContactsFullName() {
		manager.navigateTo().mainPage();
		List<WebElement> rows = driver.findElements(By.name("entry"));
		String[] fullname = new String[rows.size()]; 
		int i = 0;
		for (WebElement row : rows) {
			String lastname = row.findElement(By.xpath(".//td[2]")).getText();
			String firstname = row.findElement(By.xpath(".//td[3]")).getText();
			String phonenumber = row.findElement(By.xpath(".//td[5]")).getText();
			if ((lastname.isEmpty())||(firstname.isEmpty())) {
				fullname[i] = firstname + lastname + phonenumber;
				} else {
					fullname[i] = firstname + " " + lastname + phonenumber;
					}
				i = i + 1;
			}
		return fullname;		
	}
	
	public String[] getPrintedPhonesContactData() {
		manager.navigateTo().printPhones();
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='view']/tbody/tr[@class='odd']"));
		List<WebElement> evenRows = driver.findElements(By.xpath("//table[@id='view']/tbody/tr[@class='even']"));
		String emptycell = ".";
		int j=0, k=1;
		
		for (WebElement evenRow : evenRows) {
			rows.add((k*2)-1, evenRow);
			k=k+1;
		}
		
		String[] fullname = new String[rows.size()*3], homePhone = new String[rows.size()*3], namePhone = new String[rows.size()*3];
		
		for (WebElement row : rows) {
			for (int l = 1; l < 4; l++) {
					String cell = row.findElement(By.xpath(".//td[" + l + "]")).getText();
					if (!cell.equals(emptycell)) {
									String[] list = cell.split("\n");
									fullname[j] = list[0].substring(0, list[0].lastIndexOf(":")).trim();
									if (list.length > 1) {
										if ((list[1].startsWith("H:"))||(list[1].startsWith("M:"))||(list[1].startsWith("W:")))
											{    
											homePhone[j] = list[1].substring(2).trim().replace(" ", "");
											}
											else homePhone[j] = "";								
									} else homePhone[j] = "";
									namePhone[j] =  fullname[j] + homePhone[j];
									j = j + 1;
								}
							}
						}
		return namePhone;
		}
	
	public int getNumberOfContacts() {
		manager.navigateTo().mainPage();
		List<WebElement> rows = driver.findElements(By.name("entry"));
	 	return rows.size();
	}
	
	public int getNumberOfPrintedPhones() {
		int n = 0;
		manager.navigateTo().printPhones();
		List<WebElement> oddRows = driver.findElements(By.xpath("//table[@id='view']/tbody/tr[@class='odd']"));
		List<WebElement> evenRows = driver.findElements(By.xpath("//table[@id='view']/tbody/tr[@class='even']"));
		oddRows.addAll(evenRows);
		String emptycell = ".";
		
		for (WebElement oddRow : oddRows) {
			for (int l = 1; l < 4; l++) {
		     	if (!oddRow.findElement(By.xpath(".//td[" + l + "]")).getText().equals(emptycell)) {
		     		n = n + 1;
				}
		      }
			} 
		return n;
	}
}
package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Random;
import org.testng.annotations.Test;
import com.example.utils.SortedListOf;

public class ContactModificationTests extends TestBase {

	@Test(dataProvider = "randomValidContactsGenerator")
	public void modifySomeContact(ContactData contact) {
		// save old state
		SortedListOf<ContactData> oldList = new SortedListOf<ContactData>(app.getHibernateHelper().listContacts());

		Random rnd = new Random();
		int index = rnd.nextInt(oldList.size() - 1);

		// actions
		app.getContactHelper().modifyContact(index+2, 7, contact);

		// save new state
		SortedListOf<ContactData> newList = app.getContactHelper().getUIContacts();

		// compare states
		assertThat(newList, equalTo(oldList.without(index).withAdded(contact)));
	}
}
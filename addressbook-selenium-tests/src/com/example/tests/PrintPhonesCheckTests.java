package com.example.tests;
import org.testng.annotations.Test;

public class PrintPhonesCheckTests extends TestBase {
	
	@Test
	public void testEntriesMatchOnPrintPhones() throws Exception {	

		if (app.getContactHelper().getComparisonOfContactNumber()) 
		{
		  System.out.println("SUCCESS: Number of Results on Home Page EQUALS the Number of Results Print Phones Page");
		  		if(app.getContactHelper().getComparisonOfContactDataOnMainAndPrintPages())
		  			{
		  				System.out.println("SUCCESS: Contact First Names, Last Names and Phones are the same on Home and Print Phone pages.");
		  			} 
		  			else { 
		  					System.out.println("FAIL: The elements on Home and Print Phone pages are DIFFERENT");
		  				 }
		 }
			else {
					System.out.println("FAIL: Number of Results on Home Page is DIFFERENT from the Number of Results on Print Phones Page.");
				}
		}
}
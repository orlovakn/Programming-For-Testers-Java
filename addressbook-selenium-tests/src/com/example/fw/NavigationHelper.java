package com.example.fw;

import org.openqa.selenium.By;

public class NavigationHelper extends WebDriverHelperBase {

	
	public NavigationHelper(ApplicationManager manager) {
		super(manager);
	}

	public void mainPage() {
		if (! onMainPage()) {
		driver.get(manager.baseUrl + "addressbookv4.1.4/");
	//	click(By.linkText("home"));
		}
	}
	
	public void printPhones() {
		if (! onPrintPhonesPage()) {
		click(By.linkText("print phones"));
		}
	}

	private boolean onPrintPhonesPage() {
		return driver.findElements(By.id("view")).size() > 0;
	}

	private boolean onMainPage() {
	   return driver.findElements(By.id("maintable")).size() > 0;
	}

	public void groupsPage() {
		if(!onGroupsPage()) {
		    click(By.linkText("groups"));
		}
	}

	private boolean onGroupsPage() {
		if (driver.getCurrentUrl().contains("/group.php")
			&& driver.findElements(By.name("new")).size() > 0) {
			return true;
			} else {
			return false;
			}
	}
}
package com.example.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.example.fw.Folders;

public class TestFolderDeletion extends TestBase{
	
	@Test
	public void testFolderDeletion() {
//select 1st folder = folder
		int index = 1;
		Folders oldFolders = app.getFolderHelper().getFolders();
		app.getFolderHelper().deleteFolder(index);
		Folders newFolders = app.getFolderHelper().getFolders();
		assertThat(newFolders.withAdded(folder), equalTo(oldFolders));

	}	
}

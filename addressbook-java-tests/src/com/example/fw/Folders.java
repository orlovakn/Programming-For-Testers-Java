package com.example.fw;

import java.util.ArrayList;
import java.util.List;


public class Folders {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((storedfolders == null) ? 0 : storedfolders.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folders other = (Folders) obj;
		if (storedfolders == null) {
			if (other.storedfolders != null)
				return false;
		} else if (!storedfolders.equals(other.storedfolders))
			return false;
		return true;
	}

	private List<String> storedfolders = null;
	
	public Folders(List<String> folders) {
	this.storedfolders = new ArrayList<String>(folders);
	}

	public Folders withAdded(String folder) {
		Folders newList = new Folders(storedfolders);
		newList.storedfolders.add(folder);
		return newList;
	}

	@Override
	public String toString() {
		return "Folders [" + storedfolders + "]";
	}
}

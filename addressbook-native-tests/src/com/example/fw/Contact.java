package com.example.fw;

public class Contact {

	public String firstName;
	public String lastName;
	
	public Contact setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public Contact setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

}

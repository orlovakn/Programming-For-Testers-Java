package com.example.tests;

public class Sample {
	
  public static void main(String[] args) {
	  String a = "tttest";
	  String b = "       +7 (916) 123-45-67    ";	  
	  b = b.trim();
	  System.out.println(b);
	  String[] list = b.split("\\s+"); 
//	  b = b.replaceAll("[ ()\\-]", "");
	  System.out.println(list);
	  System.out.println(b.matches("\\+\\d+"));
	  
	 ///System.out.println(list.length);
  // System.out.println(list[0]);
  // System.out.println(list[1]);
	//  System.out.println(list[2]);
  }
}
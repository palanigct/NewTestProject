package com.app.excelread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class testXLread {

	public static void main(String[] args) {
		
		/*Readfile_xx read = new Readfile_xx();
		read.data("src/test/resources/SIMONG_INPUT.xlsx", "APP_DATA");*/
		
		Readfile read = new Readfile();
		//read.data("src/test/resources/SIMONG_INPUT.xlsx", "APP_DATA");
		
		
		List<HashMap<String,String>> mydata = new ArrayList<HashMap<String, String>>();
		
		
		
		mydata =	read.data("src/test/resources/Release Status.xls", "Sheet1");
		
		System.out.println(mydata);
		
		/*
		String[] key = new String[]{"SCENARIO CODE :","EXECUTE ?","HOME","VALIDATE HOME PAGE","EXISTING CUSTOMER","EXISTING CUSTOMER SEARCH","ACCOUNT ID/PHONE NUMBER","SSN","TAX ID","ORDER ID","EC-LAST NAME","EC-FIRST NAME","EC-SERVICE ADDRESS HOUSE NUMBER","EC-SA-STREET NAME/PO BOX","EC-SA-CITY","EC-SA-STATE","EC-SA-ZIP","EC-BILLING ADDRESS HOUSE NUMBER","EC-BA-STREET NAME/PO BOX","EC-BA-CITY","EC-BA-STATE","EC-BA-ZIP","TOLL FREE","IP USER ID","EXISTING CUSTOMER SEARCH FINAL ACTION","CLICK VERIFY BUTTON","NEW CUSTOMER  PAGE","CUSTOMER TYPE","CUSTOMER TYPE FINAL ACTION","CALLER IDENTIFICATION","PURPOSE OF CALL","FIRST NAME","LAST NAME","CONTACT NUMBER","WHY IS THIS CUSTOMER CALLING TODAY ?","CALLER IDENTIFICATION FINAL ACTION","SEARCH SERVICE ADDRESS","FULL SERVICE ADDRESS","HOUSE NUMBER","STREET NAME","UNIT TYPE","UNIT NO","CITY","STATE","ZIP","CLICK SEARCH BUTTON","FILTER ADDRESS","CLICK VERIFY BUTTON","CLICK - HANDBOOK SEARCH LINK","HANDBOOK SEARCH TEXT","CLICK - HANDBOOK SEARCH BUTTON","HANDBOOK POP UP FINAL ACTION","SEARCH SERVICE ADDRESS FINAL ACTION","CUSTOMER DETAILS","ACCOUNT SUB TYPE","M-FIRST NAME","M-LAST NAME","M-MIDDLE NAME","M-CONTACT NUMBER","ADDITIONAL LINE","EMAIL","CUSTOMER DETAILS FINAL ACTION","ORDERING ACTIONS",};
		for(int j=0;j<5;j++){
		
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			for(int i=0;i<key.length;i++){
		System.out.println("VALUE  : "+mydata.get(j).get(key[i]));
			}
		}
		
		for(int i=0;i<key.length;i++){
			System.out.print("public static final String "+key[i]+" = ");
			System.out.print('"');
			System.out.print(key[i]);
			System.out.print('"');
			System.out.println(" ;");
		}
		*/
		
	}

}

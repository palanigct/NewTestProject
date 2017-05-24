package com.app.RallyApi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.app.excelread.Readfile;
import com.app.excelwrite.Excel_Write;

public class RallyRunner 
{
	public static RallyRunner obj=new RallyRunner();
	public static Get_Iteration_data ite_obj=new Get_Iteration_data();
	public static Other_Functions other_fun_obj=new Other_Functions();
	public static Get_Release_data rele_obj=new Get_Release_data();	
	
	public static ArrayList<String> team_list=new ArrayList<String>();
	
	public static void main(String arg[]) throws Throwable
	{
		System.out.println("started\n");
		
		team_list=other_fun_obj.get_team_List();   //get the team list
		
		other_fun_obj.copy_template_folder();	   //copy template folder to output folder	
		
		ite_obj.Exe_Iteration(team_list);          //execute iteration function for get the iterations data's 
		
		rele_obj.Exe_Release(team_list);		   //execute releases function for get the releases data's  
		
		other_fun_obj.copy_output_folder();        //copy the output folder to system 
		
		System.out.println("\ncompleted");		
	}	
	
}


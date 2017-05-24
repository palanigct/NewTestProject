package com.app.excelwrite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.jetty.util.TestCase;

import com.app.excelread.Readfile;
import com.app.pojos.Defects;
import com.app.pojos.TeamStatus;
import com.app.pojos.TestCases;
import com.app.pojos.UserStories;

public class Excel_Write 
{
	public static int currentRow_ite=3;
	public static int currentRow_rele=3;
	public static Readfile read = new Readfile();
	public static List<HashMap<String,String>> mydata_App_Data = read.data("src/test/resources/INPUT.xls", "APP_DATA");
	
	public static void write_Iteration_Status(TeamStatus team_status,String team_name) throws Exception
	{	
		String filename="src\\test\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
		
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet("Sheet1");
        HSSFCell cell = null;
        HSSFRow row=null;
               
        HSSFCellStyle style1=sheet.getRow(2).getCell(24).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(22).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(23).getCellStyle();
        
        if(StringUtils.containsIgnoreCase(team_name, "total"))
        	style1=style2=style3;
        
        	
        
        row=sheet.createRow(currentRow_ite);
        
       
        UserStories userstory= team_status.getUserStories();
		Defects defects=team_status.getDefects();
		TestCases testCase=team_status.getTestCases();	
		String per_exe="";
		String per_pass="";
		String per_fail="";
      
		if(testCase.getTotal()==0)
		{
			per_exe  = "N/A";
			per_pass = "N/A";
			per_fail = "N/A";			
		}
		else
		{
			per_exe  = testCase.getPercentage_execute()+"%";
			per_pass = testCase.getPercentage_pass()+"%";
			per_fail = testCase.getPercentage_fail()+"%";
		}
		
        for(short i=0;i<25;i++)
        {
        	cell=row.createCell(i);
        	
        	switch(i)
        	{
        	    case 0:   cell.setCellValue(team_name); 				 cell.setCellStyle(style1); break;
        		case 1:   cell.setCellValue(userstory.getBacklogs());    cell.setCellStyle(style2); break;
        		case 2:   cell.setCellValue(userstory.getDefined());     cell.setCellStyle(style2);break;
        		case 3:   cell.setCellValue(userstory.getIn_progress()); cell.setCellStyle(style2);break;
        		case 4:   cell.setCellValue(userstory.getCompleted());   cell.setCellStyle(style2);break;
        		case 5:   cell.setCellValue(userstory.getAccepted());    cell.setCellStyle(style2);break;        		
        		case 6:   cell.setCellValue(userstory.getTotal());       cell.setCellStyle(style3);break;
        		case 7:   cell.setCellValue(defects.getBacklogs());      cell.setCellStyle(style2);break;
        		case 8:   cell.setCellValue(defects.getDefined());       cell.setCellStyle(style2);break;
        		case 9:   cell.setCellValue(defects.getIn_progress());   cell.setCellStyle(style2);break;        	
        		case 10:  cell.setCellValue(defects.getCompleted());     cell.setCellStyle(style2);break;
        		case 11:  cell.setCellValue(defects.getAccepted());      cell.setCellStyle(style2);break;
        		case 12:   cell.setCellValue(defects.getTotal()); 	     cell.setCellStyle(style3);break;
        		case 13:   cell.setCellValue(testCase.getPass()); 	     cell.setCellStyle(style2);break;
        		case 14:   cell.setCellValue(testCase.getFail());        cell.setCellStyle(style2);break;        	
        		case 15:   cell.setCellValue(testCase.getIn_progress()); cell.setCellStyle(style2);break;
        		case 16:   cell.setCellValue(testCase.getBlocked()); cell.setCellStyle(style2);break;
        		case 17:   cell.setCellValue(testCase.getNo_run());  cell.setCellStyle(style2);break;      		
        		case 18:   cell.setCellValue(testCase.getTotal());	 cell.setCellStyle(style3);break; 
        		case 19:   cell.setCellValue(per_exe); 				 cell.setCellStyle(style2);break; 
        		case 20:   cell.setCellValue(per_pass); 			 cell.setCellStyle(style2);break; 
        		case 21:   cell.setCellValue(per_fail);	 			 cell.setCellStyle(style2);break; 
        		default:   cell.setCellValue("");  					break;
        	}        	
        }//end for
        
        
        currentRow_ite++;
        file.close();
 
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
	}
	
	public static void write_Release_Status(TeamStatus team_status,String team_name) throws Exception
	{		
		
		String filename="src\\test\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Release Status.xls";
		
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet("Sheet1");
        HSSFCell cell = null;
        HSSFRow row=null;       
        
        HSSFCellStyle style1=sheet.getRow(2).getCell(24).getCellStyle();
        HSSFCellStyle style2=sheet.getRow(2).getCell(22).getCellStyle();
        HSSFCellStyle style3=sheet.getRow(2).getCell(23).getCellStyle();
        
        if(StringUtils.containsIgnoreCase(team_name, "total"))
        	style1=style2=style3;
        
        row=sheet.createRow(currentRow_rele);
       
        
        UserStories userstory= team_status.getUserStories();
		Defects defects=team_status.getDefects();
		TestCases testCase=team_status.getTestCases();	
		
		String per_exe="";
		String per_pass="";
		String per_fail="";
      
		if(testCase.getTotal()==0)
		{
			per_exe  = "N/A";
			per_pass = "N/A";
			per_fail = "N/A";			
		}
		else
		{
			per_exe  = testCase.getPercentage_execute()+"%";
			per_pass = testCase.getPercentage_pass()+"%";
			per_fail = testCase.getPercentage_fail()+"%";
		}
      
        for(short i=0;i<25;i++)
        {
        	cell=row.createCell(i);
        	
        	switch(i)
        	{
        	    case 0:   cell.setCellValue(team_name);  					cell.setCellStyle(style1); break;
        		case 1:   cell.setCellValue(userstory.getBacklogs()); 		cell.setCellStyle(style2); break;
        		case 2:   cell.setCellValue(userstory.getDefined()); 		cell.setCellStyle(style2); break;
        		case 3:   cell.setCellValue(userstory.getIn_progress()); 	cell.setCellStyle(style2); break;
        		case 4:   cell.setCellValue(userstory.getCompleted()); 		cell.setCellStyle(style2); break;
        		case 5:   cell.setCellValue(userstory.getAccepted()); 		cell.setCellStyle(style2); break;        		
        		case 6:   cell.setCellValue(userstory.getTotal()); 			cell.setCellStyle(style3); break;
        		case 7:   cell.setCellValue(defects.getBacklogs()); 		cell.setCellStyle(style2); break;
        		case 8:   cell.setCellValue(defects.getDefined()); 			cell.setCellStyle(style2); break;
        		case 9:   cell.setCellValue(defects.getIn_progress()); 		cell.setCellStyle(style2); break;        	
        		case 10:  cell.setCellValue(defects.getCompleted()); 		cell.setCellStyle(style2); break;
        		case 11:  cell.setCellValue(defects.getAccepted()); 		cell.setCellStyle(style2); break;
        		case 12:   cell.setCellValue(defects.getTotal()); 			cell.setCellStyle(style3); break;
        		case 13:   cell.setCellValue(testCase.getPass()); 			cell.setCellStyle(style2); break;
        		case 14:   cell.setCellValue(testCase.getFail()); 			cell.setCellStyle(style2); break;        	
        		case 15:   cell.setCellValue(testCase.getIn_progress()); 	cell.setCellStyle(style2); break;
        		case 16:   cell.setCellValue(testCase.getBlocked()); 		cell.setCellStyle(style2); break;
        		case 17:   cell.setCellValue(testCase.getNo_run()); 		cell.setCellStyle(style2); break;      		
        		case 18:   cell.setCellValue(testCase.getTotal()); 			cell.setCellStyle(style3); break; 
        		case 19:   cell.setCellValue(per_exe); 						cell.setCellStyle(style2); break; 
        		case 20:   cell.setCellValue(per_pass); 					cell.setCellStyle(style2); break; 
        		case 21:   cell.setCellValue(per_fail); 					cell.setCellStyle(style2); break; 
        		default:   cell.setCellValue(""); break;
        	}        	
        }//end for
        
        
        currentRow_rele++;
        file.close();
 
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
	}
	
	public static void write_sprint_number(String name) throws IOException
	{
		String filename="src\\test\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
		
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet("Sheet1");
        HSSFCell cell = null;
        HSSFRow row=null;
        
        cell=sheet.getRow(0).getCell(20);
        cell.setCellValue(name);
        
        file.close();
        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();       
	}
	
	public static void write_release_number(String name) throws IOException
	{
		String filename="src\\test\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Release Status.xls";
		
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet = workbook.getSheet("Sheet1");
        HSSFCell cell = null;
        HSSFRow row=null;
        
        cell=sheet.getRow(0).getCell(20);
        cell.setCellValue(name);
        
        file.close();
        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();       
	}
	
	public static void write_userstoryAndDefect(UserStories story,String teamName,String statusType,String type) throws IOException
	{
		String 	filename="";
		if(StringUtils.containsIgnoreCase(statusType, "iteration"))
			filename="src\\test\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
		else
			filename="src\\test\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Release Status.xls";
			
		FileInputStream file = new FileInputStream(new File(filename));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        HSSFSheet sheet=null;
        HSSFCell cell = null;
        HSSFRow row=null; 
        
        if(StringUtils.containsIgnoreCase(type, "UserStory"))
        	 sheet = workbook.getSheet("Sheet2");
        else
        	 sheet = workbook.getSheet("Sheet3");
              
               
        int currentRow=sheet.getLastRowNum()+1;
        row=sheet.createRow(currentRow);
        
        for(int i=0;i<10;i++)
        {
        	cell=row.createCell(i);
        	switch(i)
        	{
        		case 0: cell.setCellValue(teamName); break;
        		case 1: cell.setCellValue(story.getFormattedID()); break;
        		case 2: cell.setCellValue(story.getStatus());break;
        		case 3: cell.setCellValue(story.getSprintname());break;
        		case 4: cell.setCellValue(story.getReleaseName());break;
        		case 5: cell.setCellValue(story.getName());break;
        		default : break;
        	}
        	
        }
        
        file.close();        
        FileOutputStream outFile =new FileOutputStream(new File(filename));
        workbook.write(outFile);
        outFile.close();
		
	}	
	
	public static void createRow_with_styleFormat() throws IOException
	{
		String newFilename="src\\test\\resources\\Output Folder\\Rally Report\\Excel Sheets\\Iteration Status.xls";
		FileInputStream Newfile = new FileInputStream(new File(newFilename));
        HSSFWorkbook newWorkbook = new HSSFWorkbook(Newfile);
        HSSFSheet newSheet = newWorkbook.getSheet("Sheet1");
        
        String oldFilename="src\\test\\resources\\Folder Templates\\Rally Report\\Excel Sheets\\Iteration Status.xls";
		FileInputStream oldfile = new FileInputStream(new File(newFilename));
        HSSFWorkbook oldWorkbook = new HSSFWorkbook(oldfile);
        HSSFSheet oldSheet = oldWorkbook.getSheet("Sheet1");
             
        HSSFRow oldrow=null;
        HSSFRow newrow= null;        
        HSSFCell oldcell = null;
        HSSFCell newcell = null;
        
        newrow=newSheet.createRow(10);
        newrow.createCell(0).setCellValue("test1");
        newrow.createCell(1).setCellValue("test2");
        newrow.createCell(2).setCellValue("test3");
        
        //oldrow=oldSheet.getRow(11);
        
        HSSFCellStyle style1=newSheet.getRow(2).getCell(0).getCellStyle();
        HSSFCellStyle style2=newSheet.getRow(2).getCell(22).getCellStyle();
        HSSFCellStyle style3=newSheet.getRow(2).getCell(23).getCellStyle();
        
        newrow.getCell(0).setCellStyle(style1); 
        newrow.getCell(1).setCellStyle(style2); 
        newrow.getCell(2).setCellStyle(style3); 
        
        /*for(int i=0;i<oldrow.getLastCellNum()-oldrow.getFirstCellNum();i++)
        {  
        	HSSFCellStyle style1=oldrow.getCell(i).getCellStyle();        	
        	newrow.getCell(i).setCellStyle(style1);     	
        }
        */
        
        
        Newfile.close();        
        FileOutputStream outFile =new FileOutputStream(new File(newFilename));
        newWorkbook.write(outFile);
        outFile.close();
	}
	
	
	public static void copy_cell_format()
	{
		
	}
	
	public static void main(String arg[]) throws IOException
	{
		Excel_Write obj=new Excel_Write();
		obj.createRow_with_styleFormat();
	}
}   
        
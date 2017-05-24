package com.app.excelread;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;




public class Readfile {
	
	public static List<HashMap<String,String>> data(String filepath,String sheetName)
	
   {
      List<HashMap<String,String>> mydata = new ArrayList<HashMap<String, String>>();
      try
      {
    	  
    	  
         FileInputStream fs = new FileInputStream(filepath);
         Workbook wb = Workbook.getWorkbook(fs);
       
         Sheet sheet = wb.getSheet(sheetName);
        
       //System.out.println("number of cloumn :"+sheet.getColumns());
         for(int i=1;i<sheet.getColumns();i++)
         {
            
            HashMap<String,String> currentHash = new HashMap<String,String>();
       
            for(int j=1;j<sheet.getRows();j++)
            {

                  currentHash.put(sheet.getCell(0, j).getContents().trim(), sheet.getCell(i, j).getContents().trim());
               
            }
            mydata.add(currentHash);
         }
         fs.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return mydata;
   }
}
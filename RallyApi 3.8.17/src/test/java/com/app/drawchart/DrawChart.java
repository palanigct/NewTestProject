package com.app.drawchart;

import java.awt.Color;
import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import com.app.excelread.Readfile;
import com.app.pojos.Defects;
import com.app.pojos.TeamStatus;
import com.app.pojos.TestCases;
import com.app.pojos.UserStories;

public class DrawChart extends ApplicationFrame 
{
	
	public static Readfile read = new Readfile();
	public static List<HashMap<String,String>> mydata_App_Data = read.data("src/test/resources/INPUT.xls", "APP_DATA");
	
	
    public static void draw_Piechart(TeamStatus total,String statusType) throws Exception
	{
	
    	String filepath="";
    	if(StringUtils.containsIgnoreCase(statusType, "Sprint"))
    	{
    		filepath="src\\test\\resources\\Output Folder\\Rally Report\\PieCharts\\Sprint\\";	
    	}
    	else
    	{
    		filepath="src\\test\\resources\\Output Folder\\Rally Report\\PieCharts\\Release\\";	    		
    	}
    	
		String img_path    = filepath;		
		String img_path_us = img_path+"Userstory-"+statusType+".png";
		String img_path_de = img_path+"Defect-"+statusType+".png";
		String img_path_tc = img_path+"TestCase-"+statusType+".png";
		
		UserStories us_total=total.getUserStories();
		Defects df_total=total.getDefects();
		TestCases tc_total=total.getTestCases();
	
		//user Stories
	
		String title="UserStories-"+statusType;	
		DefaultPieDataset dataset = new DefaultPieDataset( );
		dataset.setValue( "Backlogs" , new Double(us_total.getBacklogs()) );  
		dataset.setValue( "Defined" , new Double( us_total.getDefined() ) );   
		dataset.setValue( "In-Progress" , new Double( us_total.getIn_progress() ) );    
		dataset.setValue( "Completed" , new Double( us_total.getCompleted() ) );  
		dataset.setValue( "Accepted" , new Double( us_total.getAccepted() ) ); 
		JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, true);  	      
  	    int width = 640; /* Width of the image */
  	    int height = 480; /* Height of the image */ 
  	    File file1 = new File( img_path_us);   	      
  	    try {
  	    		PiePlot plot = (PiePlot) chart.getPlot();
  	    		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}[{1}]({2})"));  //0-name 1-count 2-percentage
  	    		plot.setSectionPaint("Backlogs", Color.RED);
  	    		plot.setSectionPaint("Defined", Color.CYAN);
  	    		plot.setSectionPaint("In-Progress", Color.BLUE);
  	    		plot.setSectionPaint("Completed",Color.YELLOW);
  	    		plot.setSectionPaint("Accepted", Color.GREEN);
  	    		final ChartRenderingInfo info = new ChartRenderingInfo( new StandardEntityCollection());
  	    		ChartUtilities.saveChartAsPNG(file1, chart, width, height, info);
  	    	}   catch (Exception e) {  }     
  	      
  	    
  	      
  	     //Defects
  	      
  	     title="Defects-"+statusType;	
  	     dataset = new DefaultPieDataset( );
  	     dataset.setValue( "Backlogs" , new Double(df_total.getBacklogs()) );  
  	     dataset.setValue( "Defined" , new Double( df_total.getDefined() ) );   
  	     dataset.setValue( "In-Progress" , new Double(df_total.getIn_progress() ) );    
  	     dataset.setValue( "Completed" , new Double( df_total.getCompleted() ) );  
  	     dataset.setValue( "Accepted" , new Double( df_total.getAccepted() ) ); 
  	     chart = ChartFactory.createPieChart(title, dataset, true, true, true);  	  	      
  	     width = 640; /* Width of the image */
  	  	 height = 480; /* Height of the image */ 
  	  	 file1 = new File( img_path_de); 
  	  	 try {
 	    		PiePlot plot = (PiePlot) chart.getPlot();
 	    		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}[{1}]({2})"));
 	    		plot.setSectionPaint("Backlogs", Color.RED);
 	    		plot.setSectionPaint("Defined", Color.CYAN);
 	    		plot.setSectionPaint("In-Progress", Color.BLUE);
 	    		plot.setSectionPaint("Completed",Color.YELLOW);
 	    		plot.setSectionPaint("Accepted", Color.GREEN);
 	    		final ChartRenderingInfo info = new ChartRenderingInfo( new StandardEntityCollection());
 	    		ChartUtilities.saveChartAsPNG(file1, chart, width, height, info);
 	    	}   catch (Exception e) {  }     
 	      
  	  	  
  	  	 //Test Case
  	  	      
  	  	 title="Test Case-"+statusType;	
  	  	 dataset = new DefaultPieDataset( );
  	  	 dataset.setValue( "Pass" , new Double(tc_total.getPass()) );  
  	  	 dataset.setValue( "Fail" , new Double( tc_total.getFail() ) );   
  	  	 dataset.setValue( "In-Progress" , new Double(tc_total.getIn_progress() ) );    
  	  	 dataset.setValue( "Blocked" , new Double( tc_total.getBlocked() ) );  
  	  	 dataset.setValue( "No-Run" , new Double( tc_total.getNo_run() ) );
  	  	 chart = ChartFactory.createPieChart(title, dataset, true, true, true);
  	  	 width = 640; /* Width of the image */
  	     height = 480; /* Height of the image */ 
  	  	 file1 = new File( img_path_tc); 
  	  	 try {
  	    		PiePlot plot = (PiePlot) chart.getPlot();
  	    		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}[{1}]({2})"));
  	    		plot.setSectionPaint("Pass", Color.GREEN);
  	    		plot.setSectionPaint("Fail", Color.RED);  	    		
  	    		plot.setSectionPaint("In-Progress", Color.BLUE);
  	    		plot.setSectionPaint("Blocked", Color.CYAN);
  	    		plot.setSectionPaint("No-Run",Color.YELLOW);
  	    		
  	    		final ChartRenderingInfo info = new ChartRenderingInfo( new StandardEntityCollection());
  	    		ChartUtilities.saveChartAsPNG(file1, chart, width, height, info);
  	    	 }  catch (Exception e) {  }     
  	        	  	 
	}
		
	public static void main(String arg[]) throws Exception
	{
		
		/*String imgpath=mydata_App_Data.get(0).get("Image Path");		
		
		DefaultPieDataset dataset = new DefaultPieDataset( );
	    dataset.setValue( "Backlogs" ,12);  
	    dataset.setValue( "Defined" , 10);   
	    dataset.setValue( "In-Progress" ,4 );    
	    dataset.setValue( "Completed" , 22);  
	    dataset.setValue( "Accepted" , 5 );  
		
		JFreeChart chart = ChartFactory.createPieChart("Test", dataset, true, true, true);
			try {
			    PiePlot plot = (PiePlot) chart.getPlot();
			    plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}({2})"));
			    plot.setSectionPaint("Backlogs", Color.RED);
			    plot.setSectionPaint("Defined", Color.CYAN);
			    plot.setSectionPaint("In-Progress", Color.BLUE);
			    plot.setSectionPaint("Completed",Color.YELLOW);
			    plot.setSectionPaint("Accepted", Color.GREEN);
			    final ChartRenderingInfo info = new ChartRenderingInfo( new StandardEntityCollection());
			    final File file1 = new File("C:\\Users\\ab71791\\Desktop\\Rally Output folder\\Chart5.png");
			    ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);

			} catch (Exception e) {
			    // log exception
			}*/
			
			System.out.println("success");
		UserStories us_total=new UserStories();
		Defects df_total=new Defects();
		TestCases tc_total=new TestCases();
		
		us_total.setAll(4, 6, 7, 9, 9, 10);
		df_total.setAll(4, 16, 7, 9, 19, 10);
		tc_total.setAll(4, 6, 17, 9, 9, 10,0,0,0);
		
		TeamStatus total=new TeamStatus();
		total.setAll(us_total, df_total, tc_total);
	
		
		DrawChart obj=new DrawChart("title");	
		obj.draw_Piechart(total,"Release");
	}
	
	public DrawChart(String title) 
	{
		super(title);
		
	}
}

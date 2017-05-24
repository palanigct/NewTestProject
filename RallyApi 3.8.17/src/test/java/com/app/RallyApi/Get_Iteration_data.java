package com.app.RallyApi;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.app.drawchart.DrawChart;
import com.app.excelread.Readfile;
import com.app.excelwrite.Excel_Write;
import com.app.pojos.Defects;
import com.app.pojos.TeamStatus;
import com.app.pojos.TestCases;
import com.app.pojos.UserStories;
import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;

import jnr.ffi.Struct.in_addr_t;

public class Get_Iteration_data 
{
	private static final String Static = null;
	public static Readfile read = new Readfile();
	public static Excel_Write write=new Excel_Write();
	public static List<HashMap<String,String>> mydata_App_Data = read.data("src/test/resources/INPUT.xls", "APP_DATA");
	public static Get_Iteration_data ite_obj=new Get_Iteration_data();
	public static TeamStatus teams_status_total=new TeamStatus();
	
	
	
	public void Exe_Iteration(ArrayList<String> team_list) throws Throwable
	{
		String sprint_number=mydata_App_Data.get(0).get("Sprint Number");
		String sprint_name="Sprint "+sprint_number;
		System.out.println("\n Sprint Number : "+sprint_number);
	     
		write.write_sprint_number(sprint_name);
		
		for(int i=0;i<team_list.size();i++)
		{
			System.out.println("======================="+team_list.get(i)+"========================");
			
			String team_name=team_list.get(i);
			ite_obj.get_Iteration_Status_details_for_team_and_sprint(team_name, sprint_name);
			
			System.out.println(team_name+"  success");	
		}
		//teams_status_total.displayAll();
		
		TestCases testcase=teams_status_total.getTestCases();	
		testcase=ite_obj.calculate_percent_testcase(testcase);
		teams_status_total.setTestCases(testcase);
		
		write.write_Iteration_Status(teams_status_total,"Sprint Total");	
		ite_obj.draw_pie_chart(sprint_name);
	}
	
	public static TeamStatus get_Iteration_Status_details_for_team_and_sprint(String team_name,String sprint_name) throws Exception
	{
		String type="";
		TeamStatus team_status=new TeamStatus();
		UserStories userstory=new UserStories();
		Defects defect= new Defects();
		TestCases testcase=new TestCases();
		
	    
		// get userstory values
		
	    type="userstory";	    
	    TeamStatus temp=ite_obj.callRestApi(team_name, sprint_name,type);
	    userstory=temp.getUserStories();	   
	    TestCases testcase1=temp.getTestCases(); 
	   
		// get defect values
		
		type="defects";		
		temp=ite_obj.callRestApi(team_name, sprint_name,type);	
		defect=temp.getDefects();		
		TestCases testcase2=temp.getTestCases();	
		testcase=ite_obj.addTwoTestCases(testcase1, testcase2);		
		testcase=ite_obj.calculate_percent_testcase(testcase);	
		
		team_status.setUserStories(userstory);
		team_status.setDefects(defect);			
		team_status.setTestCases(testcase);  
		
		//write to excel
		
		write.write_Iteration_Status(team_status,team_name);
		
		ite_obj.add_total(team_status);		
		return team_status;		
	}
		
	public TeamStatus callRestApi(String team_name,String sprint_name,String type) throws IOException, URISyntaxException
	{
		TeamStatus team_status=new TeamStatus();
		
		int total_count=0;		
		int backlogs=0;
		int defined=0;
		int in_progress=0;
		int completed=0;
		int accepted=0;
		
		int total_tc=0;
		int pass_tc=0;
		int fail_tc=0;
		int in_progress_tc=0;
		int blocked_tc=0;
		int no_run_tc=0;
		int per_exe=0;
		int per_pass=0;
		int per_fail=0;
		
		
  	    String iterationName	= sprint_name;
		String host 			= "https://rally1.rallydev.com";
        String username 		= "palanisamy.subramani@centurylink.com";
        String password 		= "Lalith@93";        
        String applicationName  = "Find Userstories and Defects by Iterations and Team";
        
        RallyRestApi restApi = null;
        String reqfor="";
        if(type.contains("userstory")) reqfor="HierarchicalRequirement";
        else reqfor="Defect";

     try {
             restApi = new RallyRestApi(new URI(host),username,password);
             restApi.setApplicationName(applicationName); 

             //System.out.println(restApi.getWsapiVersion()); 
             
             QueryRequest storyRequest = new QueryRequest(reqfor);
             //storyRequest.setFetch(new Fetch(new String[] {"Name", "FormattedID","ScheduleState"}));
             storyRequest.setPageSize(1);
             storyRequest.setLimit(1000);
             storyRequest.setScopedDown(false);
             storyRequest.setScopedUp(false);
             storyRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=",iterationName).and(new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
                    
             QueryResponse storyQueryResponse = restApi.query(storyRequest);
             total_count=storyQueryResponse.getTotalResultCount();
             
             for (int j=0; j<storyQueryResponse.getResults().size();j++)
             {
                 JsonObject storyJsonObject = storyQueryResponse.getResults().get(j).getAsJsonObject();
                                
                 int totaltc=storyJsonObject.get("TestCaseCount").getAsInt();
                 int passtc=storyJsonObject.get("PassingTestCaseCount").getAsInt();
                 String testCaseStatus=storyJsonObject.get("TestCaseStatus").getAsString(); 
                 
                 pass_tc+=passtc;
                 total_tc+=totaltc;              
                 
                 if(totaltc-passtc!=0)
                 {
                	 if(testCaseStatus.contains("NONE_RUN")){  no_run_tc += totaltc-passtc;	 }
                	 else if(testCaseStatus.contains("SOME_RUN_NONE_PASSING")){fail_tc += totaltc-passtc; }                	 
                	 else { in_progress_tc += totaltc-passtc;}
                 }
                	 
                 //ALL_RUN_ALL_PASSING  ALL_RUN_NONE_PASSING  NONE_RUN  SOME_RUN_NONE_PASSING   SOME_RUN_ALL_PASSING
                 
                 String state_temp=storyJsonObject.get("ScheduleState").getAsString();
                 String name=storyJsonObject.get("Name").getAsString();
                 String formId=storyJsonObject.get("FormattedID").getAsString();
                               
                
                 JsonObject releaseJson=null;
                 JsonObject sprintJson=null;
                 String releaseName="Null";
                 String sprintName="Null";
                		 
                 if(!(storyJsonObject.get("Release").toString().equals("null"))) 	
                 {
                	 releaseJson=(JsonObject) storyJsonObject.get("Release");                	
                	 releaseName=releaseJson.get("_refObjectName").getAsString();
                 }                
                 
                 if(!(storyJsonObject.get("Iteration").toString().equals("null"))) 	
                 {
                	 sprintJson=(JsonObject) storyJsonObject.get("Iteration");                	 
                	 sprintName=sprintJson.get("_refObjectName").getAsString();
                 }
                 
                 UserStories story=new UserStories();
                 story.setFormattedID(formId);
                 story.setName(name);
                 story.setStatus(state_temp);
                 story.setSprintname(sprintName);
                 story.setReleaseName(releaseName);
                 //story.setSprintOrRelease(sprintName);                 
               
                 write.write_userstoryAndDefect(story, team_name, "iteration", type);           
                
                 
                 if(state_temp.contains("Backlog")) 	backlogs++;
                 if(state_temp.contains("Defined")) 	defined++;
                 if(state_temp.contains("In-Progress")) in_progress++;
                 if(state_temp.contains("Completed")) 	completed++;
                 if(state_temp.contains("Accepted")) 	accepted++;
                 
              }	 
     
     	 }finally {  if (restApi != null) { 	   restApi.close();   }   }
		
    
    	 UserStories userstory_details=new UserStories();
    	 Defects defect_details=new Defects();
    	 TestCases testcase_details=new TestCases();
    	 
    	 userstory_details.setAll(backlogs, defined, in_progress, completed, accepted, total_count);
    	 defect_details.setAll(backlogs, defined, in_progress, completed, accepted, total_count);
    	 
    	 if(total_tc!=0)
    	 {
    		 double temp=0;
    		 temp=((double)(pass_tc+fail_tc)/(double)total_tc)*100;
    		 per_exe=(int) temp;
    		 temp=((double)pass_tc/(double)(pass_tc+fail_tc))*100; 
    		 per_pass=(int) temp;
    		 temp=((double)fail_tc/(double)(pass_tc+fail_tc))*100;  
    		 per_fail=(int) temp;
    	 }
    	 testcase_details.setAll(pass_tc, fail_tc, in_progress_tc, blocked_tc, no_run_tc, total_tc,per_exe,per_pass,per_fail);
    	 
    	 team_status.setAll(userstory_details, defect_details, testcase_details);
    	 return team_status;     
	
	}
	
	public static TestCases addTwoTestCases(TestCases testcase1,TestCases testcase2)
	{
		TestCases temp =new TestCases();	
		
		temp.setPass(testcase1.getPass()+testcase2.getPass());
		temp.setFail(testcase1.getFail()+testcase2.getFail());
		temp.setIn_progress(testcase1.getIn_progress()+testcase2.getIn_progress());
		temp.setBlocked(testcase1.getBlocked()+testcase2.getBlocked());
		temp.setNo_run(testcase1.getNo_run()+testcase2.getNo_run());
		temp.setTotal(testcase1.getTotal()+testcase2.getTotal());	
		
		
		return temp;
	}
		
	public static TeamStatus add_total(TeamStatus teams_status)
	{	
		int total_us=0;
		int Backlog_us=0;
		int Defined_us=0;
		int In_Progress_us=0;
		int Completed_us=0;
		int Accepted_us=0;
		
		int total_de=0;
		int Backlog_de=0;
		int Defined_de=0;
		int In_Progress_de=0;
		int Completed_de=0;
		int Accepted_de=0;
		
		int pass_tc=0;
		int fail_tc=0;
		int in_progress_tc=0;
		int blocked_tc=0;
		int no_run_tc=0;
		int total_tc=0;
		int per_exe_tc=0;
		int per_pass_tc=0;
		int per_fail_tc=0;
		
		
		    
		    
		    Backlog_us     = teams_status.getUserStories().getBacklogs()+teams_status_total.getUserStories().getBacklogs();  
		    Defined_us     = teams_status.getUserStories().getDefined()+teams_status_total.getUserStories().getDefined();  
		    In_Progress_us = teams_status.getUserStories().getIn_progress()+teams_status_total.getUserStories().getIn_progress();    
		    Completed_us   = teams_status.getUserStories().getCompleted()+teams_status_total.getUserStories().getCompleted(); 			  
		    Accepted_us    = teams_status.getUserStories().getAccepted()+teams_status_total.getUserStories().getAccepted();  			  
		    total_us       = teams_status.getUserStories().getTotal()+teams_status_total.getUserStories().getTotal();  
			  
		    Backlog_de     = teams_status.getDefects().getBacklogs()+teams_status_total.getDefects().getBacklogs();  
		    Defined_de     = teams_status.getDefects().getDefined()+teams_status_total.getDefects().getDefined();  
		    In_Progress_de = teams_status.getDefects().getIn_progress()+teams_status_total.getDefects().getIn_progress();    
		    Completed_de   = teams_status.getDefects().getCompleted()+teams_status_total.getDefects().getCompleted(); 			  
		    Accepted_de    = teams_status.getDefects().getAccepted()+teams_status_total.getDefects().getAccepted();  			  
		    total_de       = teams_status.getDefects().getTotal()+teams_status_total.getDefects().getTotal();  
			  		   
		    pass_tc        = teams_status.getTestCases().getPass()+teams_status_total.getTestCases().getPass();
		    fail_tc        = teams_status.getTestCases().getFail()+teams_status_total.getTestCases().getFail();
		    in_progress_tc = teams_status.getTestCases().getIn_progress()+teams_status_total.getTestCases().getIn_progress();
		    blocked_tc     = teams_status.getTestCases().getBlocked()+teams_status_total.getTestCases().getBlocked();
		    no_run_tc      = teams_status.getTestCases().getNo_run()+teams_status_total.getTestCases().getNo_run();
		    total_tc       = teams_status.getTestCases().getTotal()+teams_status_total.getTestCases().getTotal();
		    
		   		    
		    UserStories userstory_total=new UserStories();
			Defects defect_total= new Defects();
			TestCases testcase= new TestCases();
			
			userstory_total.setAll(Backlog_us, Defined_us, In_Progress_us, Completed_us, Accepted_us, total_us);
			defect_total.setAll(Backlog_de, Defined_de, In_Progress_de, Completed_de, Accepted_de, total_de);
			testcase.setAll(pass_tc, fail_tc, in_progress_tc, blocked_tc, no_run_tc, total_tc,per_exe_tc,per_pass_tc,per_fail_tc);
						
			teams_status_total.setUserStories(userstory_total);
			teams_status_total.setDefects(defect_total);
			teams_status_total.setTestCases(testcase);		    
		
			
		return teams_status_total;
		
	}
	
	public static void draw_pie_chart(String sprint_name) throws Throwable
	{
		DrawChart drawchart=new DrawChart("");
		drawchart.draw_Piechart(teams_status_total,sprint_name);
	}
	
	public static TestCases calculate_percent_testcase(TestCases testcase)
	{
		
		int total_tc=testcase.getTotal();
		int pass_tc=testcase.getPass();
		int fail_tc=testcase.getFail();
		int in_progress_tc=testcase.getIn_progress();
		int blocked_tc=testcase.getBlocked();
		int no_run_tc=testcase.getNo_run();
		int per_exe=0;
		int per_pass=0;
		int per_fail=0;
		
		int executed_tc=pass_tc+fail_tc;
		int non_executed_tc=total_tc-executed_tc;
		
		double temp=((double)executed_tc/(double)total_tc)*100;
		per_exe=(int)temp;
		
		temp=((double)pass_tc/(double)executed_tc)*100;
		per_pass=(int)temp;
		
		temp=((double)fail_tc/(double)executed_tc)*100;
		per_fail=(int)temp;	
		
		//testcase.setAll(pass_tc, fail_tc, in_progress_tc, blocked_tc, no_run_tc, total_tc, per_exe, per_pass, per_fail);
		
		testcase.setPercentage_execute(per_exe);
		testcase.setPercentage_pass(per_pass);
		testcase.setPercentage_fail(per_fail);
		
		return testcase;
	}
	
	
//================================================= not use =====  testing purpose only ================================	
	
	
	public static void main(String arg[]) throws Exception
	{
		Get_Iteration_data obj=new Get_Iteration_data();
		obj.get_Iteration_Status_details_for_team_and_sprint("SDWAN Kenobi", "Sprint 229");
	}
	
	public static TestCases addTestCases(TestCases testcase)
	{
		TestCases temp =new TestCases();	
		TestCases add_testcases=new TestCases();  //its need to declare on  global
		
		temp.setPass(testcase.getPass()+add_testcases.getPass());
		temp.setFail(testcase.getFail()+add_testcases.getFail());
		temp.setIn_progress(testcase.getIn_progress()+add_testcases.getIn_progress());
		temp.setBlocked(testcase.getBlocked()+add_testcases.getBlocked());
		temp.setNo_run(testcase.getNo_run()+add_testcases.getNo_run());
		temp.setTotal(testcase.getTotal()+add_testcases.getTotal());	
		
		add_testcases.setTestCase(temp);
		
		return temp;
	}
	
	
	
}

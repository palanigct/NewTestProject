package com.app.RallyApi;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Set;



import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;


/*
<dependency>
	<groupId>org.json</groupId>
<artifactId>json</artifactId>
<version>20090211</version>
</dependency>
*/


public class test {
	

	public static void main(String[] args) throws IOException, URISyntaxException 
	{
			test tst=new test();
			
			System.out.println("*************************user story*******************************");
			//test.userstoryreq();
			System.out.println("*************************test case*******************************");
			//test.testcasereq();
			System.out.println("*************************Iteration*******************************");
			//test.iterationreq();
			System.out.println("*************************Release*******************************");
			//test.releasereq();
			System.out.println("*************************test *******************************");
			test.testreq();
			
	}    
	
	public static void userstoryreq() throws IOException, URISyntaxException
	{
		String iterationName	= "Sprint 220";
		String releaseName      = "PI10 2017.04";
	    String team_name        = "Mikasa";
	    
		String host 			= "https://rally1.rallydev.com";
        String username 		= "palanisamy.subramani@centurylink.com";
        String password 		= "Lalith@93";        
        String applicationName  = "Find Userstories and Defects by Iterations and Team";
       
       
        
        RallyRestApi restApi = null;
        try {
        	restApi = new RallyRestApi(new URI(host),username,password);
            restApi.setApplicationName(applicationName); 

            QueryRequest userstoryRequest = new QueryRequest("HierarchicalRequirement"); 
            userstoryRequest.setQueryFilter(new QueryFilter("Release.Name", "=", releaseName).and(new QueryFilter("Project.Name", "=", team_name)));  //new QueryFilter("Project.Name", "=", team_name).and
            
            //userstoryRequest.setQueryFilter(new QueryFilter("Release.Name", "=", releaseName).and(new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
        

            QueryResponse userstoryResponse = restApi.query(userstoryRequest);
            System.out.println("Successful: " + userstoryResponse.wasSuccessful());
            System.out.println("US Size: " + userstoryResponse.getTotalResultCount());
           
            for (int i=0; i<userstoryResponse.getResults().size()&&i<1;i++)
            {
                JsonObject userstoryJsonObject = userstoryResponse.getResults().get(i).getAsJsonObject();
                 
                Object fmid=userstoryJsonObject.get("FormattedID");   // "FormattedID","ScheduleState"
                Object stat=userstoryJsonObject.get("ScheduleState");
                Object obid=userstoryJsonObject.get("ObjectID");
                Object tccount=userstoryJsonObject.get("TestCaseCount");
                Object passTc=userstoryJsonObject.get("PassingTestCaseCount");
                Object testCaseStatus=userstoryJsonObject.get("TestCaseStatus"); 
                JsonObject testcasejson = (JsonObject) userstoryJsonObject.get("TestCases");   
                JsonObject workspacejson = (JsonObject) userstoryJsonObject.get("Workspace");                
                Object workref=workspacejson.get("_ref");
               
                String work_ref=workref.toString();
                String objid=obid.toString();
                
                JsonObject release=(JsonObject) userstoryJsonObject.get("Release");
                JsonObject sprint=null;//(JsonObject) userstoryJsonObject.get("Iteration");
                Object obj=userstoryJsonObject.get("Iteration");
                String str1=obj.toString();
               
                if(userstoryJsonObject.get("Iteration").toString().equals("null"))
                {
                	System.out.println("---nulll---");
                	//sprint=(JsonObject) userstoryJsonObject.get("Iteration");
                }
                else
                {
                	System.out.println("-------not null------");
                }
                
                Object relName=release.get("_refObjectName");
                Object sprName="";//sprint.get("_refObjectName");
                
                System.out.println(release+ " \n"+sprint);
                System.out.println("form id : " +fmid+" state : "+ stat+" rele Name : "+relName+ " spr name : "+sprName+ " obj "+obj);
                System.out.println(" obj id : "+obid+" totalcount : "+ tccount +" pass : "+passTc +" test status : "+testCaseStatus);
                
                
                
                 	 if(i==2)
                     {
                     	JsonObject applicationjson = (JsonObject) userstoryJsonObject.get("c_Application"); 
                     	//System.out.println(applicationjson.get("_tagsNameArray"));
                     	JsonArray applicationList= new JsonArray();
                     	applicationList=applicationjson.get("_tagsNameArray").getAsJsonArray();
                     	JsonObject ele=(JsonObject) applicationList.get(0);                	
                     	
                     	System.out.println(ele.get("Name"));
                     	System.out.println(" US===form id : " +fmid+" state : "+ stat);
                     	
                     	
                     	Set<?> key=userstoryJsonObject.entrySet();
                         Iterator itr=key.iterator();
                         while(itr.hasNext())
                         {
                         	String str=itr.next().toString();	                
                         	System.out.println("str : "+str);
                         }
                     }
                     
                	 
                	 
                	 
                 	
                 
                
                /*
                System.out.println("work ref : "+workref);
                // System.out.println("obid:TC:PTC"+obid+":"+tccount+":"+passTc+"\n\n"+testcasejson);
                
                QueryRequest testcaseRequest = new QueryRequest("TestCase");
                testcaseRequest.setWorkspace(work_ref);
                testcaseRequest.setQueryFilter(new QueryFilter("Project.Name", "=", team_name));// .and(new QueryFilter("ObjectID", "=" ,objid))
                QueryResponse testcaseQueryResponse = restApi.query(testcaseRequest);
	            System.out.println("Successful  tc:  " + testcaseQueryResponse.wasSuccessful());
	            System.out.println("Size: " + testcaseQueryResponse.getTotalResultCount());   
	            */
	            /*for (int j=0; j<testcaseQueryResponse.getResults().size()&&j<10;j++)
	            {
	            	JsonObject testcaseJsonObject = testcaseQueryResponse.getResults().get(i).getAsJsonObject();
	            	 Object tcobid=testcaseJsonObject.get("ObjectID");
	            	 System.out.println(tcobid);
	            	
	            }
	            */
	          
                
                /*JsonObject testcase=(JsonObject) userstoryJsonObject.get("TestCases");
               
                
                Object ref=userstoryJsonObject.get("ObjectID");
                System.out.println("testcases"+ testcase);
                String refs=ref.toString();
                System.out.println("ObjectID "+ref+"\n\n");
                
                
                QueryRequest testcaseRequest = new QueryRequest("TestCase");
                //testcaseRequest.setQueryFilter((new QueryFilter(" ref", "=", refs)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
                //testSetRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=", iterationName).and(new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
                //testcaseRequest.setWorkspace(refs);
              
                testcaseRequest.setFetch(new Fetch("FormattedID"));	                
                testcaseRequest.setQueryFilter(new QueryFilter("ObjectID", "=", "56503916587"));
                
                QueryResponse testcaseQueryResponse = restApi.query(testcaseRequest);
	            System.out.println("Successful  tc:  " + testcaseQueryResponse.wasSuccessful());
	            System.out.println("Size: " + testcaseQueryResponse.getTotalResultCount());               
               */
                
            }

        } finally {
            if (restApi != null) {
                restApi.close();
            }
        }
	}
	
	public static void testcasereq() throws IOException, URISyntaxException
	{
		String iterationName	= "Sprint 219";
	    String team_name        = "SDWAN Kenobi";
	    
		String host 			= "https://rally1.rallydev.com";
        String username 		= "palanisamy.subramani@centurylink.com";
        String password 		= "Lalith@93";        
        String applicationName = "Find Userstories and Defects by Iterations and Team";
       
       
        
        RallyRestApi restApi = null;
        try {
        	restApi = new RallyRestApi(new URI(host),username,password);
            restApi.setApplicationName(applicationName); 

            QueryRequest testcaseRequest = new QueryRequest("TestCase");           
            //userstoryRequest.setQueryFilter(new QueryFilter("Project.Name", "=", team_name).and(new QueryFilter("Project.Name", "=", team_name)));
            testcaseRequest.setQueryFilter(new QueryFilter("Project.Name", "=", team_name));  //new QueryFilter("Iteration.Name", "=", iterationName).and
         
            //  https://rally1.rallydev.com/slm/webservice/v2.0/testcase/62489186472    new QueryFilter("Project.Name", "=", team_name).and new QueryFilter("_ref","=","https://rally1.rallydev.com/slm/webservice/v2.0/testcase/62489186472")

            QueryResponse testcaseResponse = restApi.query(testcaseRequest);
            System.out.println("Successful: " + testcaseResponse.wasSuccessful());
            System.out.println("TC Size: " + testcaseResponse.getTotalResultCount());
           
            for (int i=0; i<testcaseResponse.getResults().size()&&i<1;i++)
            {
                JsonObject testcaseJsonObject = testcaseResponse.getResults().get(i).getAsJsonObject();
                
                Object ref=testcaseJsonObject.get("ObjectID");
                Object lastVerdict =testcaseJsonObject.get("LastVerdict");
                
                System.out.println(lastVerdict);
                
                Set<?> key=testcaseJsonObject.entrySet();
                Iterator itr=key.iterator();
                while(itr.hasNext())
                {
                	String str=itr.next().toString();	                
                	System.out.println("str : "+str);  
                }
                
            }

        } finally {
            if (restApi != null) {
                restApi.close();
            }
        }
	}
			
	public static void iterationreq() throws IOException, URISyntaxException
	{
		String iterationName	= "Sprint 220";
		String releaseName      = "PI10 2017.04";
	    String team_name        = "Mikasa";
	    
		String host 			= "https://rally1.rallydev.com";
        String username 		= "palanisamy.subramani@centurylink.com";
        String password 		= "Lalith@93";        
        String applicationName  = "Find Userstories and Defects by Iterations and Team";
       
       
        
        RallyRestApi restApi = null;
        try {
        	restApi = new RallyRestApi(new URI(host),username,password);
            restApi.setApplicationName(applicationName); 

            QueryRequest iterationRequest = new QueryRequest("Iteration"); 
            //iterationRequest.setQueryFilter(new QueryFilter("Release.Name", "=", releaseName).and(new QueryFilter("Project.Name", "=", team_name)));  //new QueryFilter("Project.Name", "=", team_name).and            
            //userstoryRequest.setQueryFilter(new QueryFilter("Release.Name", "=", releaseName).and(new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
        

            QueryResponse iterationResponse = restApi.query(iterationRequest);
            System.out.println("Successful: " + iterationResponse.wasSuccessful());
            System.out.println("iteration Size: " + iterationResponse.getTotalResultCount());
           
            for (int i=0; i<iterationResponse.getResults().size()&&i<1;i++)
            {
                JsonObject userstoryJsonObject = iterationResponse.getResults().get(i).getAsJsonObject();
               
                 Set<?> key=userstoryJsonObject.entrySet();
                 Iterator itr=key.iterator();
                 while(itr.hasNext())
                 {
                 	String str=itr.next().toString();	                
                 	System.out.println("str : "+str);
                 }
                    
            }

        } finally {
            if (restApi != null) {
                restApi.close();
            }
        }
	}
	
	public static void releasereq() throws IOException, URISyntaxException
	{
		String iterationName	= "Sprint 220";
		String releaseName      = "PI10 2017.04";
	    String team_name        = "Mikasa";
	    
		String host 			= "https://rally1.rallydev.com";
        String username 		= "palanisamy.subramani@centurylink.com";
        String password 		= "Lalith@93";        
        String applicationName  = "Find Userstories and Defects by Iterations and Team";
       
       
        
        RallyRestApi restApi = null;
        try {
        	restApi = new RallyRestApi(new URI(host),username,password);
            restApi.setApplicationName(applicationName); 

            QueryRequest iterationRequest = new QueryRequest("Release"); 
            //iterationRequest.setQueryFilter(new QueryFilter("Release.Name", "=", releaseName).and(new QueryFilter("Project.Name", "=", team_name)));  //new QueryFilter("Project.Name", "=", team_name).and            
            //userstoryRequest.setQueryFilter(new QueryFilter("Release.Name", "=", releaseName).and(new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
        

            QueryResponse iterationResponse = restApi.query(iterationRequest);
            System.out.println("Successful: " + iterationResponse.wasSuccessful());
            System.out.println("release Size: " + iterationResponse.getTotalResultCount());
           
            for (int i=0; i<iterationResponse.getResults().size()&&i<1;i++)
            {
                JsonObject userstoryJsonObject = iterationResponse.getResults().get(i).getAsJsonObject();
               
                 Set<?> key=userstoryJsonObject.entrySet();
                 Iterator itr=key.iterator();
                 while(itr.hasNext())
                 {
                 	String str=itr.next().toString();	                
                 	System.out.println("str : "+str);
                 }
                    
            }

        } finally {
            if (restApi != null) {
                restApi.close();
            }
        }
	}
	
	public static void testreq() throws IOException, URISyntaxException
	{
		String iterationName	= "Sprint 220";
		String releaseName      = "PI10 2017.04";
	    String team_name        = "Mikasa";
	    
		String host 			= "https://rally1.rallydev.com";
        String username 		= "palanisamy.subramani@centurylink.com";
        String password 		= "Lalith@93";        
        String applicationName  = "Find Userstories and Defects by Iterations and Team";
       
       
        
        RallyRestApi restApi = null;
        try {
        	restApi = new RallyRestApi(new URI(host),username,password);
            restApi.setApplicationName(applicationName); 
           
            QueryRequest testRequest = new QueryRequest("TestSets");  //Releases , Iterations , Project ,  HierarchicalRequirement , TestCases , Defects , 
            testRequest.setQueryFilter((new QueryFilter("Project.Name", "=", team_name)));  //new QueryFilter("Project.Name", "=", team_name).and            
            //testRequest.setQueryFilter(new QueryFilter("Release.Name", "=", releaseName).and(new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
        

            QueryResponse testResponse = restApi.query(testRequest);
            System.out.println("Successful: " + testResponse.wasSuccessful());
            System.out.println("responce Size: " + testResponse.getTotalResultCount());
           
            for (int i=0; i<testResponse.getResults().size()&&i<1;i++)
            {
                JsonObject userstoryJsonObject = testResponse.getResults().get(i).getAsJsonObject();
               
                 Set<?> key=userstoryJsonObject.entrySet();
                 Iterator itr=key.iterator();
                 while(itr.hasNext())
                 {
                 	String str=itr.next().toString();	                
                 	System.out.println("str : "+str);
                 }
                    
            }

        } finally {
            if (restApi != null) {
                restApi.close();
            }
        }
	}
	
	
	//obj id : 63562622913	
	//ObjectID=63267832232
	
	//_refObjectUUID="5af00ef8-672e-419a-b576-7581355fa457"
	//_refObjectUUID="a28f3e65-bc8d-469e-8a32-2cec8465623b"
	
	//ObjectUUID="a28f3e65-bc8d-469e-8a32-2cec8465623b"
	//ObjectUUID="5af00ef8-672e-419a-b576-7581355fa457"
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	        //=============================================================================================================
	        /*
	        RallyRestApi restApi = null;
	       // String reqfor="HierarchicalRequirement";	        
	        String reqfor="TestCase";	 
	        System.out.println("strated\n");
	        
	        
	        restApi = new RallyRestApi(new URI(host),username,password);
            restApi.setApplicationName(applicationName);
            System.out.println(restApi.getWsapiVersion()); 
	        
	        try {
	             restApi = new RallyRestApi(new URI(host),username,password);
	             restApi.setApplicationName(applicationName); 

	             //System.out.println(restApi.getWsapiVersion()); 
	             
	             QueryRequest storyRequest = new QueryRequest(reqfor);
	             storyRequest.setFetch(new Fetch(new String[] {"Name", "FormattedID","ScheduleState","TestSet","LastVerdict","Verdict"}));
	             storyRequest.setPageSize(10);
	             storyRequest.setLimit(10);
	             storyRequest.setScopedDown(false);
	             storyRequest.setScopedUp(false);
	             storyRequest.setQueryFilter((new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
                   
	             QueryResponse storyQueryResponse = restApi.query(storyRequest);
	             total_count=storyQueryResponse.getTotalResultCount();
	             System.out.println("query result count: " + iterationName + " :" + storyQueryResponse.getTotalResultCount());
	             
	             for (int j=0; j<storyQueryResponse.getResults().size();j++)
	             {
	                 JsonObject storyJsonObject = storyQueryResponse.getResults().get(j).getAsJsonObject();
	                 //String state_temp=storyJsonObject.get("ScheduleState").getAsString();	                 
	                 
	                 System.out.println( "FormattedID: " + storyJsonObject.get("FormattedID") + " LastVerdict: " + storyJsonObject.get("LastVerdict")+ " TestSet: " + storyJsonObject.get("TestSet")); //"Name: " + storyJsonObject.get("Name") +
	                 //System.out.println( "testcase: " + storyJsonObject.get("TestCase") + " Verdict: " + storyJsonObject.get("LastVerdict")); //"Name: " + storyJsonObject.get("Name") +
	 	            
	             }	 
	     
	     } catch (URISyntaxException e)
	     {
			
			e.printStackTrace();
		 }finally {  if (restApi != null) { 	   restApi.close();   } System.out.println("\ncompleted");   }
	
	        
*/	        //==========================================================================================
	/*        
	        
	        RallyRestApi restApi = null;
	        //String reqfor="HierarchicalRequirement";	        
	        String reqfor="testcase";	 
	        System.out.println("strated\n");
	        
	        
	        restApi = new RallyRestApi(new URI(host),username,password);
            restApi.setApplicationName(applicationName);
            System.out.println(restApi.getWsapiVersion()); 
            
	        QueryRequest testCaseRequest = new QueryRequest("TestCase");
	        testCaseRequest.setFetch(new Fetch("Build","TestCase","TestSet", "Verdict","FormattedID"));
	        testCaseRequest.setQueryFilter(new QueryFilter("Project.Name", "=", team_name));   //.and(new QueryFilter("ScheduleState", "=", "In-Progress")
            
	        QueryResponse testCaseResponse = restApi.query(testCaseRequest);
	        int numberTestCases = testCaseResponse.getTotalResultCount();
	        System.out.println("tc count = "+numberTestCases);
	        
	        for (int j=0; j<testCaseResponse.getResults().size()&&j<5;j++)
            {
                JsonObject storyJsonObject = testCaseResponse.getResults().get(j).getAsJsonObject(); 
                String frid=storyJsonObject.get("FormattedID").getAsString();
                System.out.println( j+"  == FormattedID: " +frid+ " Verdict: " + storyJsonObject.get("Verdict")); //"Name: " + storyJsonObject.get("Name") +
               
                
                QueryRequest testCaseResultRequest = new QueryRequest("TestCaseResult");
    	        testCaseResultRequest.setFetch(new Fetch("Build","TestCase","TestSet", "Verdict","FormattedID","Iteration.Name"));
    	        
    	        
    	        testCaseResultRequest.setQueryFilter(new QueryFilter("TestCase.FormattedID", "=",frid));
    	        QueryResponse testCaseResultResponse = restApi.query(testCaseResultRequest);
    	        int numberTestCaseResults = testCaseResultResponse.getTotalResultCount();
    	        
    	        System.out.println("TCR "+numberTestCaseResults);
    	        if(numberTestCaseResults >0)
    	        {
    	            System.out.println("state "+testCaseResultResponse.getResults().get(0).getAsJsonObject().get("Verdict").getAsString());
    	        	System.out.println("state "+testCaseResultResponse.getResults().get(0).getAsJsonObject().get("Iteration.Name").getAsString());
    	        }
    	        	else
    	            System.out.println("Not executed");
            }	 
    
	        
	        System.out.println("\ncompleted");
	   
	  */      
	    //====================================================================================================    
	     /*try {
	             restApi = new RallyRestApi(new URI(host),username,password);
	             restApi.setApplicationName(applicationName); 

	             //System.out.println(restApi.getWsapiVersion()); 
	             
	             QueryRequest storyRequest = new QueryRequest(reqfor);
	             storyRequest.setFetch(new Fetch(new String[] {"Name", "FormattedID","ScheduleState"}));
	             storyRequest.setPageSize(1);
	             storyRequest.setLimit(1000);
	             storyRequest.setScopedDown(false);
	             storyRequest.setScopedUp(false);
	             storyRequest.setQueryFilter(new QueryFilter("Iteration.Name", "=", iterationName).and(new QueryFilter("Project.Name", "=", team_name)));//.and(new QueryFilter("ScheduleState", "=", "In-Progress")
                    
	             QueryResponse storyQueryResponse = restApi.query(storyRequest);
	             total_count=storyQueryResponse.getTotalResultCount();
	             System.out.println("query result count: " + iterationName + " :" + storyQueryResponse.getTotalResultCount());
	             
	             for (int j=0; j<storyQueryResponse.getResults().size();j++)
	             {
	                 JsonObject storyJsonObject = storyQueryResponse.getResults().get(j).getAsJsonObject();
	                 String state_temp=storyJsonObject.get("ScheduleState").getAsString();
	                 
	                 if(state_temp.contains("Backlog")) 	backlogs++;
	                 if(state_temp.contains("Defined")) 	defined++;
	                 if(state_temp.contains("In-Progress")) in_progress++;
	                 if(state_temp.contains("Completed")) 	completed++;
	                 if(state_temp.contains("Accepted")) 	accepted++;
	                 
	                 System.out.println( "FormattedID: " + storyJsonObject.get("FormattedID") + " ScheduleState: " + storyJsonObject.get("ScheduleState")); //"Name: " + storyJsonObject.get("Name") +
	             }	 
	     
	     } catch (URISyntaxException e)
	     {
			
			e.printStackTrace();
		 }finally {  if (restApi != null) { 	   restApi.close();   } System.out.println("\ncompleted");   }
	
	     */
	     
	     
	     
	
	
	

}

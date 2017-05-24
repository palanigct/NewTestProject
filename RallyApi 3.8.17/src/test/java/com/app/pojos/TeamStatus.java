package com.app.pojos;

public class TeamStatus
{	
	UserStories userstories=new UserStories();
	Defects defects=new Defects();
	TestCases testcases=new TestCases();
	
	public void setAll(UserStories userstories,Defects defects,TestCases testcases)
	{
		this.userstories=userstories;
		this.defects=defects;
		this.testcases=testcases;		
	}
   
	public UserStories getUserStories()
	{
		return userstories;
	}
	public void setUserStories(UserStories userstories)
	{
		this.userstories=userstories;
	}
	
	public Defects getDefects()
	{
		return defects;
	}
	public void setDefects(Defects defects)
	{
		this.defects=defects;
	}

	public TestCases getTestCases()
	{
		return testcases;
	}
	public void setTestCases(TestCases testcases)
	{
		this.testcases=testcases;
	}
	
	public void displayAll()
	{		
		System.out.println("User story  == defi "+userstories.getDefined()+" || back "+userstories.getBacklogs()+" || in-prog "+userstories.getIn_progress()+" || comp "+userstories.getCompleted()+" || accep "+userstories.getAccepted()+" || tota "+userstories.getTotal());
		System.out.println("Defects == defi "+defects.getDefined()+" || back "+defects.getBacklogs()+" || in-prog "+defects.getIn_progress()+" || comp "+defects.getCompleted()+" || accp "+defects.getAccepted()+" || tota "+defects.getTotal());
		System.out.println("Testcases == pass "+testcases.getPass()+" || fail "+testcases.getFail()+" || in-prog "+testcases.getIn_progress()+" || blog "+testcases.getBlocked()+" || no-run "+testcases.getNo_run()+" || tota "+testcases.getTotal());
	}
}

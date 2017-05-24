package com.app.pojos;

public class TestCases
{
  private int pass;
  private int fail;
  private int in_progress;
  private int blocked;
  private int no_run;
  private int total;
  private int percentage_execute;
  private int percentage_pass;
  private int percentage_fail;
  
  public void setAll( int pass, int fail,int in_progress,int blocked,int no_run,int total,int percentage_execute,int percentage_pass,int percentage_fail)
  {
	  this.pass=pass;
	  this.fail=fail;
	  this.in_progress=in_progress;
	  this.blocked=blocked;
	  this.no_run=no_run;
	  this.total=total;
	  this.percentage_execute=percentage_execute;
	  this.percentage_pass=percentage_pass;
	  this.percentage_fail=percentage_fail;
  }
  
  
public int getPercentage_execute() {
	return percentage_execute;
}


public void setPercentage_execute(int percentage_execute) {
	this.percentage_execute = percentage_execute;
}


public int getPercentage_pass() {
	return percentage_pass;
}


public void setPercentage_pass(int percentage_pass) {
	this.percentage_pass = percentage_pass;
}


public int getPercentage_fail() {
	return percentage_fail;
}


public void setPercentage_fail(int percentage_fail) {
	this.percentage_fail = percentage_fail;
}


public int getPass() {
	return pass;
}
public void setPass(int pass) {
	this.pass = pass;
}
public int getFail() {
	return fail;
}
public void setFail(int fail) {
	this.fail = fail;
}
public int getIn_progress() {
	return in_progress;
}
public void setIn_progress(int in_progress) {
	this.in_progress = in_progress;
}
public int getBlocked() {
	return blocked;
}


public void setBlocked(int blocked) {
	this.blocked = blocked;
}


public int getNo_run() {
	return no_run;
}
public void setNo_run(int no_run) {
	this.no_run = no_run;
}
public int getTotal() {
	return total;
}
public void setTotal(int total) {
	this.total = total;
}

public void setTestCase(TestCases testcase)
{
	this.pass=testcase.pass;
	this.fail=testcase.fail;
	this.in_progress=testcase.in_progress;
	this.blocked=testcase.blocked;
	this.no_run=testcase.no_run;
	this.percentage_execute=testcase.percentage_execute;
	this.percentage_pass=testcase.percentage_pass;
	this.percentage_fail=testcase.percentage_fail;
}

public void displayAll()
{
	System.out.println("pass : "+pass);
	System.out.println("fail  : "+fail);
	System.out.println("in-progress: "+in_progress);
	System.out.println("blocked : "+blocked);
	System.out.println("no-run : "+no_run);
	System.out.println("total : "+total);
	System.out.println("per_exe : "+percentage_execute);
	System.out.println("per_pass : "+percentage_pass);
	System.out.println("per_fail : "+percentage_fail);
}
  
}

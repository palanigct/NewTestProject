package com.app.pojos;

public class UserStories 
{
	private int backlogs;
	private int defined;
	private int in_progress;
	private int completed;
	private int accepted;
	private int total;
	private String name;
	private String formattedID;
	private String status;
	private String sprintOrRelease;
	
	public String getSprintOrRelease() {
		return sprintOrRelease;
	}

	public void setSprintOrRelease(String sprintOrRelease) {
		this.sprintOrRelease = sprintOrRelease;
	}

	private String sprintname;
	private String releaseName;
	
	public String getSprintname() {
		return sprintname;
	}

	public void setSprintname(String sprintname) {
		this.sprintname = sprintname;
	}

	public String getReleaseName() {
		return releaseName;
	}

	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}

	public String getFormattedID() {
		return formattedID;
	}

	public void setFormattedID(String formattedID) {
		this.formattedID = formattedID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAll(int backlogs,int defined,int in_progress,int completed, int accepted, int total)
	{
		this.backlogs=backlogs;
		this.defined=defined;
		this.in_progress=in_progress;
		this.completed=completed;
		this.accepted=accepted;
		this.total=total;
	}	
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getBacklogs() {
		return backlogs;
	}
	public void setBacklogs(int backlogs) {
		this.backlogs = backlogs;
	}
	public int getDefined() {
		return defined;
	}
	public void setDefined(int defined) {
		this.defined = defined;
	}
	public int getIn_progress() {
		return in_progress;
	}
	public void setIn_progress(int in_progress) {
		this.in_progress = in_progress;
	}
	public int getCompleted() {
		return completed;
	}
	public void setCompleted(int completed) {
		this.completed = completed;
	}
	public int getAccepted() {
		return accepted;
	}
	public void setAccepted(int accepted) {
		this.accepted = accepted;
	}
	
	public void displayAll()
	{
		System.out.println("backlogs : "+backlogs);
		System.out.println("defined  : "+defined);
		System.out.println("in-progress: "+in_progress);
		System.out.println("completed : "+completed);
		System.out.println("accepted : "+accepted);
		System.out.println("total : "+total);
	}
}

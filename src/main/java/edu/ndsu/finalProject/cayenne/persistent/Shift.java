package edu.ndsu.finalProject.cayenne.persistent;

public class Shift {
	private int PK;
	private String instructorName;
	private String lessonDateTime;
	private String lessonDescription;
	private String courseName;
	
	public Shift(int key, String name, String datetime, String lessonDesc, String course)
	{
		PK = key;
		instructorName = name;
		lessonDateTime = datetime;
		lessonDescription = lessonDesc;
		courseName = course;
	}
	
	public String getInstructorName()
	{
		return instructorName;
	}
	
	public String getLessonDateTime()
	{
		return lessonDateTime;
	}
	
	public String getLessonDescription()
	{
		return lessonDescription;
	}
	
	public String courseName()
	{
		return courseName;
	}
	
	public int getPK()
	{
		return PK;
	}
}

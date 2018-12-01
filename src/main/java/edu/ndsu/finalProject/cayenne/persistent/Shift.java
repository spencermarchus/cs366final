package edu.ndsu.finalProject.cayenne.persistent;

public class Shift {
	private String instructorName;
	private String lessonDateTime;
	private String lessonDescription;
	private String courseName;
	
	public Shift(String name, String datetime, String lessonDesc, String course)
	{
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
}

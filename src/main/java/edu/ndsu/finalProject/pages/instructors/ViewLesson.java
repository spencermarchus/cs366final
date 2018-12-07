package edu.ndsu.finalProject.pages.instructors;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.tynamo.security.services.SecurityService;

import edu.ndsu.finalProject.cayenne.persistent.Guardian;
import edu.ndsu.finalProject.cayenne.persistent.Lesson;
import edu.ndsu.finalProject.cayenne.persistent.LessonDate;
import edu.ndsu.finalProject.cayenne.persistent.UserAccount;
import edu.ndsu.finalProject.services.DatabaseService;
import edu.ndsu.finalProject.services.UserAccountService;

@RequiresRoles("instructor")
public class ViewLesson 
{
	@Inject
	SecurityService securityService;
	
	@Inject
	UserAccountService userAccountService;
	
	@Inject
	DatabaseService db;
	
	@Property
	private String PK;
	
	@Property
	@Persist
	Lesson lesson;
	
	@Property
	List<LessonDate> lessonDates;
	
	@Property
	LessonDate lessonDate;
	
	@Property
	String courseLocation;
	
	void setupRender()
	{
		lesson = db.getLessonByPK(null, Integer.parseInt(PK));
		lessonDates = lesson.getLessonDates();
		courseLocation = lesson.getCourse().getName();
	}
	
	String onPassivate()
	{
		return String.valueOf(PK);
	}
	
	public void onActivate(int pK) {
		PK = String.valueOf(pK);
	}
}



package edu.ndsu.finalProject.pages.instructors;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.tynamo.security.services.SecurityService;

import edu.ndsu.finalProject.cayenne.persistent.Guardian;
import edu.ndsu.finalProject.cayenne.persistent.Instructor;
import edu.ndsu.finalProject.cayenne.persistent.Lesson;
import edu.ndsu.finalProject.cayenne.persistent.LessonDate;
import edu.ndsu.finalProject.cayenne.persistent.UserAccount;
import edu.ndsu.finalProject.services.DatabaseService;
import edu.ndsu.finalProject.services.UserAccountService;

@RequiresAuthentication
public class ViewLessonDate 
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
	List<Instructor> instructors;
	
	@Property
	Instructor instructor;
	
	@Property
	LessonDate lessonDate;
	
	@Property
	String courseLocation;
	
	void setupRender()
	{
		lessonDate = db.getLessonDateByPK(null, Integer.parseInt(PK));
		
		courseLocation = lessonDate.getLesson().getCourse().getName();
		
		instructors = db.getInstructorsByLessonDate(lessonDate);
	}
	
	String onPassivate()
	{
		return String.valueOf(PK);
	}
	
	public void onActivate(int pK) {
		PK = String.valueOf(pK);
	}
}



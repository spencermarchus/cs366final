package edu.ndsu.finalProject.pages.guardians;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.tynamo.security.services.SecurityService;
import edu.ndsu.finalProject.cayenne.persistent.LessonDate;
import edu.ndsu.finalProject.services.DatabaseService;
import edu.ndsu.finalProject.services.UserAccountService;

@RequiresAuthentication
public class GuardianLessonDateView 
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
	LessonDate lessonDate;
	
	@Property
	String courseLocation;
	
	void setupRender()
	{
		lessonDate = db.getLessonDateByPK(null, Integer.parseInt(PK));
		
		courseLocation = lessonDate.getLesson().getCourse().getName();
	}
	
	String onPassivate()
	{
		return String.valueOf(PK);
	}
	
	public void onActivate(int pK) {
		PK = String.valueOf(pK);
	}
}



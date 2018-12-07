package edu.ndsu.finalProject.pages.instructors;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.cayenne.ObjectContext;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.tynamo.security.services.SecurityService;

import edu.ndsu.finalProject.cayenne.persistent.Instructor;
import edu.ndsu.finalProject.cayenne.persistent.InstructorWorking;
import edu.ndsu.finalProject.cayenne.persistent.LessonDate;
import edu.ndsu.finalProject.services.CayenneService;
import edu.ndsu.finalProject.services.DatabaseService;
import edu.ndsu.finalProject.services.UserAccountService;

@RequiresRoles("instructor")
public class MyShifts {
	@Inject
	private DatabaseService db;
	@Inject
	private CayenneService cayenneService;
	@Inject
	private UserAccountService uas;
	
	@Inject
	private SecurityService securityService;

	@Property
	@Persist
	private Instructor instructor; // object being edited
	
	@Property
	private int instructorPK; // activation context
	
	@Property
	private List<InstructorWorking> shifts;
	
	@Property
	private List<LessonDate> lessonsWorking;
	
	@Property
	private InstructorWorking shift;
	
	@Property
	private LessonDate lessonWorking;

	@Property
	private String username;
	
	public void onActivate(int PK) {
		instructorPK = PK;
	}

	public int onPassivate() {
		return instructorPK;
	}

	public void setupRender() {
		
		
		username = securityService.getSubject().getPrincipal().toString();
		instructor = uas.getInstructorByEmail(username);
		
		if(instructor == null)
			System.out.println("INSTRUCTOR NULL");
		shifts = null;
		
		/*
		List<LessonDate> allDates = db.getAllLessonDates();
		List<LessonDate> shifts = new ArrayList<LessonDate>;
		
		for(LessonDate ld : allDates)
		{
			if()
		}
		*/
		
		lessonsWorking = db.getLessonDatesByInstructor(instructor);
		
	}
}

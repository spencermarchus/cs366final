package edu.ndsu.finalProject.pages.instructors;
import java.util.List;
import javax.inject.Inject;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.tynamo.security.services.SecurityService;
import edu.ndsu.finalProject.cayenne.persistent.Instructor;
import edu.ndsu.finalProject.cayenne.persistent.InstructorWorking;
import edu.ndsu.finalProject.cayenne.persistent.LessonDate;
import edu.ndsu.finalProject.cayenne.persistent.Shift;
import edu.ndsu.finalProject.services.DatabaseService;
import edu.ndsu.finalProject.services.UserAccountService;

@RequiresRoles("instructor")
public class MyShifts {
	@Inject
	private DatabaseService db;
	
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
	private List<Shift> shifts;
	
	@Property
	private Shift shift;
	
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
				
		/*
		List<LessonDate> allDates = db.getAllLessonDates();
		List<LessonDate> shifts = new ArrayList<LessonDate>;
		
		for(LessonDate ld : allDates)
		{
			if()
		}
		*/
		
		shifts = db.getShiftsByInstructor(instructor);
		
	}
}

package edu.ndsu.finalProject.pages.guardians;

import javax.inject.Inject;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.tynamo.security.services.SecurityService;

import edu.ndsu.finalProject.cayenne.persistent.Course;
import edu.ndsu.finalProject.cayenne.persistent.Guardian;
import edu.ndsu.finalProject.cayenne.persistent.Guardianship;
import edu.ndsu.finalProject.cayenne.persistent.Student;
import edu.ndsu.finalProject.services.DatabaseService;
import edu.ndsu.finalProject.services.UserAccountService;


@RequiresRoles("guardian")
public class AddStudent {
	@Inject
	private DatabaseService db;

	@Inject
	private SecurityService securityService;
	
	@InjectComponent
	private Form addForm;

	@Property
	@Persist
	private Student student;
	
	@Property
	@Persist
	private Guardian guardian;

	@Property
	private SelectModel selectModel;
	
	@Inject
	UserAccountService uas;
	
	@Property
	private String username;

	void setupRender() {
		username = securityService.getSubject().getPrincipal().toString();	
		guardian = uas.getGuardianByEmail(username);
		student = db.getNewStudent();
		
	}

	void onValidateFromAddForm() {
		if(student.getName() == null || student.getName().trim().isEmpty()) {
			addForm.recordError("You must specify a name for the course.");
		}
		if(student.getBirthDate() == null || student.getBirthDate().trim().isEmpty()) {
			addForm.recordError("You must specify a phone number for the course.");
		}
		
		
		// Only save changes to the database if there were no errors
		if(!addForm.getHasErrors()) {
			db.updateStudent(student);
			
			Guardianship gs = db.getNewGuardianship();
			gs.setStudent(student);
			gs.setGuardian(guardian);
			db.updateGuardianship(gs);
		}
	}
	
	Object onSuccess() {
		return "guardians/GuardianPage";
	}
}

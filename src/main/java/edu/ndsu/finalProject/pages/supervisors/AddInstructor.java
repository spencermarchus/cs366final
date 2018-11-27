package edu.ndsu.finalProject.pages.supervisors;

import javax.inject.Inject;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import edu.ndsu.finalProject.cayenne.persistent.Instructor;
import edu.ndsu.finalProject.services.DatabaseService;

@RequiresRoles("supervisor")
public class AddInstructor {
	@Inject
	private DatabaseService db;
	
	@Property
	private String tempPassword;

	@InjectComponent
	private Form addForm;

	@Property
	@Persist
	private Instructor instructor;

	@Property
	private SelectModel selectModel;
	


	void setupRender() {
		instructor = db.getNewInstructor();
		
	}

	void onValidateFromAddForm() {
		if(instructor.getFName() == null || instructor.getFName().trim().isEmpty()) {
			addForm.recordError("You must specify a first name for the instructor.");
		}
		if(instructor.getLName() == null || instructor.getLName().trim().isEmpty()) {
			addForm.recordError("You must specify a last name for the instructor.");
		}
		if(instructor.getAddress() == null || instructor.getAddress().trim().isEmpty()) {
			addForm.recordError("You must specify an address for the instructor.");
		}
		if(instructor.getDateOfBirth() == null || instructor.getDateOfBirth().trim().isEmpty()) {
			addForm.recordError("You must specify a date of birth for the instructor.");
		}
		if(instructor.getEmail() == null || instructor.getEmail().trim().isEmpty()) {
			addForm.recordError("You must specify an email for the instructor.");
		}
		if(instructor.getPhone() == null || instructor.getPhone().trim().isEmpty()) {
			addForm.recordError("You must specify a phone number for the instructor.");
		}
		if(instructor.getWage() == 0.0f) {
			addForm.recordError("You must specify a wage for the instructor.");
		}
		// Only save changes to the database if there were no errors
		if(!addForm.getHasErrors()) {
			instructor.setPassword(tempPassword);
			db.updateInstructor(instructor);
		}
	}
	
	Object onSubmit() {
		return "supervisors/AllInstructors";
	}
}

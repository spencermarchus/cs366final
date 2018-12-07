package edu.ndsu.finalProject.pages.supervisors;

import javax.inject.Inject;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import edu.ndsu.finalProject.cayenne.persistent.Course;
import edu.ndsu.finalProject.services.DatabaseService;


@RequiresRoles("supervisor")
public class AddCourse {
	@Inject
	private DatabaseService db;


	@InjectComponent
	private Form addForm;

	@Property
	@Persist
	private Course course;

	@Property
	private SelectModel selectModel;
	


	void setupRender() {
		course = db.getNewCourse();
		
	}

	void onValidateFromAddForm() {
		if(course.getName() == null || course.getName().trim().isEmpty()) {
			addForm.recordError("You must specify a name for the course.");
		}
		if(course.getPhone() == null || course.getPhone().trim().isEmpty()) {
			addForm.recordError("You must specify a phone number for the course.");
		}
		if(course.getAddress() == null || course.getAddress().trim().isEmpty()) {
			addForm.recordError("You must specify an address for the course.");
		}
		if(course.getEmail() == null || course.getEmail().trim().isEmpty()) {
			addForm.recordError("You must specify an email for the course.");
		}
		if(course.getWebsite() == null || course.getWebsite().trim().isEmpty()) {
			addForm.recordError("You must specify a website for the course.");
		}
		// Only save changes to the database if there were no errors
		if(!addForm.getHasErrors()) {
			db.updateCourse(course);
		}
	}
	
	Object onSubmit() {
		return "Courses";
	}
}

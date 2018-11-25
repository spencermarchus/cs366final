package edu.ndsu.finalProject.pages.supervisors;

import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.mixins.Confirm;
import org.apache.tapestry5.services.SelectModelFactory;

import edu.ndsu.finalProject.cayenne.persistent.Course;
import edu.ndsu.finalProject.services.DatabaseService;



public class AddCourse {
	@Inject
	private DatabaseService db;

	@Inject
	private SelectModelFactory selectModelFactory;

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
		if(course.getPhone() == null) {
			addForm.recordError("You must specify a phone number for the course.");
		}
		if(course.getAddress() == null) {
			addForm.recordError("You must specify an address for the course.");
		}
		if(course.getEmail() == null) {
			addForm.recordError("You must specify an email for the course.");
		}
		if(course.getWebsite() == null) {
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

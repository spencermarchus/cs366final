package edu.ndsu.finalProject.pages.supervisors;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.services.SelectModelFactory;


import edu.ndsu.finalProject.cayenne.persistent.Course;
import edu.ndsu.finalProject.cayenne.persistent.Lesson;
import edu.ndsu.finalProject.cayenne.persistent.LessonDate;
import edu.ndsu.finalProject.services.CourseValueEncoder;
import edu.ndsu.finalProject.services.DatabaseService;

@RequiresRoles("supervisor")
public class AddLessonDate {
	@Inject
	private DatabaseService db;
	
	@Property
	private String tempPassword;

	@InjectComponent
	private Form addForm;

	@Property
	@Persist
	private Lesson lesson;
	
	@Property
	@Persist
	private String PK;
	
	@Property
	private String time;
	
	@Property
	private String date;
	
	@Property
	@Persist
	private LessonDate lessonDate;
	
		
	void setupRender() {
		lesson = db.getLessonByPK(null, Integer.parseInt(PK));
	}

	void onValidateFromAddForm() {

		lessonDate = db.getNewLessonDate(lesson.getObjectContext());
		
		if(date == null || date.trim().isEmpty()) {
			addForm.recordError("You must enter a date.");
		}
		
		if(time == null || time.trim().isEmpty()) {
			addForm.recordError("You must enter a time.");
		}
		
		// Only save changes to the database if there were no errors
		if(!addForm.getHasErrors()) {
			lessonDate.setLessonDatetime(date.trim() + " " + time.trim());
			lessonDate.setLesson(lesson);
			db.updateLesson(lesson);
		}
	}
	
	Object onSuccess() {
		return "supervisors/SupervisorPage";
	}

	
	String onPassivate()
	{
		return String.valueOf(PK);
	}
	
	public void onActivate(int pK) {
		PK = String.valueOf(pK);
	}
}

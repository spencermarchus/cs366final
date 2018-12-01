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
import edu.ndsu.finalProject.services.CourseValueEncoder;
import edu.ndsu.finalProject.services.DatabaseService;

@RequiresRoles("supervisor")
public class AddLesson {
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
	private SelectModel selectModelCourse;
	
	@Inject
    private SelectModelFactory selectModelFactory;
	
	@Property
	@Persist
	private ValueEncoder<Course> valueEncoder; 
	
	void setupRender() {
		lesson = db.getNewLesson();
		List<Course> courses = db.getAllCourses(lesson.getObjectContext());
		
		selectModelCourse = selectModelFactory.create(courses, "name");
		valueEncoder = new CourseValueEncoder(db, lesson.getObjectContext());
	}

	void onValidateFromAddForm() {
		if(lesson.getDescription() == null || lesson.getDescription().trim().isEmpty()) {
			addForm.recordError("You must specify a name for the lesson.");
		}
		
		if(lesson.getCapacity() == 0 ) {
			addForm.recordError("You must specify a capacity for the lesson.");
		}
		if(lesson.getLevel() == null || lesson.getLevel().trim().isEmpty()) {
			addForm.recordError("You must specify a level - red, white, or blue.");
		}
		
		if(lesson.getCourse() == null)
		{
			addForm.recordError("You must specify a course.");
		}
		
		
		
		// Only save changes to the database if there were no errors
		if(!addForm.getHasErrors()) {
			db.updateLesson(lesson);
		}
	}
	
	Object onSuccess() {
		return "supervisors/Alllessons";
	}

	
	
	/*
	public ValueEncoder<String> getCourseEncoder() {

        return new ValueEncoder<String>() {

            @Override
            public String toClient(String value) {
                // return the given object's ID
                return value;
            }

            @Override
            public String toValue(String id) { 
                return id;
            }
        }; 
    }
    */
}

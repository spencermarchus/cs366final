package edu.ndsu.finalProject.pages.guardians;

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
import org.tynamo.security.services.SecurityService;

import edu.ndsu.finalProject.cayenne.persistent.Course;
import edu.ndsu.finalProject.cayenne.persistent.Enrollment;
import edu.ndsu.finalProject.cayenne.persistent.Guardian;
import edu.ndsu.finalProject.cayenne.persistent.Guardianship;
import edu.ndsu.finalProject.cayenne.persistent.Lesson;
import edu.ndsu.finalProject.cayenne.persistent.Student;
import edu.ndsu.finalProject.services.DatabaseService;
import edu.ndsu.finalProject.services.LessonValueEncoder;
import edu.ndsu.finalProject.services.StudentValueEncoder;
import edu.ndsu.finalProject.services.UserAccountService;


@RequiresRoles("guardian")
public class EnrollStudent {
	@Inject
	private DatabaseService db;

	@Inject
	private SecurityService securityService;
	
	@InjectComponent
	private Form addForm;

	@Property
	Lesson lesson;
	
	@Property
	@Persist
	private Student student;
	
	@Property
	@Persist
	private Guardian guardian;

	@Property
	private SelectModel selectModelStudent;
	
	@Property
	private SelectModel selectModelLesson;
	
	@Inject
	private SelectModelFactory selectModelFactory;
	
	@Property
	@Persist
	private ValueEncoder<Lesson> valueEncoderLesson;
	
	@Property
	@Persist
	private ValueEncoder<Student> valueEncoderStudent;
	
	@Inject
	UserAccountService uas;
	
	@Property
	private String username;

	void setupRender() {
		username = securityService.getSubject().getPrincipal().toString();	
		guardian = uas.getGuardianByEmail(username);
		List<Student> myStudents = db.getStudentsByGuardian(guardian);
		List<Lesson> lessons = db.getAllLessons(guardian.getObjectContext());
		
		selectModelLesson = selectModelFactory.create(lessons, "description");
		selectModelStudent = selectModelFactory.create(myStudents, "name");
		
		valueEncoderStudent = new StudentValueEncoder(db, guardian.getObjectContext());
		valueEncoderLesson = new LessonValueEncoder(db, guardian.getObjectContext());
	}

	void onValidateFromAddForm() {
		if(student == null || student.getName().trim().isEmpty()) {
			addForm.recordError("You must select a student");
		}
		if(lesson == null ) {
			addForm.recordError("You must select a lesson");
		}
		if(db.getNumEnrollmentsForLesson(lesson) >= lesson.getCapacity()) {
			addForm.recordError("Lesson is full");
		}
		
		Enrollment e = db.getNewEnrollment(guardian.getObjectContext());
		e.setLesson(lesson);
		e.setStudent(student);
		
		if(db.alreadyEnrolled(e))
		{
			addForm.recordError("Student already enrolled in lesson.");
		}
		
		// Only save changes to the database if there were no errors
		if(!addForm.getHasErrors()) {
			db.updateEnrollment(e);
		}
	}
	
	Object onSuccess() {
		return "guardians/GuardianPage";
	}
}

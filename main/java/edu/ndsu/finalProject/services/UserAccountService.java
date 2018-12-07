package edu.ndsu.finalProject.services;

import org.apache.cayenne.ObjectContext;
import edu.ndsu.finalProject.cayenne.persistent.*;
public interface UserAccountService 
{
	Instructor getInstructorByEmail(String userID);	
	Guardian getGuardianByEmail(String userID);	
	UserAccount createNewUserAccount(ObjectContext context);	
	Instructor createNewInstructor(ObjectContext context);
	Guardian createNewGuardian(ObjectContext context);
	Guardianship createNewGuardianship(ObjectContext context);
	InstructorWorking createNewInstructorWorking(ObjectContext context);
	Student createNewStudent(ObjectContext context);
	Course createNewCourse(ObjectContext context);
	Enrollment createNewEnrollment(ObjectContext context);
	Lesson createNewLesson(ObjectContext context);
	LessonDate createNewLessonDate(ObjectContext context);
	void updateUserAccount(UserAccount userAccount);
}


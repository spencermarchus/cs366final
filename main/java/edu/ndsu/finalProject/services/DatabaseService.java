package edu.ndsu.finalProject.services;

import java.util.List;

import org.apache.cayenne.ObjectContext;

import edu.ndsu.finalProject.cayenne.persistent.*;



public interface DatabaseService {
	Guardian getGuardianByPK(ObjectContext context, int PK);
	List<Course> getAllCourses();
	List<Student> getAllStudents();
	List<Instructor> getAllInstructors();
	List<Guardian> getAllGuardians();
	List<Guardianship> getAllGuardianships();
	Student getStudentByPK(ObjectContext context, int PK);
	List<InstructorWorking> getAllShifts();
	List<Lesson> getAllLessons();
	List<LessonDate> getAllLessonDates();
	List<InstructorWorking> getAllInstructorWorkings();
	LessonDate getLessonDateByPK(ObjectContext context, int PK);
	Instructor getInstructorByPK(ObjectContext context, int PK);
	List<LessonDate> getLessonDatesByInstructor(Instructor i);
}

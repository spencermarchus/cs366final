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
	List<InstructorWorking> getAllShifts();
	List<Lesson> getAllLessons();
	List<LessonDate> getAllLessonDates();
	List<Guardianship> getAllGuardianships();
	List<InstructorWorking> getAllInstructorWorkings();
	LessonDate getLessonDateByPK(ObjectContext context, int PK);
	Instructor getInstructorByPK(ObjectContext context, int PK);
	Student getStudentByPK(ObjectContext context, int PK);
	List<LessonDate> getLessonDatesByInstructor(Instructor i);
	List<Student> getStudentsByGuardian(Guardian g);
	Course getNewCourse();
	void updateCourse(Course c);
	void updateStudent(Student s);
	Instructor getNewInstructor();
	InstructorWorking getNewInstructorWorking();
	Guardian getNewGuardian();
	Guardianship getNewGuardianship();
	Student getNewStudent();
	void updateInstructor(Instructor i);
	void updateGuardianship(Guardianship gs);
	List<Instructor> getInstructorsByLessonDate(LessonDate ld);
	Instructor getInstructorForName(ObjectContext context, String name);
	LessonDate getLessonDateByToString(ObjectContext context, String name);
	List<String> getAllLessonDateStrings();
	List<String> getAllInstructorNames();
	boolean shiftExists(InstructorWorking iw);
	void updateGuardian(Guardian g);
}

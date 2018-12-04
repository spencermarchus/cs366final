package edu.ndsu.finalProject.services;

import java.util.List;

import org.apache.cayenne.ObjectContext;

import edu.ndsu.finalProject.cayenne.persistent.*;



public interface DatabaseService {
	Guardian getGuardianByPK(ObjectContext context, int PK);
	List<Course> getAllCourses(ObjectContext context);
	List<Student> getAllStudents(ObjectContext context);
	List<Instructor> getAllInstructors(ObjectContext context);
	List<Guardian> getAllGuardians(ObjectContext context);
	List<Shift> getAllShifts(ObjectContext context);
	List<Lesson> getAllLessons(ObjectContext context);
	List<LessonDate> getAllLessonDates(ObjectContext context);
	List<Guardianship> getAllGuardianships(ObjectContext context);
	List<Enrollment> getAllEnrollments(ObjectContext context);
	List<InstructorWorking> getAllInstructorWorkings(ObjectContext context);
	LessonDate getLessonDateByPK(ObjectContext context, int PK);
	Instructor getInstructorByPK(ObjectContext context, int PK);
	Student getStudentByPK(ObjectContext context, int PK);
	Course getCourseByPK(ObjectContext context, int PK);
	Lesson getLessonByPK(ObjectContext context, int PK);
	List<LessonDate> getLessonDatesByInstructor(Instructor i);
	List<Student> getStudentsByGuardian(Guardian g);
	List<Lesson> getLessonsByStudent(Student s);
	List<Shift> getShiftsByInstructor(Instructor i);
	Course getNewCourse();
	LessonDate getNewLessonDate(ObjectContext context);
	Enrollment getNewEnrollment(ObjectContext context);
	void updateCourse(Course c);
	void updateStudent(Student s);
	void updateLesson(Lesson l);
	Instructor getNewInstructor();
	InstructorWorking getNewInstructorWorking();
	Guardian getNewGuardian();
	Guardianship getNewGuardianship(ObjectContext context);
	Student getNewStudent(ObjectContext context);
	Lesson getNewLesson();
	void updateInstructor(Instructor i);
	void updateGuardianship(Guardianship gs);
	List<Instructor> getInstructorsByLessonDate(LessonDate ld);
	Instructor getInstructorForName(ObjectContext context, String name);
	LessonDate getLessonDateByToString(ObjectContext context, String name);
	Course getCourseByName(ObjectContext context, String name);
	List<String> getAllLessonDateStrings();
	List<String> getAllInstructorNames();
	List<String> getAllCourseNames();
	boolean shiftExists(InstructorWorking iw);
	boolean alreadyEnrolled(Enrollment e);
	void updateGuardian(Guardian g);
	void updateEnrollment(Enrollment e);
	Integer getNumEnrollmentsForLesson(Lesson l);
	
}

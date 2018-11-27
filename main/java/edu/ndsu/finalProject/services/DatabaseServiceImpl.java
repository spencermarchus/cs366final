package edu.ndsu.finalProject.services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;


import edu.ndsu.finalProject.cayenne.persistent.*;



public class DatabaseServiceImpl implements DatabaseService {
	
	private CayenneService cayenneService;
	
	public DatabaseServiceImpl(CayenneService cayenneService) {
		this.cayenneService = cayenneService;
	}
	
	public Guardian getGuardianByPK(ObjectContext context, int PK) {
		if(context == null) {
			context = cayenneService.newContext();
		}

		return (Guardian) Cayenne.objectForPK(context, Guardian.class, PK);
	}
	
	public Student getStudentByPK(ObjectContext context, int PK) {
		if(context == null) {
			context = cayenneService.newContext();
		}

		return (Student) Cayenne.objectForPK(context, Student.class, PK);
	}
	
	public List<Course> getAllCourses() {
		return ObjectSelect.query(Course.class).select(cayenneService.newContext());
	}
	
	public List<Student> getAllStudents() {
		return ObjectSelect.query(Student.class).select(cayenneService.newContext());
	}
	
	public List<Instructor> getAllInstructors() {
		return ObjectSelect.query(Instructor.class).select(cayenneService.newContext());
	}
	
	public List<InstructorWorking> getAllInstructorWorkings() {
		return ObjectSelect.query(InstructorWorking.class).select(cayenneService.newContext());
	}
	
	public List<Guardian> getAllGuardians() {
		return ObjectSelect.query(Guardian.class).select(cayenneService.newContext());
	}
	
	public List<Guardianship> getAllGuardianships() {
		return ObjectSelect.query(Guardianship.class).select(cayenneService.newContext());
	}
	
	public List<InstructorWorking> getAllShifts() {
		return ObjectSelect.query(InstructorWorking.class).select(cayenneService.newContext());
	}
	
	public List<Lesson> getAllLessons() {
		return ObjectSelect.query(Lesson.class).select(cayenneService.newContext());
	}
	
	public List<LessonDate> getAllLessonDates() {
		return ObjectSelect.query(LessonDate.class).select(cayenneService.newContext());
	}
	
	public Instructor getInstructorByPK(ObjectContext context, int PK) {
		if(context == null) {
			context = cayenneService.newContext();
		}

		return (Instructor) Cayenne.objectForPK(context, Instructor.class, PK);
	}
	
	public LessonDate getLessonDateByPK(ObjectContext context, int PK)
	{
		if(context == null) {
			context = cayenneService.newContext();
		}

		return (LessonDate) Cayenne.objectForPK(context, LessonDate.class, PK);
	}
	
	//returns all future lesson dates that the instructor is assigned, including all lessons worked in the last 24 hours
	public List<LessonDate> getLessonDatesByInstructor(Instructor i)
	{
		
		List<InstructorWorking> allShifts = getAllInstructorWorkings();
		List<InstructorWorking> shiftsAssigned = new ArrayList<InstructorWorking>();
		for(InstructorWorking iw : allShifts)
		{
			if(iw.getInstructorId() == i.getPK())
				shiftsAssigned.add(iw);
		}
		
		List<LessonDate> lessonsWorking = new ArrayList<LessonDate>();
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				
		//for matching attributes in join table, add instructor's shifts to list and return
		for(InstructorWorking iw : shiftsAssigned)
		{
			try {
				LessonDate ld = getLessonDateByPK(null, iw.getDateId());
				
				Date firstDate = sdf.parse(ld.getLessonDay());
				Date seconddate = new Date();
				seconddate.setTime(seconddate.getTime()-86400000); //subtract a day from current time so that today's worked lessons always get returned
				if(firstDate.after(seconddate))
					lessonsWorking.add(ld);
			}catch(Exception e)
			{
				//something didn't get formatted right - should never really happen
			}
			
		}
		
		return lessonsWorking;
	}
	
	//get all student accounts under a guardian account
	public List<Student> getStudentsByGuardian(Guardian g)
	{
		List<Guardianship> allGuardianships = getAllGuardianships();
		List<Student> myStudents = new ArrayList<Student>();
		
		for(Guardianship gu : allGuardianships)
		{
			if(gu.getGuardianId() == g.getPK())
				myStudents.add(getStudentByPK(g.getObjectContext(), gu.getStudentId()));
		}
		
		return myStudents;
	}
	
}

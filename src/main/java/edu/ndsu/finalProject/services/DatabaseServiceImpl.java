package edu.ndsu.finalProject.services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import edu.ndsu.finalProject.cayenne.persistent.*;



public class DatabaseServiceImpl implements DatabaseService {
	
	private CayenneService cayenneService;
	
	//private UserAccountService userAccountService;
	
	public DatabaseServiceImpl(CayenneService cayenneService) {
		this.cayenneService = cayenneService;
	}
	
	public Guardian getGuardianByPK(ObjectContext context, int PK) {
		if(context == null) {
			context = cayenneService.newContext();
		}

	
		
		return (Guardian) Cayenne.objectForPK(context, Guardian.class, PK);
	}
	
	public Instructor getInstructorForName(ObjectContext context, String name)
	{
		List<Instructor> all = this.getAllInstructors();
		for(Instructor i : all)
		{
			if(i.getName().equals(name))
				return i;
		}
		return null;
	}
	
	public LessonDate getLessonDateByToString(ObjectContext context, String name)
	{
		List<LessonDate> all = this.getAllLessonDates();
		for(LessonDate ld : all)
		{
			if(ld.toString().equals(name))
				return ld;
			
		}
		return null;
	}
	
	public boolean shiftExists(InstructorWorking test)
	{
		List<InstructorWorking> allShifts = this.getAllInstructorWorkings();
		for(InstructorWorking iw : allShifts)
		{
			if(test.getDateId() == iw.getDateId() && test.getInstructorId() == iw.getInstructorId())
				return true;
		}
		return false;
	}
	
	public List<String> getAllLessonDateStrings() {
		List<LessonDate> all = this.getAllLessonDates();
		List<String> dates = new ArrayList<String>();
		for(LessonDate ld : all)
			dates.add(ld.toString());
		
		return dates;
	}
	
	public List<String> getAllInstructorNames() {
		List<Instructor> all = this.getAllInstructors();
		List<String> names = new ArrayList<String>();
		for(Instructor i : all)
			names.add(i.getName());
		
		return names;
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
	
	public List<InstructorWorking> getAllShifts() {
		return ObjectSelect.query(InstructorWorking.class).select(cayenneService.newContext());
	}
	
	public List<Lesson> getAllLessons() {
		return ObjectSelect.query(Lesson.class).select(cayenneService.newContext());
	}
	
	public List<LessonDate> getAllLessonDates() {
		return ObjectSelect.query(LessonDate.class).select(cayenneService.newContext());
	}
	
	public List<Guardianship> getAllGuardianships() {
		return ObjectSelect.query(Guardianship.class).select(cayenneService.newContext());
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
				
		for(InstructorWorking iw : shiftsAssigned)
		{
			try {
				LessonDate ld = getLessonDateByPK(null, iw.getDateId());
				
				Date firstDate = sdf.parse(ld.getLessonDay());
				Date seconddate = new Date();
				seconddate.setTime(seconddate.getTime()-86400000); //subtract a day from current time so that today's lessons always get returned
				if(firstDate.after(seconddate))
					lessonsWorking.add(ld);
			}catch(Exception e)
			{
				//something didn't get formatted right - should never really happen
			}
			
		}
		
		return lessonsWorking;
	}
	
	//returns all Instructors that the instructor is assigned, including all lessons worked in the last 24 hours
		public List<Instructor> getInstructorsByLessonDate(LessonDate ld)
		{
			
			List<InstructorWorking> allShifts = getAllInstructorWorkings();
			List<InstructorWorking> shifts = new ArrayList<InstructorWorking>();
			
			for(InstructorWorking iw : allShifts)
			{
				if(iw.getDateId() == ld.getPK())
					shifts.add(iw);
			}
			
			List<Instructor> instructorsWorking = new ArrayList<Instructor>();
		
			for(InstructorWorking iw : shifts)
			{
				instructorsWorking.add(this.getInstructorByPK(null, iw.getInstructorId()));	
			}
			
			return instructorsWorking;
		}
	
	public Course getNewCourse() {
		return cayenneService.newContext().newObject(Course.class);
	}
	
	public InstructorWorking getNewInstructorWorking() {
		return cayenneService.newContext().newObject(InstructorWorking.class);
	}
	
	public void updateCourse(Course c) {
		c.getObjectContext().commitChanges();
	}
	
	public Instructor getNewInstructor() {
		ObjectContext context = cayenneService.newContext();
		Instructor i = context.newObject(Instructor.class);
		i.setPasswordSalt(new SecureRandomNumberGenerator().nextBytes().toHex());
		return i;
	}
	
	public void updateInstructor(Instructor i) {
		i.getObjectContext().commitChanges();
	}
	
	//gets a list of guardians corresponding to a student
	public List<Guardian> getGuardiansByStudent(Student s)
	{
		List<Guardian> myGuardians = new ArrayList<Guardian>();
		List<Guardianship> allGuardianships = this.getAllGuardianships();
		
		for(Guardianship gu : allGuardianships)
		{
			if(gu.getStudentId() == s.getPK())
				myGuardians.add(this.getGuardianByPK(null, gu.getGuardianId()));
		}
		
		return myGuardians;
	}
	
	//gets a list of students corresponding to a guardian
	public List<Student> getStudentsByGuardian(Guardian g)
	{
		List<Student> myStudents = new ArrayList<Student>();
		List<Guardianship> allGuardianships = this.getAllGuardianships();
			
		for(Guardianship gu : allGuardianships)
		{
			if(gu.getGuardianId() == g.getPK())
				myStudents.add(this.getStudentByPK(null, gu.getStudentId()));
		}
			
		return myStudents;
	}
	
}

package edu.ndsu.finalProject.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import org.apache.cayenne.query.SelectQuery;
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
		List<Instructor> all = this.getAllInstructors(context);
		for(Instructor i : all)
		{
			if(i.getName().equals(name))
				return i;
		}
		return null;
	}
	
	public LessonDate getLessonDateByToString(ObjectContext context, String name)
	{
		List<LessonDate> all = getAllLessonDates(context);
		for(LessonDate ld : all)
		{
			if(ld.toString().equals(name))
				return ld;
			
		}
		return null;
	}
	
	//get lessons that a student is enrolled in
	public List<Lesson> getLessonsByStudent(Student s)
	{
		List<Enrollment> allEnrollments = getAllEnrollments(s.getObjectContext());
		List<Lesson> mylessons = new ArrayList<Lesson>();
		
		for(Enrollment e : allEnrollments)
		{
			if(e.getStudent().getPK() == s.getPK())
			{
				mylessons.add(e.getLesson());
			}
		}
		
		return mylessons;
		
	}
	
	public boolean shiftExists(InstructorWorking other)
	{
		List<InstructorWorking> allShifts = this.getAllInstructorWorkings(other.getObjectContext());
		for(InstructorWorking iw : allShifts)
		{
			if(other.getDate().getPK() == iw.getDate().getPK() && other.getInstructor().getPK() == iw.getInstructor().getPK())
				return true;
		}
		return false;
	}
	
	public List<String> getAllLessonDateStrings() {
		List<LessonDate> all = this.getAllLessonDates(cayenneService.newContext());
		List<String> dates = new ArrayList<String>();
		for(LessonDate ld : all)
			dates.add(ld.toString());
		
		return dates;
	}
	
	public List<String> getAllInstructorNames() {
		List<Instructor> all = this.getAllInstructors(cayenneService.newContext());
		List<String> names = new ArrayList<String>();
		for(Instructor i : all)
			names.add(i.getName());
		
		return names;
	}
	
	public List<String> getAllCourseNames() {
		List<Course> all = this.getAllCourses(cayenneService.newContext());
		List<String> names = new ArrayList<String>();
		for(Course c : all)
			names.add(c.getName());
		
		return names;
	}
	
	public Student getStudentByPK(ObjectContext context, int PK) {
		if(context == null) {
			context = cayenneService.newContext();
		}

		return (Student) Cayenne.objectForPK(context, Student.class, PK);
	}
	
	public Course getCourseByPK(ObjectContext context, int PK)
	{
		if(context == null)
			context = cayenneService.newContext();
		return (Course) Cayenne.objectForPK(context, Course.class, PK);
	}
	
	public Course getCourseByName(ObjectContext context, String name)
	{
		List<Course> allCourses = getAllCourses(context);
		for(Course c : allCourses)
			if(c.getName().equals(name))
				return c;
		
		return null; //no result
	}
	
	public List<Course> getAllCourses(ObjectContext context) {
		if(context == null)
			context = cayenneService.newContext();
		return ObjectSelect.query(Course.class).select(context);
	}
	
	public List<Student> getAllStudents(ObjectContext context) {
		if(context == null)
			context = cayenneService.newContext();
		return ObjectSelect.query(Student.class).select(context);
	}
	
	public List<Instructor> getAllInstructors(ObjectContext context) {
		if(context == null)
			context = cayenneService.newContext();
		return ObjectSelect.query(Instructor.class).select(context);
	}
	
	public List<Enrollment> getAllEnrollments(ObjectContext context) {
		if(context == null)
			context = cayenneService.newContext();
		return ObjectSelect.query(Enrollment.class).select(context);
	}
	
	public List<InstructorWorking> getAllInstructorWorkings(ObjectContext context) {
		if(context == null)
			context = cayenneService.newContext();
		return ObjectSelect.query(InstructorWorking.class).select(context);
	}
	
	public List<Guardian> getAllGuardians(ObjectContext context) {
		if(context == null)
			context = cayenneService.newContext();
		return ObjectSelect.query(Guardian.class).select(context);
	}
	
	public List<Shift> getAllShifts(ObjectContext context) {
		if(context == null)
			context = cayenneService.newContext();
		List<InstructorWorking> allInstructorWorkings = getAllInstructorWorkings(context);
		List<Shift> allShifts = new ArrayList<Shift>();
		for(InstructorWorking iw : allInstructorWorkings)
		{
			allShifts.add(new Shift(
					iw.getInstructor().getName(), 
					iw.getDate().getLessonDatetime(), 
					iw.getDate().getLesson().getDescription(), 
					iw.getDate().getLesson().getCourse().getName()
					));
		}
		return allShifts;
	}
	
	public List<Lesson> getAllLessons(ObjectContext context ){
		if(context == null)
			context = cayenneService.newContext();
		return ObjectSelect.query(Lesson.class).select(context);
	}
	
	public List<LessonDate> getAllLessonDates(ObjectContext context) {
		if(context == null)
			context = cayenneService.newContext();
		return ObjectSelect.query(LessonDate.class).select(context);
	}
	
	public List<Guardianship> getAllGuardianships(ObjectContext context) {
		if(context == null)
			context = cayenneService.newContext();
		return ObjectSelect.query(Guardianship.class).select(context);
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
	
	public Lesson getLessonByPK(ObjectContext context, int PK)
	{
		if(context == null) {
			context = cayenneService.newContext();
		}

		return (Lesson) Cayenne.objectForPK(context, Lesson.class, PK);
	}
	
	//returns all future lesson dates that the instructor is assigned, including all lessons worked in the last 24 hours
	public List<LessonDate> getLessonDatesByInstructor(Instructor i)
	{
		
		List<InstructorWorking> allShifts = getAllInstructorWorkings(i.getObjectContext());
		List<InstructorWorking> shiftsAssigned = new ArrayList<InstructorWorking>();
		for(InstructorWorking iw : allShifts)
		{
			if(iw.getInstructor().getPK() == i.getPK())
				shiftsAssigned.add(iw);
		}
		
		List<LessonDate> lessonsWorking = new ArrayList<LessonDate>();
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				
		for(InstructorWorking iw : shiftsAssigned)
		{
			try {
				LessonDate ld = iw.getDate();
				//first ten characters are the date
				Date firstDate = sdf.parse(ld.getLessonDatetime().substring(10));
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
			
			List<InstructorWorking> allShifts = getAllInstructorWorkings(ld.getObjectContext());
			List<InstructorWorking> shifts = new ArrayList<InstructorWorking>();
			
			for(InstructorWorking iw : allShifts)
			{
				if(iw.getDate().getPK() == ld.getPK())
					shifts.add(iw);
			}
			
			List<Instructor> instructorsWorking = new ArrayList<Instructor>();
		
			for(InstructorWorking iw : shifts)
			{
				instructorsWorking.add(iw.getInstructor());	
			}
			
			return instructorsWorking;
		}
	
	public Course getNewCourse() {
		return cayenneService.newContext().newObject(Course.class);
	}
	
	//create new guardian and initialize password salt to hash password with
	public Guardian getNewGuardian() {
		Guardian g = cayenneService.newContext().newObject(Guardian.class);
		g.setPasswordSalt(new SecureRandomNumberGenerator().nextBytes().toHex());
		return g;
	}
	
	//create new student 
	public Student getNewStudent() {
		Student s = cayenneService.newContext().newObject(Student.class);
		s.setRecommendedLevel("red");
		return s;
	}
		
	public Lesson getNewLesson()
	{
		return cayenneService.newContext().newObject(Lesson.class);
	}
	
	public Guardianship getNewGuardianship() {
		Guardianship gs = cayenneService.newContext().newObject(Guardianship.class);
		return gs;
	}
	
	public InstructorWorking getNewInstructorWorking() {
		return cayenneService.newContext().newObject(InstructorWorking.class);
	}
	
	
	
	//create new Instructor and initialize password salt to hash password with
	public Instructor getNewInstructor() {
		ObjectContext context = cayenneService.newContext();
		Instructor i = context.newObject(Instructor.class);
		i.setPasswordSalt(new SecureRandomNumberGenerator().nextBytes().toHex());
		return i;
	}
	
	//commit changes to instructor in database
	public void updateInstructor(Instructor i) {
		i.getObjectContext().commitChanges();
	}
	
	//commit changes to guardianship in database
		public void updateGuardianship(Guardianship g) {
			g.getObjectContext().commitChanges();
		}
		
	
	//commit changes to guardian in database
	public void updateGuardian(Guardian g) {
		g.getObjectContext().commitChanges();
	}
	
	public void updateCourse(Course c) {
		c.getObjectContext().commitChanges();
	}
	
	public void updateStudent(Student s) {
		s.getObjectContext().commitChanges();
	}
	
	public void updateLesson(Lesson l) {
		l.getObjectContext().commitChanges();
	}
	
	//gets a list of guardians corresponding to a student
	public List<Guardian> getGuardiansByStudent(Student s)
	{
		List<Guardian> myGuardians = new ArrayList<Guardian>();
		List<Guardianship> allGuardianships = this.getAllGuardianships(s.getObjectContext());
		
		for(Guardianship gu : allGuardianships)
		{
			if(gu.getStudent().getPK() == s.getPK())
				myGuardians.add(gu.getGuardian());
		}
		
		return myGuardians;
	}
	
	//gets a list of students corresponding to a guardian
	public List<Student> getStudentsByGuardian(Guardian g)
	{
		List<Student> myStudents = new ArrayList<Student>();
		List<Guardianship> allGuardianships = this.getAllGuardianships(g.getObjectContext());
			
		for(Guardianship gu : allGuardianships)
		{
			if(gu.getGuardian().getPK() == g.getPK())
				myStudents.add(gu.getStudent());
		}
			
		return myStudents;
	}
	
	public List<Shift> getShiftsByInstructor(Instructor i)
	{
		List<Shift> all = this.getAllShifts(i.getObjectContext());
		List<Shift> myShifts = new ArrayList<Shift>();
		
		for(Shift s : all)
		{
			if(s.getInstructorName().equals(i.getName()))
				myShifts.add(s);
			
		}
		return myShifts;
	}
}

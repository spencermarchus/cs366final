package edu.ndsu.finalProject.services;

import org.apache.cayenne.CayenneRuntimeException;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.ndsu.finalProject.cayenne.persistent.Course;
import edu.ndsu.finalProject.cayenne.persistent.Enrollment;
import edu.ndsu.finalProject.cayenne.persistent.Guardian;
import edu.ndsu.finalProject.cayenne.persistent.Guardianship;
import edu.ndsu.finalProject.cayenne.persistent.Instructor;
import edu.ndsu.finalProject.cayenne.persistent.InstructorWorking;
import edu.ndsu.finalProject.cayenne.persistent.Lesson;
import edu.ndsu.finalProject.cayenne.persistent.LessonDate;
import edu.ndsu.finalProject.cayenne.persistent.Student;
import edu.ndsu.finalProject.cayenne.persistent.UserAccount;

public class UserAccountServiceImpl implements UserAccountService {
	
	private CayenneService 	cayenneService;
	private Logger 			logger;
	
	public UserAccountServiceImpl(CayenneService cayenneService) {
		this.cayenneService = cayenneService;
		logger = LoggerFactory.getLogger(this.getClass());
	}

	@Override
	public Instructor getInstructorByEmail(String userID) {
		logger.info("Getting UserAccount for " + userID);
		ObjectContext context = cayenneService.newContext();
		try {
			Instructor instructor = ObjectSelect.query(Instructor.class)
				.where(Instructor.EMAIL.like(userID)).selectOne(context);
			if(instructor == null) {
				logger.info("No account for " + userID);
			}
			return instructor;
		}
		catch (CayenneRuntimeException e) {
			logger.info("More than one UserAccount was returned for " + userID);
			logger.info(e.toString(), e);
			return null; 
		}
	}
	
	@Override
	public InstructorWorking createNewInstructorWorking(ObjectContext context)
	{
		if(context == null)
			context = cayenneService.newContext();
		
		InstructorWorking iw = context.newObject(InstructorWorking.class);
		return iw;
	}
	@Override
	public Course createNewCourse(ObjectContext context)
	{
		if(context == null)
			context = cayenneService.newContext();
		Course c = context.newObject(Course.class);
		return c;
	}
	@Override
	public Lesson createNewLesson(ObjectContext context)
	{
		if(context == null)
			context = cayenneService.newContext();
		Lesson l = context.newObject(Lesson.class);
		return l;
	}
	@Override
	public Enrollment createNewEnrollment(ObjectContext context)
	{
		if(context == null)
			context = cayenneService.newContext();
		Enrollment e = context.newObject(Enrollment.class);
		return e;
	}
	
	@Override
	public LessonDate createNewLessonDate(ObjectContext context)
	{
		if(context == null)
			context = cayenneService.newContext();
		LessonDate ld = context.newObject(LessonDate.class);
		return ld;
	}
	
	@Override
	public Guardianship createNewGuardianship(ObjectContext context)
	{
		if(context == null)
			context = cayenneService.newContext();
		
		Guardianship g = context.newObject(Guardianship.class);
		return g;
	}
	
	@Override
	public Guardian getGuardianByEmail(String userID) {
		logger.info("Getting Guardian for " + userID);
		ObjectContext context = cayenneService.newContext();
		try {
			Guardian g = ObjectSelect.query(Guardian.class)
				.where(Guardian.EMAIL.like(userID)).selectOne(context);
			if(g == null) {
				logger.info("No account for " + userID);
			}
			return g;
		}
		catch (CayenneRuntimeException e) {
			logger.info("More than one Guardian was returned for " + userID);
			logger.info(e.toString(), e);
			return null; 
		}
	}

	@Override
	public UserAccount createNewUserAccount(ObjectContext context) {
		if(context == null) {
			context = cayenneService.newContext();
		}
		UserAccount userAccount = context.newObject(UserAccount.class);
		userAccount.setPasswordSalt(new SecureRandomNumberGenerator().nextBytes().toHex());
		return userAccount;
	}
	
	@Override
	public Instructor createNewInstructor(ObjectContext context) {
		if(context == null) {
			context = cayenneService.newContext();
		}
		Instructor i = context.newObject(Instructor.class);
		i.setPasswordSalt(new SecureRandomNumberGenerator().nextBytes().toHex());
		return i;
	}
	
	@Override
	public Student createNewStudent(ObjectContext context) {
		if(context == null) {
			context = cayenneService.newContext();
		}
		Student s = context.newObject(Student.class);
		s.setRecommendedLevel("red");
		return s;
	}
	
	@Override
	public Guardian createNewGuardian(ObjectContext context) {
		if(context == null) {
			context = cayenneService.newContext();
		}
		Guardian g = context.newObject(Guardian.class);
		g.setPasswordSalt(new SecureRandomNumberGenerator().nextBytes().toHex());
		return g;
	}

	@Override
	public void updateUserAccount(UserAccount userAccount) {
		userAccount.getObjectContext().commitChanges();
	}
}


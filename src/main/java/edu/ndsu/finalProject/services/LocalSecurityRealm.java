package edu.ndsu.finalProject.services;

import org.apache.cayenne.ObjectContext;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.ndsu.finalProject.cayenne.persistent.*;

public class LocalSecurityRealm extends AuthorizingRealm {
	private CayenneService cayenneService;
	private UserAccountService userAccountService; 
	private Logger logger; 
	
	public LocalSecurityRealm(CayenneService cs, UserAccountService uas) {
		this.cayenneService = cs;
		this.userAccountService = uas;
		this.logger = LoggerFactory.getLogger(this.getClass());
		
	    //create supervisor employee	
		ObjectContext context = cayenneService.newContext();
		Instructor is = userAccountService.createNewInstructor(context);
		is.setAddress("123 1st St\nBismarck, ND\n58501");
		is.setEmail("spmarchus@gmail.com");
		is.setPassword("password");
		is.setSupervisor(true);
		is.setDateOfBirth("10/12/1997");
		is.setPhone("7014258830");
		is.setFName("Spencer");
		is.setLName("Marchus");
		is.setWage((float)12.00);		
		context.commitChanges();
		
		//create a non-supervisor employee / admin
		
		Instructor i = userAccountService.createNewInstructor(context);
		i.setAddress("321 3rd Ave\nBismarck, ND\n58501");
		i.setEmail("instructor@gmail.com");
		i.setPassword("password");
		i.setSupervisor(false);
		i.setDateOfBirth("10/12/1996");
		i.setPhone("7014258830");
		i.setFName("Marchus");
		i.setLName("Spencer");
		i.setWage((float)10.00);
		context.commitChanges();
		
		//create a guardian
		Guardian g = userAccountService.createNewGuardian(context);
		g.setFName("Sarah");
		g.setLName("Marchus");
		g.setAddress("123 1st St\nBismarck, ND\n58501");
		g.setEmail("smarchus@gmail.com");
		g.setPhone("7015872545");

		//student
		Student s = userAccountService.createNewStudent(context);
		s.setBirthDate("1/1/2007");
		s.setFName("Johnny");
		s.setLName("Marchus");
		
		//student - guardian
		Guardianship gs = userAccountService.createNewGuardianship(context);
		//student and guardian set to what we just created above
		gs.setGuardian(g);
		gs.setStudent(s);
		
		Course c = userAccountService.createNewCourse(context);
		c.setName("Pebble Creek Golf Course");
		c.setAddress("2525 N 19th St, Bismarck, ND 58503");
		c.setEmail("bisparks@bisparks.org");
		c.setPhone("7012233600");
		c.setWebsite("http://bisparks.org/golf-courses/");
		context.commitChanges();
		
		Lesson l = userAccountService.createNewLesson(context);
		l.setCapacity(32);
		l.setLevel("red");
		l.setName("M-F Red Level 9AM June 3-7");
		l.setCourse(c);
		
		Enrollment e = userAccountService.createNewEnrollment(context);
		e.setLesson(l);
		e.setStudent(s);
		context.commitChanges();
		
		//create some lesson dates and shifts
		LessonDate ld = userAccountService.createNewLessonDate(context);
		ld.setLesson(l);
		ld.setLessonDay("06/03/2019");
		ld.setLessonTime("9:00-10:00AM");
		context.commitChanges();
		
		LessonDate ld2 = userAccountService.createNewLessonDate(l.getObjectContext());
		ld2.setLesson(l);
		ld2.setLessonDay("06/04/2019");
		ld2.setLessonTime("9:00-10:00AM");
		context.commitChanges();
		
		LessonDate ld3 = userAccountService.createNewLessonDate(l.getObjectContext());
		ld3.setLesson(l);
		ld3.setLessonDay("06/05/2019");
		ld3.setLessonTime("9:00-10:00AM");
		context.commitChanges();
		
		
		InstructorWorking iw = userAccountService.createNewInstructorWorking(ld.getObjectContext());
		iw.setInstructorId(is.getPK());
		iw.setDateId(ld.getPK());
		ld.getObjectContext().commitChanges();
		
		InstructorWorking iw2 = userAccountService.createNewInstructorWorking(ld2.getObjectContext());
		iw2.setInstructorId(is.getPK());
		iw2.setDateId(ld2.getPK());
		iw2.getObjectContext().commitChanges();
		
		
		is.getObjectContext().commitChanges();
		ld2.getObjectContext().commitChanges();
		
		
		//add lesson to course
		c.addToLessons(l);
		
		context.commitChanges();
		
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// This should never be null or empty, but just return if it's invalid
		if(principals == null || principals.isEmpty()) {
			return null;
		}
		SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
		
		// The primary principal will be the username when logging in
		String username = (String) principals.getPrimaryPrincipal();
		logger.info("Received potential instructor authorization request for " + username);
		Instructor inst = userAccountService.getInstructorByEmail(username);
		
		String guardianUserName = (String) principals.getPrimaryPrincipal();
		logger.info("Received potential guardian authorization request for " + guardianUserName);
		Guardian g = userAccountService.getGuardianByEmail(username);
		
		// Add roles as appropriate 
		if(inst != null) {
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			authorizationInfo.addRole("instructor");
			if(inst.isSupervisor()) authorizationInfo.addRole("supervisor");
			return authorizationInfo;
		}
		else if(g != null) {
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			authorizationInfo.addRole("guardian");
			return authorizationInfo;
		}else { return null; } //something went horribly wrong
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		
		Instructor instructor = userAccountService.getInstructorByEmail(username);
		
		logger.info("Checking to see if username matches Instructor or Guardian");
		Guardian g = userAccountService.getGuardianByEmail(username);
		
		if(instructor != null) {
			logger.info(username + " is instructor");
			// Get the hashed password and the salt used for the hash
			SimpleByteSource salt = new SimpleByteSource(instructor.getPasswordSalt());
			String hash = instructor.getPasswordHash();
			return new SimpleAuthenticationInfo(username, hash, salt, this.getName());
		}else if(g != null)
		{
			logger.info(username + " is guardian");
			SimpleByteSource salt = new SimpleByteSource(g.getPasswordSalt());
			String hash = g.getPasswordHash();
			return new SimpleAuthenticationInfo(username, hash, salt, this.getName());
		}
		else {
			logger.info(username + " not found");
			return null; //no username matches
		}
	}
}



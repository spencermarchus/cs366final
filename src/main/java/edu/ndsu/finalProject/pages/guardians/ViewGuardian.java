package edu.ndsu.finalProject.pages.guardians;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.tynamo.security.services.SecurityService;

import edu.ndsu.finalProject.cayenne.persistent.Course;
import edu.ndsu.finalProject.cayenne.persistent.Guardian;
import edu.ndsu.finalProject.cayenne.persistent.Guardianship;
import edu.ndsu.finalProject.cayenne.persistent.Student;
import edu.ndsu.finalProject.services.DatabaseService;
import edu.ndsu.finalProject.services.UserAccountService;


@RequiresRoles("guardian")
public class ViewGuardian {
	@Inject
	private DatabaseService db;

	@Inject
	private SecurityService securityService;
	
	@Property
	@Persist
	private Student student;
	
	@Property
	private List<Student> students;
	
	@Property
	@Persist
	private Guardian guardian;

	@Property
	private SelectModel selectModel;
	
	@Inject
	UserAccountService uas;
	
	@Property
	private String username;

	void setupRender() {
		username = securityService.getSubject().getPrincipal().toString();	
		guardian = uas.getGuardianByEmail(username);
		students = db.getStudentsByGuardian(guardian);
	}

}

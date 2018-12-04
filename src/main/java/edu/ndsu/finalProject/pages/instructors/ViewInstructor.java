package edu.ndsu.finalProject.pages.instructors;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.tynamo.security.services.SecurityService;

import edu.ndsu.finalProject.cayenne.persistent.Instructor;
import edu.ndsu.finalProject.services.DatabaseService;
import edu.ndsu.finalProject.services.UserAccountService;


@RequiresRoles("instructor")
public class ViewInstructor {
	@Inject
	private DatabaseService db;

	@Inject
	private SecurityService securityService;
	
	
	@Property
	@Persist
	private Instructor instructor;

	
	@Inject
	UserAccountService uas;
	
	@Property
	private String username;

	void setupRender() {
		username = securityService.getSubject().getPrincipal().toString();	
		instructor = uas.getInstructorByEmail(username);
	}

}

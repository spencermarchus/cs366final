package edu.ndsu.finalProject.pages.instructors;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.tynamo.security.services.SecurityService;

import edu.ndsu.finalProject.cayenne.persistent.Instructor;
import edu.ndsu.finalProject.cayenne.persistent.UserAccount;
import edu.ndsu.finalProject.services.DatabaseService;
import edu.ndsu.finalProject.services.UserAccountService;

@RequiresRoles("instructor")
public class InstructorPage 
{
	@Inject
	SecurityService securityService;
	
	@Inject 
	DatabaseService db;
	
	@Inject
	UserAccountService userAccountService;
	
	@Property
	@Persist
	Instructor inst;
	
	void setupRender()
	{
		String username = securityService.getSubject().getPrincipal().toString();
		inst = userAccountService.getInstructorByEmail(username);
	}
	
	public String getUnauthorizedURL() {
	    return "Index";
	  }
	
	
}



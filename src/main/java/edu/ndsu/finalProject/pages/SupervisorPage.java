package edu.ndsu.finalProject.pages;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.tynamo.security.services.SecurityService;

import edu.ndsu.finalProject.cayenne.persistent.*;
import edu.ndsu.finalProject.services.UserAccountService;

@RequiresRoles("supervisor")
public class SupervisorPage 
{
	@Inject
	SecurityService securityService;
	
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
}



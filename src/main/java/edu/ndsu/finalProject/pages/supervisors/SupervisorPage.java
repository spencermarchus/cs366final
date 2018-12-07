package edu.ndsu.finalProject.pages.supervisors;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.tynamo.security.services.SecurityService;

import edu.ndsu.finalProject.cayenne.persistent.Instructor;
import edu.ndsu.finalProject.cayenne.persistent.UserAccount;
import edu.ndsu.finalProject.services.DatabaseService;
import edu.ndsu.finalProject.services.UserAccountService;

@RequiresRoles("supervisor")
public class SupervisorPage 
{
	@Inject
	SecurityService securityService;
	
	@Inject
	UserAccountService userAccountService;
	
	@Inject
	DatabaseService db;
	
	@Property
	@Persist
	Instructor i;
	
	void setupRender()
	{
		String username = securityService.getSubject().getPrincipal().toString();
	}
}



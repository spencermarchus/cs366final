package edu.ndsu.finalProject.pages;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.tynamo.security.services.SecurityService;

import edu.ndsu.finalProject.cayenne.persistent.Instructor;
import edu.ndsu.finalProject.cayenne.persistent.UserAccount;
import edu.ndsu.finalProject.services.UserAccountService;

@RequiresRoles("instructor")
public class InstructorPage 
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
		//String username = securityService.getSubject().getPrincipal().toString();
		//userAccount = userAccountService.getUserAccountByEmail(username);
	}
}



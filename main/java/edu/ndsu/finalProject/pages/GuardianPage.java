package edu.ndsu.finalProject.pages;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.tynamo.security.services.SecurityService;

import edu.ndsu.finalProject.cayenne.persistent.Guardian;
import edu.ndsu.finalProject.cayenne.persistent.UserAccount;
import edu.ndsu.finalProject.services.UserAccountService;

@RequiresRoles("guardian")
public class GuardianPage 
{
	@Inject
	SecurityService securityService;
	
	@Inject
	UserAccountService userAccountService;
	
	@Property
	@Persist
	Guardian guardian;
	
	void setupRender()
	{
		String username = securityService.getSubject().getPrincipal().toString();
		guardian = userAccountService.getGuardianByEmail(username);
	}
}



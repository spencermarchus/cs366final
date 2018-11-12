package edu.ndsu.assignment3.pages;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.tynamo.security.services.SecurityService;

import edu.ndsu.assignment3.cayenne.persistent.UserAccount;
import edu.ndsu.assignment3.services.UserAccountService;

@RequiresAuthentication
public class MyAccount 
{
	@Inject
	SecurityService securityService;
	
	@Inject
	UserAccountService userAccountService;
	
	@Property
	@Persist
	UserAccount userAccount;
	
	void setupRender()
	{
		String username = securityService.getSubject().getPrincipal().toString();
		userAccount = userAccountService.getUserAccountByUserID(username);
	}
}



package edu.ndsu.assignment3.services;

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

import edu.ndsu.assignment3.cayenne.persistent.UserAccount;

public class LocalSecurityRealm extends AuthorizingRealm {
	private CayenneService cayenneService;
	private UserAccountService userAccountService; 
	private Logger logger; 
	
	public LocalSecurityRealm(CayenneService cs, UserAccountService uas) {
		this.cayenneService = cs;
		this.userAccountService = uas;
		this.logger = LoggerFactory.getLogger(this.getClass());
		
		//copy-pasted code from assignment instructions
		ObjectContext context = cayenneService.newContext();
		UserAccount manager = userAccountService.createNewUserAccount(context);
		manager.setUserID("john.doe");
		manager.setName("John Doe");
		manager.setPassword("password");
		manager.setManager(true);
		manager.setEmployee(true);
		manager.setEmailAddress("john.doe@mysite.net");
		context.commitChanges();
		
		//create a non-manager employee / admin
		context = cayenneService.newContext();
		UserAccount admin = userAccountService.createNewUserAccount(context);
		admin.setUserID("spencer.marchus");
		admin.setName("Spencer Marchus");
		admin.setPassword("password");
		admin.setAdmin(true);
		admin.setEmployee(true);
		admin.setEmailAddress("spencer.marchus@ndsu.edu");
		context.commitChanges();

	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// This should never be null or empty, but just return if it's invalid
		if(principals == null || principals.isEmpty()) {
			return null;
		}
		// The primary principal will be the username when logging in
		String username = (String) principals.getPrimaryPrincipal();
		logger.info("Received authorization request for " + username);
		UserAccount userAccount = userAccountService.getUserAccountByUserID(username);
		
		// Add roles as appropriate 
		if(userAccount != null) {
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			if(userAccount.isAdmin()) authorizationInfo.addRole("admin");
			if(userAccount.isManager()) authorizationInfo.addRole("manager");
			if(userAccount.isEmployee()) authorizationInfo.addRole("employee");
			return authorizationInfo;
		}
		else {
			return null; 
		}
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		
		logger.info("Received authentication request for " + username);
		UserAccount userAccount = userAccountService.getUserAccountByUserID(username);
		
		if(userAccount != null) {
			// Get the hashed password and the salt used for the hash
			SimpleByteSource salt = new SimpleByteSource(userAccount.getPasswordSalt());
			String hash = userAccount.getPasswordHash();
			return new SimpleAuthenticationInfo(username, hash, salt, this.getName());
		}
		else {
			return null;
		}
	}
}



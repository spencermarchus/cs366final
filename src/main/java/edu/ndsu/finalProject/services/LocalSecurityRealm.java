package edu.ndsu.finalProject.services;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.SelectQuery;
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
		
		ObjectContext context = cayenneService.newContext();
		UserAccount is = userAccountService.createNewUserAccount(context);
		is.setEmail("spmarchus@gmail.com");
		is.setPassword("password");
		is.setInstructor(true);
		is.setInstructorsupervisor(true);
		context.commitChanges();
		
		//create a non-manager employee / admin
		context = cayenneService.newContext();
		UserAccount i = userAccountService.createNewUserAccount(context);
		i.setEmail("instructor@gmail.com");
		i.setPassword("password");
		i.setInstructor(true);
		is.setInstructorsupervisor(false);
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
				logger.info("Received authorization request for " + username);
				UserAccount userAccount = userAccountService.getUserAccountByEmail(username);
				
				// Add roles as appropriate 
				if(userAccount != null) {
					SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
					if(userAccount.isGuardian()) authorizationInfo.addRole("guardian");
					if(userAccount.isInstructor()) authorizationInfo.addRole("instructor");
					if(userAccount.isInstructorsupervisor()) authorizationInfo.addRole("supervisor");
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
		UserAccount userAccount = userAccountService.getUserAccountByEmail(username);
		
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



package edu.ndsu.finalProject.services;

import org.apache.cayenne.CayenneRuntimeException;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.ndsu.finalProject.cayenne.persistent.UserAccount;

public class UserAccountServiceImpl implements UserAccountService {
	
	private CayenneService 	cayenneSerivce;
	private Logger 			logger;
	
	public UserAccountServiceImpl(CayenneService cayenneService) {
		this.cayenneSerivce = cayenneService;
		logger = LoggerFactory.getLogger(this.getClass());
	}

	@Override
	public UserAccount getUserAccountByEmail(String userID) {
		logger.info("Getting UserAccount for " + userID);
		ObjectContext context = cayenneSerivce.newContext();
		try {
			UserAccount userAccount = ObjectSelect.query(UserAccount.class)
				.where(UserAccount.EMAIL.like(userID)).selectOne(context);
			if(userAccount == null) {
				logger.info("No account for " + userID);
			}
			return userAccount;
		}
		catch (CayenneRuntimeException e) {
			logger.info("More than one UserAccount was returned for " + userID);
			logger.info(e.toString(), e);
			return null; 
		}
	}

	@Override
	public UserAccount createNewUserAccount(ObjectContext context) {
		if(context == null) {
			context = cayenneSerivce.newContext();
		}
		UserAccount userAccount = context.newObject(UserAccount.class);
		userAccount.setPasswordSalt(new SecureRandomNumberGenerator().nextBytes().toHex());
		return userAccount;
	}

	@Override
	public void updateUserAccount(UserAccount userAccount) {
		userAccount.getObjectContext().commitChanges();
	}
}


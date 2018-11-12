package edu.ndsu.assignment3.services;

import org.apache.cayenne.ObjectContext;
import edu.ndsu.assignment3.cayenne.persistent.UserAccount;

public interface UserAccountService 
{
	UserAccount getUserAccountByUserID(String userID);	
	UserAccount createNewUserAccount(ObjectContext context);	
	void updateUserAccount(UserAccount userAccount);
}


package edu.ndsu.finalProject.services;

import org.apache.cayenne.ObjectContext;
import edu.ndsu.finalProject.cayenne.persistent.*;
public interface UserAccountService 
{
	UserAccount getUserAccountByEmail(String userID);	
	UserAccount createNewUserAccount(ObjectContext context);	
	void updateUserAccount(UserAccount userAccount);
}


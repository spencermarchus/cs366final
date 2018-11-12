package edu.ndsu.assignment3.cayenne.persistent;

import org.apache.shiro.crypto.hash.Sha512Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.ndsu.assignment3.cayenne.persistent.auto._UserAccount;

public class UserAccount extends _UserAccount {

    private static final long serialVersionUID = 1L; 
    
    public Integer getPK()
    {
    	if(getObjectId() != null && !getObjectId().isTemporary())
    	{
    		return (Integer) getObjectId().getIdSnapshot().get(PK_PK_COLUMN);
    	}
    	return null; 
    }
        
    public void setPassword(String password)
    {
    	// The class doesn't inject a Logger, but we can still create one inside of the method
    	Logger logger = LoggerFactory.getLogger(this.getClass());
    	logger.info("Setting password for " + getUserID());
        	
    	if(password != null && !password.isEmpty())
    	{
    		setPasswordHash(new Sha512Hash(password, getPasswordSalt()).toHex());
    	}
    }


}

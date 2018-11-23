package edu.ndsu.finalProject.cayenne.persistent;

import org.apache.shiro.crypto.hash.Sha512Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.ndsu.finalProject.cayenne.persistent.auto._Guardian;

public class Guardian extends _Guardian {

    private static final long serialVersionUID = 1L; 
    public void setPassword(String password)
    {
    	// The class doesn't inject a Logger, but we can still create one inside of the method
    	Logger logger = LoggerFactory.getLogger(this.getClass());
    	logger.info("Setting password for " + getEmail());
        	
    	if(password != null && !password.isEmpty())
    	{
    		setPasswordHash(new Sha512Hash(password, getPasswordSalt()).toHex());
    	}
    }
}

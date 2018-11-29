package edu.ndsu.finalProject.pages.guardians;

import javax.inject.Inject;

import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.util.SimpleByteSource;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.tynamo.security.services.SecurityService;
import edu.ndsu.finalProject.cayenne.persistent.Guardian;
import edu.ndsu.finalProject.cayenne.persistent.Instructor;
import edu.ndsu.finalProject.services.DatabaseService;
import edu.ndsu.finalProject.services.UserAccountService;

public class ChangePassword {
	@Inject
	private DatabaseService db;
	
	@Inject
	private UserAccountService uas;
	
	@InjectComponent
	private Form addForm;

	@Inject
	private SecurityService securityService;
	
	@Property
	@Persist
	private Guardian guardian;
		
	@Property
	private String username;
	
	@Property
	private String oldPassword;
	
	@Property
	private String newPassword;
	
	@Property
	private String newPasswordRepeat;
	
	void setupRender()
	{
		username = securityService.getSubject().getPrincipal().toString();
		guardian = uas.getGuardianByEmail(username);
	}
	/*
	boolean isInstructor(String username)
	{
		return uas.getInstructorByEmail(username) != null;
	
	}
	
	boolean isGuardian(String username)
	{
		return uas.getGuardianByEmail(username) != null;
	}
	*/
	void onValidateFromAddForm()
	{
		
		SimpleByteSource salt = new SimpleByteSource(guardian.getPasswordSalt());
		String hash = guardian.getPasswordHash();
		if(hash.equals(new Sha512Hash(oldPassword, salt).toHex()) && newPasswordRepeat.equals(newPassword))
		{
			guardian.setPassword(newPassword);
			
			//null out old values for security reasons
			newPassword = null;
			newPasswordRepeat = null;
			salt = null;
			hash = null;
		}else {
			addForm.recordError("Error: new passwords not equal, or current password is incorrect");
		}
		
		if(!(addForm.getHasErrors()))
			db.updateGuardian(guardian);
	
	}
	
	Object onSuccess()
	{
		return "index";
	}
}

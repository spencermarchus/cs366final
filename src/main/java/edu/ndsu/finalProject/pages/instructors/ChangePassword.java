package edu.ndsu.finalProject.pages.instructors;

import javax.inject.Inject;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.util.SimpleByteSource;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.tynamo.security.services.SecurityService;
import edu.ndsu.finalProject.cayenne.persistent.Instructor;
import edu.ndsu.finalProject.services.DatabaseService;
import edu.ndsu.finalProject.services.UserAccountService;

@RequiresRoles("instructor")
public class ChangePassword {
	@Inject
	private DatabaseService db;
	
	@Inject
	private UserAccountService uas;
	
	@InjectComponent
	private Form addForm;

	@Inject
	private AlertManager alertManager;
	
	@Inject
	private SecurityService securityService;
	
	@Property
	@Persist
	private Instructor instructor;
		
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
	
	}
	
	Object onActivate()
	{
		username = securityService.getSubject().getPrincipal().toString();
		instructor = uas.getInstructorByEmail(username);
		
		if(instructor == null)
			return "Index";
		return null;
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
		SimpleByteSource salt = new SimpleByteSource(instructor.getPasswordSalt());
		String hash = instructor.getPasswordHash();
		if(hash.equals(new Sha512Hash(oldPassword, salt).toHex()) && newPasswordRepeat.equals(newPassword))
		{
			instructor.setPassword(newPassword);
			
			//null out old values for security reasons
			newPassword = null;
			newPasswordRepeat = null;
			salt = null;
			hash = null;
		}else {
			addForm.recordError("Error: new passwords not equal, or current password is incorrect");
			salt = null;
			hash = null;
		}
		
		if(!(addForm.getHasErrors()))
		{
			db.updateInstructor(instructor);
			alertManager.success("Password changed successfully.");
		}
	}
	
	Object onSuccess()
	{
		return "index";
	}
}

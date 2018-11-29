package edu.ndsu.finalProject.pages;

import javax.inject.Inject;

import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import edu.ndsu.finalProject.cayenne.persistent.Guardian;
import edu.ndsu.finalProject.services.DatabaseService;

public class GuardianSignUp {
	@Inject
	private DatabaseService db;

	@Property
	private String password;
	
	@InjectComponent
	private Form addForm;

	  @Inject
	  private AlertManager alertManager;
	  
	@Property
	@Persist
	private Guardian guardian;

	void setupRender() {
		guardian = db.getNewGuardian();
	}

	void onValidateFromAddForm() {
		if(guardian.getName() == null || guardian.getName().trim().isEmpty()) {
			addForm.recordError("You must specify a name.");
		}
		if(guardian.getPhone() == null || guardian.getPhone().trim().isEmpty()) {
			addForm.recordError("You must specify a phone number.");
		}
		if(guardian.getAddress() == null || guardian.getAddress().trim().isEmpty()) {
			addForm.recordError("You must specify an address.");
		}
		if(guardian.getEmail() == null || guardian.getEmail().trim().isEmpty()) {
			addForm.recordError("You must specify an email.");
		}
		
		if(password == null || password.isEmpty())
		{
			addForm.recordError("You must specify a password.");
		}else {
			//password valid, update field
			guardian.setPassword(password);
		}
		
		// Only save changes to the database if there were no errors
		if(!addForm.getHasErrors()) {
			db.updateGuardian(guardian);
		}
	}
	
	Object onSuccess() {
		alertManager.success("Account created - please log in");
		return "Login";
	}
}

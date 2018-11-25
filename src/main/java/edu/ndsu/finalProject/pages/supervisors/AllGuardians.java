package edu.ndsu.finalProject.pages.supervisors;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.tynamo.security.services.SecurityService;

import edu.ndsu.finalProject.cayenne.persistent.Guardian;
import edu.ndsu.finalProject.cayenne.persistent.UserAccount;
import edu.ndsu.finalProject.services.DatabaseService;
import edu.ndsu.finalProject.services.UserAccountService;

@RequiresRoles("supervisor")
public class AllGuardians {
	
	@Inject
	private DatabaseService db;

	@Property
	private List<Guardian> guardians; // list for grid

	@Property
	private Guardian guardian; // row for grid

	void setupRender() {
		guardians = db.getAllGuardians();
	}
}

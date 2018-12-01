package edu.ndsu.finalProject.pages.supervisors;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.annotations.Property;
import edu.ndsu.finalProject.cayenne.persistent.InstructorWorking;
import edu.ndsu.finalProject.cayenne.persistent.Shift;
import edu.ndsu.finalProject.services.DatabaseService;

@RequiresRoles("supervisor")
public class AllShifts {
	@Inject
	private DatabaseService db;

	@Property
	private List<Shift> shifts; // list for grid

	@Property
	private Shift shift; // row for grid

	void setupRender() {
		
		shifts = db.getAllShifts(null);
	}
}

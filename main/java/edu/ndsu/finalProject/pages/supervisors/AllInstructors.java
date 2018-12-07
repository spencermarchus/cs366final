package edu.ndsu.finalProject.pages.supervisors;

import java.util.List;
import javax.inject.Inject;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.annotations.Property;
import edu.ndsu.finalProject.cayenne.persistent.Instructor;
import edu.ndsu.finalProject.services.DatabaseService;

@RequiresRoles("supervisor")
public class AllInstructors {
	@Inject
	private DatabaseService db;

	@Property
	private List<Instructor> instructors; // list for grid

	@Property
	private Instructor instructor; // row for grid

	void setupRender() {
		instructors = db.getAllInstructors();
	}
}

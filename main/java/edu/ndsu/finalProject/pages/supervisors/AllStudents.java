package edu.ndsu.finalProject.pages.supervisors;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.annotations.Property;


import edu.ndsu.finalProject.cayenne.persistent.Student;
import edu.ndsu.finalProject.services.DatabaseService;

@RequiresRoles("supervisor")
public class AllStudents {
	@Inject
	private DatabaseService db;

	@Property
	private List<Student> students; // list for grid

	@Property
	private Student student; // row for grid

	void setupRender() {
		students = db.getAllStudents();
	}
}

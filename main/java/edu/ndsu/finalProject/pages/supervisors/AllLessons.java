package edu.ndsu.finalProject.pages.supervisors;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.annotations.Property;

import edu.ndsu.finalProject.cayenne.persistent.Lesson;
import edu.ndsu.finalProject.services.DatabaseService;

@RequiresRoles("supervisor")
public class AllLessons {
	@Inject
	private DatabaseService db;

	@Property
	private List<Lesson> lessons; // list for grid

	@Property
	private Lesson lesson; // row for grid

	void setupRender() {
		lessons = db.getAllLessons();
	}
}

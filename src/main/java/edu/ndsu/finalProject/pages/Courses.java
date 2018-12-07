package edu.ndsu.finalProject.pages;

import java.util.List;

import javax.inject.Inject;

import org.apache.tapestry5.annotations.Property;


import edu.ndsu.finalProject.cayenne.persistent.Course;
import edu.ndsu.finalProject.services.DatabaseService;


public class Courses {
	@Inject
	private DatabaseService db;

	@Property
	private List<Course> courses; // list for grid

	@Property
	private Course course; // row for grid

	void setupRender() {
		courses = db.getAllCourses(null);
	}

}

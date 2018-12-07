package edu.ndsu.finalProject.services;

import org.apache.cayenne.ObjectContext;
import org.apache.tapestry5.ValueEncoder;

import edu.ndsu.finalProject.cayenne.persistent.Course;


public class CourseValueEncoder implements ValueEncoder<Course>{
	private DatabaseService databaseService;
	private ObjectContext context;

	public CourseValueEncoder(DatabaseService databaseService, ObjectContext context) {
		this.databaseService = databaseService;
		this.context = context;
	}

	public Course toValue(String clientValue) {
		return databaseService.getCourseByPK(context, Integer.parseInt(clientValue));
	}

	public String toClient(Course value) {
		return String.valueOf(value.getPK());
	}

}

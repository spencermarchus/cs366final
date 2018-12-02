package edu.ndsu.finalProject.services;

import org.apache.cayenne.ObjectContext;
import org.apache.tapestry5.ValueEncoder;

import edu.ndsu.finalProject.cayenne.persistent.Student;


public class StudentValueEncoder implements ValueEncoder<Student>{
	private DatabaseService databaseService;
	private ObjectContext context;

	public StudentValueEncoder(DatabaseService databaseService, ObjectContext context) {
		this.databaseService = databaseService;
		this.context = context;
	}

	public Student toValue(String clientValue) {
		return databaseService.getStudentByPK(context, Integer.parseInt(clientValue));
	}

	public String toClient(Student value) {
		return String.valueOf(value.getPK());
	}

}

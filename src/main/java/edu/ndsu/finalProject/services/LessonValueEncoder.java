package edu.ndsu.finalProject.services;

import org.apache.cayenne.ObjectContext;
import org.apache.tapestry5.ValueEncoder;

import edu.ndsu.finalProject.cayenne.persistent.Lesson;


public class LessonValueEncoder implements ValueEncoder<Lesson>{
	private DatabaseService databaseService;
	private ObjectContext context;

	public LessonValueEncoder(DatabaseService databaseService, ObjectContext context) {
		this.databaseService = databaseService;
		this.context = context;
	}

	public Lesson toValue(String clientValue) {
		return databaseService.getLessonByPK(context, Integer.parseInt(clientValue));
	}

	public String toClient(Lesson value) {
		return String.valueOf(value.getPK());
	}

}

package edu.ndsu.finalProject.pages.guardians;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.Form;
import org.tynamo.security.services.SecurityService;

import edu.ndsu.finalProject.cayenne.persistent.Course;
import edu.ndsu.finalProject.cayenne.persistent.Guardian;
import edu.ndsu.finalProject.cayenne.persistent.Guardianship;
import edu.ndsu.finalProject.cayenne.persistent.Lesson;
import edu.ndsu.finalProject.cayenne.persistent.Student;
import edu.ndsu.finalProject.services.DatabaseService;
import edu.ndsu.finalProject.services.UserAccountService;


@RequiresRoles("guardian")
public class ViewStudent {
	@Inject
	private DatabaseService db;

	@Property
	@Persist
	private Student student;

	@Property
	@Persist
	private String PK;
	
	@Inject
	UserAccountService uas;
	
	@Property
	private String username;
	
	@Property
	private Lesson lesson;
	
	@Property
	private List<Lesson> lessons;

	void setupRender() {
		student = db.getStudentByPK(null, Integer.parseInt(PK));
		lessons = db.getLessonsByStudent(student);
	}

	String onPassivate()
	{
		return String.valueOf(PK);
	}
	
	public void onActivate(int pK) {
		PK = String.valueOf(pK);
	}
}

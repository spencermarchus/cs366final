package edu.ndsu.finalProject.pages.supervisors;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.services.SelectModelFactory;
import edu.ndsu.finalProject.cayenne.persistent.InstructorWorking;
import edu.ndsu.finalProject.services.DatabaseService;

@RequiresRoles("supervisor")
public class AddInstructorShift {
	
	
	@Inject
	private DatabaseService db;
	
	@Property
	private String tempPassword;

	@InjectComponent
	private Form addForm;
	
	@Property
	@Persist
	private InstructorWorking iw;

	@Property
	private SelectModel selectModelInstructor;
	
	@Property
	private SelectModel selectModelLessonDate;
	
	@Property
	private String selectedInstructor;
	
	@Property
	@Persist(PersistenceConstants.FLASH)
	private List<String> selectedInstructors;
	
	@Property
	private String selectedLessonDate;
	
	
	@Inject
	private SelectModelFactory selectModelFactory;
		
	void setupRender() {
		iw = db.getNewInstructorWorking();
		
		
	}

	void onValidateFromAddForm(){
		if(selectedLessonDate == null)
		{
			addForm.recordError("You must specify a lesson date.");
		}
		
		if(selectedInstructors.isEmpty())
			addForm.recordError("You must specify instructors.");
				
		if(!addForm.getHasErrors())
		{
			
			for(String s : selectedInstructors)
			{
				
				InstructorWorking iw = db.getNewInstructorWorking();
				iw.setInstructorId(db.getInstructorForName(null, s).getPK());
				iw.setDateId(db.getLessonDateByToString(null, selectedLessonDate).getPK());
				
				if(!(db.shiftExists(iw)))
					iw.getObjectContext().commitChanges();
			}
		}
	}
	
	void onPrepare() {
		if(selectedInstructors == null)
			selectedInstructors = new ArrayList<String>();
		
        // Get all persons - ask business service to find them (from the database)
        List<String> instructorNames = db.getAllInstructorNames();
        List<String> ldStrings = db.getAllLessonDateStrings();
        
        selectModelInstructor = selectModelFactory.create(instructorNames);
        selectModelLessonDate = selectModelFactory.create(ldStrings);
    }
	
	Object onSuccess() {
		if(!addForm.getHasErrors())
			return "supervisors/AllShifts";
		
		return this;
	}
	
	
	 public ValueEncoder<String> getInstructorEncoder() {

	        return new ValueEncoder<String>() {

	            @Override
	            public String toClient(String value) {
	                // return the given object's ID
	                return value;
	            }

	            @Override
	            public String toValue(String id) { 
	                // find the Instructor object of the given ID in the database
	                return id;
	            }
	        }; 
	    }
	 /*
	 public ValueEncoder<LessonDate> getLessonDateEncoder() {

	        return new ValueEncoder<LessonDate>() {

	            @Override
	            public String toClient(LessonDate value) {
	                // return the given object's ID
	                return String.valueOf(value.getPK()); 
	            }

	            @Override
	            public LessonDate toValue(String id) { 
	                // find the Instructor object of the given ID in the database
	                return db.getLessonDateByPK(null, Integer.parseInt(id)); 
	            }
	        }; 
	    }
	*/
	
}

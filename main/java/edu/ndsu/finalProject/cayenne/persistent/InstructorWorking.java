package edu.ndsu.finalProject.cayenne.persistent;

import edu.ndsu.finalProject.cayenne.persistent.auto._InstructorWorking;

public class InstructorWorking extends _InstructorWorking {

    private static final long serialVersionUID = 1L; 
    public Integer getPK()
    {
    	if(getObjectId() != null && !getObjectId().isTemporary())
    	{
    		return (Integer) getObjectId().getIdSnapshot().get(WORKING_ID_PK_COLUMN);
    	}
    	return null; 
    }
}

package edu.ndsu.finalProject.cayenne.persistent;

import edu.ndsu.finalProject.cayenne.persistent.auto._Enrollment;

public class Enrollment extends _Enrollment {

    private static final long serialVersionUID = 1L; 
    public Integer getPK()
    {
    	if(getObjectId() != null && !getObjectId().isTemporary())
    	{
    		return (Integer) getObjectId().getIdSnapshot().get(ENROLLMENT_ID_PK_COLUMN);
    	}
    	return null; 
    }
    
    public boolean equals(Enrollment e)
    {
    	return (e.getLesson().getPK() == this.getLesson().getPK()
    		&& e.getStudent().getPK() == this.getStudent().getPK());
    }
}

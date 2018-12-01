package edu.ndsu.finalProject.cayenne.persistent;

import edu.ndsu.finalProject.cayenne.persistent.auto._Course;

public class Course extends _Course {

    private static final long serialVersionUID = 1L; 
    public Integer getPK()
    {
    	if(getObjectId() != null && !getObjectId().isTemporary())
    	{
    		return (Integer) getObjectId().getIdSnapshot().get(COURSE_ID_PK_COLUMN);
    	}
    	
    	return null; 
    }
    
    public boolean equals(Course c)
    {
    	return true;
    }
    
    public String toString()
    {
    	return this.getName();
    }
}

package edu.ndsu.finalProject.cayenne.persistent;

import edu.ndsu.finalProject.cayenne.persistent.auto._LessonDate;

public class LessonDate extends _LessonDate {

    private static final long serialVersionUID = 1L; 
    public Integer getPK()
    {
    	if(getObjectId() != null && !getObjectId().isTemporary())
    	{
    		return (Integer) getObjectId().getIdSnapshot().get(DATE_ID_PK_COLUMN);
    	}
    	return null; 
    }
}

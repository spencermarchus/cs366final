package edu.ndsu.finalProject.cayenne.persistent;

import edu.ndsu.finalProject.cayenne.persistent.auto._Lesson;

public class Lesson extends _Lesson {

    private static final long serialVersionUID = 1L; 
    public Integer getPK()
    {
    	if(getObjectId() != null && !getObjectId().isTemporary())
    	{
    		return (Integer) getObjectId().getIdSnapshot().get(LESSON_ID_PK_COLUMN);
    	}
    	return null; 
    }
}

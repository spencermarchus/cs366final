package edu.ndsu.finalProject.cayenne.persistent;

import edu.ndsu.finalProject.cayenne.persistent.auto._Guardianship;

public class Guardianship extends _Guardianship {

    private static final long serialVersionUID = 1L; 
    public Integer getPK()
    {
    	if(getObjectId() != null && !getObjectId().isTemporary())
    	{
    		return (Integer) getObjectId().getIdSnapshot().get(GS_ID_PK_COLUMN);
    	}
    	return null; 
    }
}

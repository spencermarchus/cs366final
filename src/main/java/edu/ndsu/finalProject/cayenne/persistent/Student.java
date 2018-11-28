package edu.ndsu.finalProject.cayenne.persistent;

import edu.ndsu.finalProject.cayenne.persistent.auto._Student;

public class Student extends _Student {

    private static final long serialVersionUID = 1L; 
    public Integer getPK()
    {
    	if(getObjectId() != null && !getObjectId().isTemporary())
    	{
    		return (Integer) getObjectId().getIdSnapshot().get(STUDENT_ID_PK_COLUMN);
    	}
    	return null; 
    }
    
    public void graduate()
    {
    	if(this.getRecommendedLevel().equals("red"))
    		this.setRecommendedLevel("white");
    	
    	if(this.getRecommendedLevel().equals("white"))
    		this.setRecommendedLevel("blue");
    }
}

package edu.ndsu.finalProject.cayenne.persistent.auto;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

/**
 * Class _Guardianship was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Guardianship extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String GS_ID_PK_COLUMN = "gs_id";

    public static final Property<Integer> GUARDIAN_ID = Property.create("guardianId", Integer.class);
    public static final Property<Integer> STUDENT_ID = Property.create("studentId", Integer.class);

    public void setGuardianId(int guardianId) {
        writeProperty("guardianId", guardianId);
    }
    public int getGuardianId() {
        Object value = readProperty("guardianId");
        return (value != null) ? (Integer) value : 0;
    }

    public void setStudentId(int studentId) {
        writeProperty("studentId", studentId);
    }
    public int getStudentId() {
        Object value = readProperty("studentId");
        return (value != null) ? (Integer) value : 0;
    }

}
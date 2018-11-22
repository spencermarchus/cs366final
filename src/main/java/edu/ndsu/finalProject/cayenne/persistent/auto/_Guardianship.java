package edu.ndsu.finalProject.cayenne.persistent.auto;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

import edu.ndsu.finalProject.cayenne.persistent.Guardian;
import edu.ndsu.finalProject.cayenne.persistent.Student;

/**
 * Class _Guardianship was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Guardianship extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String GS_ID_PK_COLUMN = "gs_id";

    public static final Property<Guardian> GUARDIAN = Property.create("guardian", Guardian.class);
    public static final Property<Student> STUDENT = Property.create("student", Student.class);

    public void setGuardian(Guardian guardian) {
        setToOneTarget("guardian", guardian, true);
    }

    public Guardian getGuardian() {
        return (Guardian)readProperty("guardian");
    }


    public void setStudent(Student student) {
        setToOneTarget("student", student, true);
    }

    public Student getStudent() {
        return (Student)readProperty("student");
    }


}
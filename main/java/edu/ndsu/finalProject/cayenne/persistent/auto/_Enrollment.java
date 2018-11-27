package edu.ndsu.finalProject.cayenne.persistent.auto;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

import edu.ndsu.finalProject.cayenne.persistent.Lesson;
import edu.ndsu.finalProject.cayenne.persistent.Student;

/**
 * Class _Enrollment was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Enrollment extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ENROLLMENT_ID_PK_COLUMN = "enrollment_id";

    public static final Property<Lesson> LESSON = Property.create("lesson", Lesson.class);
    public static final Property<Student> STUDENT = Property.create("student", Student.class);

    public void setLesson(Lesson lesson) {
        setToOneTarget("lesson", lesson, true);
    }

    public Lesson getLesson() {
        return (Lesson)readProperty("lesson");
    }


    public void setStudent(Student student) {
        setToOneTarget("student", student, true);
    }

    public Student getStudent() {
        return (Student)readProperty("student");
    }


}
package edu.ndsu.finalProject.cayenne.persistent.auto;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

import edu.ndsu.finalProject.cayenne.persistent.InstructorWorking;

/**
 * Class _Instructor was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Instructor extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String INSTRUCTOR_ID_PK_COLUMN = "instructor_id";

    public static final Property<String> ADDRESS = Property.create("address", String.class);
    public static final Property<String> DATE_OF_BIRTH = Property.create("dateOfBirth", String.class);
    public static final Property<String> EMAIL = Property.create("email", String.class);
    public static final Property<String> F_NAME = Property.create("fName", String.class);
    public static final Property<String> L_NAME = Property.create("lName", String.class);
    public static final Property<String> PASSWORD_HASH = Property.create("passwordHash", String.class);
    public static final Property<String> PASSWORD_SALT = Property.create("passwordSalt", String.class);
    public static final Property<String> PHONE = Property.create("phone", String.class);
    public static final Property<Boolean> SUPERVISOR = Property.create("supervisor", Boolean.class);
    public static final Property<Integer> USER_ID = Property.create("userID", Integer.class);
    public static final Property<Float> WAGE = Property.create("wage", Float.class);
    public static final Property<List<InstructorWorking>> WORKINGS = Property.create("workings", List.class);

    public void setAddress(String address) {
        writeProperty("address", address);
    }
    public String getAddress() {
        return (String)readProperty("address");
    }

    public void setDateOfBirth(String dateOfBirth) {
        writeProperty("dateOfBirth", dateOfBirth);
    }
    public String getDateOfBirth() {
        return (String)readProperty("dateOfBirth");
    }

    public void setEmail(String email) {
        writeProperty("email", email);
    }
    public String getEmail() {
        return (String)readProperty("email");
    }

    public void setFName(String fName) {
        writeProperty("fName", fName);
    }
    public String getFName() {
        return (String)readProperty("fName");
    }

    public void setLName(String lName) {
        writeProperty("lName", lName);
    }
    public String getLName() {
        return (String)readProperty("lName");
    }

    public void setPasswordHash(String passwordHash) {
        writeProperty("passwordHash", passwordHash);
    }
    public String getPasswordHash() {
        return (String)readProperty("passwordHash");
    }

    public void setPasswordSalt(String passwordSalt) {
        writeProperty("passwordSalt", passwordSalt);
    }
    public String getPasswordSalt() {
        return (String)readProperty("passwordSalt");
    }

    public void setPhone(String phone) {
        writeProperty("phone", phone);
    }
    public String getPhone() {
        return (String)readProperty("phone");
    }

    public void setSupervisor(boolean supervisor) {
        writeProperty("supervisor", supervisor);
    }
	public boolean isSupervisor() {
        Boolean value = (Boolean)readProperty("supervisor");
        return (value != null) ? value.booleanValue() : false;
    }

    public void setUserID(int userID) {
        writeProperty("userID", userID);
    }
    public int getUserID() {
        Object value = readProperty("userID");
        return (value != null) ? (Integer) value : 0;
    }

    public void setWage(float wage) {
        writeProperty("wage", wage);
    }
    public float getWage() {
        Object value = readProperty("wage");
        return (value != null) ? (Float) value : 0;
    }

    public void addToWorkings(InstructorWorking obj) {
        addToManyTarget("workings", obj, true);
    }
    public void removeFromWorkings(InstructorWorking obj) {
        removeToManyTarget("workings", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<InstructorWorking> getWorkings() {
        return (List<InstructorWorking>)readProperty("workings");
    }


}

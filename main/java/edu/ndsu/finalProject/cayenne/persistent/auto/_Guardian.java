package edu.ndsu.finalProject.cayenne.persistent.auto;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

/**
 * Class _Guardian was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Guardian extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String GUARDIAN_ID_PK_COLUMN = "guardian_id";

    public static final Property<String> ADDRESS = Property.create("address", String.class);
    public static final Property<String> EMAIL = Property.create("email", String.class);
    public static final Property<String> F_NAME = Property.create("fName", String.class);
    public static final Property<String> L_NAME = Property.create("lName", String.class);
    public static final Property<String> PASSWORD_HASH = Property.create("passwordHash", String.class);
    public static final Property<String> PASSWORD_SALT = Property.create("passwordSalt", String.class);
    public static final Property<String> PHONE = Property.create("phone", String.class);

    public void setAddress(String address) {
        writeProperty("address", address);
    }
    public String getAddress() {
        return (String)readProperty("address");
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

}

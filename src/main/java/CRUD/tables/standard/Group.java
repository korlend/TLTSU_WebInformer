package CRUD.tables.standard;

import CRUD.tables.Table;

/**
 * Created by Артем on 14.05.2016.
 */
public class Group implements Table {
    private int OID;
    private int Course;
    private String Name;

    public void setCourse(int course) {
        Course = course;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public int getCourse() {
        return Course;
    }

    public int getOID() {
        return OID;
    }

    public String getName() {
        return Name;
    }
}

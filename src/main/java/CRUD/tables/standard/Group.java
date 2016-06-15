package CRUD.tables.standard;

import CRUD.tables.Table;

/**
 * Created by Артем on 14.05.2016.
 */
public class Group implements Table {
    private int OID;
    private int Course;
    private String Name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;

        Group group = (Group) o;

        if (getOID() != group.getOID()) return false;
        if (getCourse() != group.getCourse()) return false;
        return !(getName() != null ? !getName().equals(group.getName()) : group.getName() != null);

    }

    @Override
    public int hashCode() {
        int result = getOID();
        result = 31 * result + getCourse();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

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

package com.korlend.CRUD.tables.standard;

import com.korlend.CRUD.tables.Table;

import java.util.Comparator;

/**
 * Created by Артем on 14.05.2016.
 */
public class Group implements Comparator, Comparable, Table {
    private int OID;
    private int Course;
    private String Name;
    private int Faculty;

    public int getFaculty() {
        return Faculty;
    }

    public void setFaculty(int faculty) {
        Faculty = faculty;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return ((Group) o1).getOID() < ((Group) o2).getOID() ? -1 :
                ((Group) o1).getOID() > ((Group) o2).getOID() ? 1 : 0;
    }

    @Override
    public int compareTo(Object o) {
        return this.compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;

        Group group = (Group) o;

        if (getOID() != group.getOID()) return false;
        if (getFaculty() != group.getFaculty()) return false;
        if (getCourse() != group.getCourse()) return false;
        return !(getName() != null ? !getName().equals(group.getName()) : group.getName() != null);

    }

    @Override
    public int hashCode() {
        int result = getOID();
        result = 31 * result + getCourse();
        result = 31 * result + getFaculty();
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

    public String getUNIName() {
        return Integer.toString(OID);
    }

    @Override
    public String toString() {
        return "Group{" +
                "OID=" + OID +
                ", Course=" + Course +
                ", Faculty=" + Faculty +
                ", Name='" + Name + '\'' +
                '}';
    }
}

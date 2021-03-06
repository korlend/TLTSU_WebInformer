package com.korlend.CRUD.tables.standard;

import com.korlend.CRUD.tables.Table;

import java.util.Comparator;

/**
 * Created by Артем on 14.05.2016.
 */
public class Chair implements Comparator, Comparable, Table {
    private int OID;
    private String Name;
    private int Auditorium;
    private int Faculty;

    public int getFaculty() {
        return Faculty;
    }

    public void setFaculty(int faculty) {
        Faculty = faculty;
    }

    public void setAuditorium(int auditorium) {
        Auditorium = auditorium;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public int getAuditorium() {
        return Auditorium;
    }

    public int getOID() {
        return OID;
    }

    public String getName() {
        return Name;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return ((Chair) o1).getOID() < ((Chair) o2).getOID() ? -1 :
                ((Chair) o1).getOID() > ((Chair) o2).getOID() ? 1 : 0;
    }

    @Override
    public int compareTo(Object o) {
        return this.compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chair)) return false;

        Chair chair = (Chair) o;

        if (getOID() != chair.getOID()) return false;
        if (getFaculty() != chair.getFaculty()) return false;
        if (getAuditorium() != chair.getAuditorium()) return false;
        return !(getName() != null ? !getName().equals(chair.getName()) : chair.getName() != null);

    }

    @Override
    public int hashCode() {
        int result = getOID();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getAuditorium();
        result = 31 * result + getFaculty();
        return result;
    }

    public String getUNIName() {
        return Integer.toString(OID);
    }

    @Override
    public String toString() {
        return "Chair{" +
                "OID=" + OID +
                ", Name='" + Name + '\'' +
                ", Faculty='" + Faculty + '\'' +
                ", Auditorium=" + Auditorium +
                '}';
    }
}

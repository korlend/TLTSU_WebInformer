package com.korlend.CRUD.tables.standard;

import com.korlend.CRUD.tables.Table;

import java.util.Comparator;

/**
 * Created by Артем on 14.05.2016.
 */
public class Lecturer implements Comparator, Comparable, Table {
    private int OID;
    private String FIO;
    private int Chair;

    @Override
    public int compare(Object o1, Object o2) {
        return ((Lecturer) o1).getOID() < ((Lecturer) o2).getOID() ? -1 :
                ((Lecturer) o1).getOID() > ((Lecturer) o2).getOID() ? 1 : 0;
    }

    @Override
    public int compareTo(Object o) {
        return this.compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lecturer)) return false;

        Lecturer lecturer = (Lecturer) o;

        if (getOID() != lecturer.getOID()) return false;
        if (getChair() != lecturer.getChair()) return false;
        return !(getFIO() != null ? !getFIO().equals(lecturer.getFIO()) : lecturer.getFIO() != null);

    }

    @Override
    public int hashCode() {
        int result = getOID();
        result = 31 * result + (getFIO() != null ? getFIO().hashCode() : 0);
        result = 31 * result + getChair();
        return result;
    }

    public void setChair(int chair) {
        Chair = chair;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public int getChair() {
        return Chair;
    }

    public int getOID() {
        return OID;
    }

    public String getFIO() {
        return FIO;
    }

    public String getUNIName() {
        return Integer.toString(OID);
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "OID=" + OID +
                ", FIO='" + FIO + '\'' +
                ", Chair=" + Chair +
                '}';
    }
}

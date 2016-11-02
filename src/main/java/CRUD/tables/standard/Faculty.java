package CRUD.tables.standard;

import CRUD.tables.Table;

import java.util.Comparator;

/**
 * Created by Артем on 03.09.2016.
 */
public class Faculty implements Comparator, Comparable, Table {
    private int OID;
    private String Abbr;
    private String Name;

    @Override
    public int compare(Object o1, Object o2) {
        return ((Faculty) o1).getOID() < ((Faculty) o2).getOID() ? -1 :
                ((Faculty) o1).getOID() > ((Faculty) o2).getOID() ? 1 : 0;
    }

    @Override
    public int compareTo(Object o) {
        return this.compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty)) return false;

        Faculty faculty = (Faculty) o;

        if (getOID() != faculty.getOID()) return false;
        if (getAbbr() != null ? !getAbbr().equals(faculty.getAbbr()) : faculty.getAbbr() != null) return false;
        return !(getName() != null ? !getName().equals(faculty.getName()) : faculty.getName() != null);

    }

    @Override
    public int hashCode() {
        int result = getOID();
        result = 31 * result + (getAbbr() != null ? getAbbr().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public String getAbbr() {
        return Abbr;
    }

    public void setAbbr(String abbr) {
        Abbr = abbr;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUNIName() {
        return Integer.toString(OID);
    }

    @Override
    public String toString() {
        return "Group{" +
                "OID=" + OID +
                ", Abbr=" + Abbr +
                ", Name='" + Name + '\'' +
                '}';
    }
}

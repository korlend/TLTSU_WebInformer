package CRUD.tables.standard;

import CRUD.tables.Table;

import java.util.Comparator;

/**
 * Created by Артем on 14.05.2016.
 */
public class Stream implements Comparator, Comparable, Table {
    private int OID;
    private String Name;

    public void setName(String name) {
        Name = name;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return ((Stream) o1).getOID() < ((Stream) o2).getOID() ? -1 :
                ((Stream) o1).getOID() > ((Stream) o2).getOID() ? 1 : 0;
    }

    @Override
    public int compareTo(Object o) {
        return this.compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stream)) return false;

        Stream stream = (Stream) o;

        if (getOID() != stream.getOID()) return false;
        return !(getName() != null ? !getName().equals(stream.getName()) : stream.getName() != null);

    }

    @Override
    public int hashCode() {
        int result = getOID();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    public void setOID(int OID) {
        this.OID = OID;
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
        return "Stream{" +
                "OID=" + OID +
                ", Name='" + Name + '\'' +
                '}';
    }
}

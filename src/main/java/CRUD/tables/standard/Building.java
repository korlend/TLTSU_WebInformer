package CRUD.tables.standard;

import CRUD.tables.Table;

import java.util.Comparator;

/**
 * Created by Артем on 14.05.2016.
 */
public class Building implements Comparator, Comparable, Table {
    private int OID;
    private String Name;
    private String Address;

    public void setAddress(String address) {
        Address = address;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public int getOID() {
        return OID;
    }

    public String getAddress() {
        return Address;
    }

    public String getName() {
        return Name;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return ((Building) o1).getOID() < ((Building) o2).getOID() ? -1 :
                ((Building) o1).getOID() > ((Building) o2).getOID() ? 1 : 0;
    }

    @Override
    public int compareTo(Object o) {
        return this.compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Building)) return false;

        Building building = (Building) o;

        if (getOID() != building.getOID()) return false;
        return !(getName() != null ? !getName().equals(building.getName()) : building.getName() != null);

    }

    @Override
    public int hashCode() {
        int result = getOID();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }

    public String getUNIName() {
        return Integer.toString(OID);
    }
}

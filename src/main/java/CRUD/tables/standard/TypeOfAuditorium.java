package CRUD.tables.standard;

import CRUD.tables.Table;

/**
 * Created by Артем on 14.05.2016.
 */
public class TypeOfAuditorium implements Table {
    private int OID;
    private String Name;

    public void setName(String name) {
        Name = name;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeOfAuditorium)) return false;

        TypeOfAuditorium that = (TypeOfAuditorium) o;

        if (getOID() != that.getOID()) return false;
        return !(getName() != null ? !getName().equals(that.getName()) : that.getName() != null);

    }

    @Override
    public int hashCode() {
        int result = getOID();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    public int getOID() {
        return OID;
    }

    public String getName() {
        return Name;
    }
}

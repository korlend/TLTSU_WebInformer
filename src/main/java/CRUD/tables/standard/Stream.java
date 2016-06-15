package CRUD.tables.standard;

import CRUD.tables.Table;

/**
 * Created by Артем on 14.05.2016.
 */
public class Stream implements Table {
    private int OID;
    private String Name;

    public void setName(String name) {
        Name = name;
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
}

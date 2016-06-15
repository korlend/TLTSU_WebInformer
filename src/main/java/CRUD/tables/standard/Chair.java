package CRUD.tables.standard;

import CRUD.tables.Table;

/**
 * Created by Артем on 14.05.2016.
 */
public class Chair implements Table {
    private int OID;
    private String Name;
    private int Auditorium;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chair)) return false;

        Chair chair = (Chair) o;

        if (getOID() != chair.getOID()) return false;
        if (getAuditorium() != chair.getAuditorium()) return false;
        return !(getName() != null ? !getName().equals(chair.getName()) : chair.getName() != null);

    }

    @Override
    public int hashCode() {
        int result = getOID();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getAuditorium();
        return result;
    }
}

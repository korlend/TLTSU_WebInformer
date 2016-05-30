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
}

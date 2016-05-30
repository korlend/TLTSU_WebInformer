package CRUD.tables.standard;

import CRUD.tables.Table;

/**
 * Created by Артем on 14.05.2016.
 */

public class Auditorium implements Table {
    private int OID;
    private String Name;
    private String Abbr;
    private int Building;
    private int TypeOfAuditorium;

    public void setAbbr(String abbr) {
        Abbr = abbr;
    }

    public void setBuilding(int building) {
        Building = building;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public void setTypeOfAuditorium(int typeOfAuditorium) {
        TypeOfAuditorium = typeOfAuditorium;
    }

    public int getBuilding() {
        return Building;
    }

    public int getOID() {
        return OID;
    }

    public int getTypeOfAuditorium() {
        return TypeOfAuditorium;
    }

    public String getAbbr() {
        return Abbr;
    }

    public String getName() {
        return Name;
    }
}

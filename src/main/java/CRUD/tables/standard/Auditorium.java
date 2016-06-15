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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Auditorium)) return false;

        Auditorium that = (Auditorium) o;

        if (getOID() != that.getOID()) return false;
        if (getBuilding() != that.getBuilding()) return false;
        if (getTypeOfAuditorium() != that.getTypeOfAuditorium()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return !(getAbbr() != null ? !getAbbr().equals(that.getAbbr()) : that.getAbbr() != null);

    }

    @Override
    public int hashCode() {
        int result = getOID();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAbbr() != null ? getAbbr().hashCode() : 0);
        result = 31 * result + getBuilding();
        result = 31 * result + getTypeOfAuditorium();
        return result;
    }

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

package CRUD.tables.standard;

import CRUD.tables.Table;

import java.util.Comparator;

/**
 * Created by Артем on 14.05.2016.
 */
public class KindOfWork implements Comparator, Comparable, Table {
    private int OID;
    private String Name;
    private String Abbr;

    @Override
    public int compare(Object o1, Object o2) {
        return ((KindOfWork) o1).getOID() < ((KindOfWork) o2).getOID() ? -1 :
                ((KindOfWork) o1).getOID() > ((KindOfWork) o2).getOID() ? 1 : 0;
    }

    @Override
    public int compareTo(Object o) {
        return this.compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KindOfWork)) return false;

        KindOfWork that = (KindOfWork) o;

        if (getOID() != that.getOID()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return !(getAbbr() != null ? !getAbbr().equals(that.getAbbr()) : that.getAbbr() != null);

    }

    @Override
    public int hashCode() {
        int result = getOID();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAbbr() != null ? getAbbr().hashCode() : 0);
        return result;
    }

    public void setAbbr(String abbr) {
        Abbr = abbr;
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

    public String getAbbr() {
        return Abbr;
    }

    public String getName() {
        return Name;
    }
}

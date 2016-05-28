package CRUD.tables;

/**
 * Created by Артем on 14.05.2016.
 */
public class KindOfWork implements Table {
    private int OID;
    private String Name;
    private String Abbr;

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

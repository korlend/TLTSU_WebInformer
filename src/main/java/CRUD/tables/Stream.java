package CRUD.tables;

/**
 * Created by Артем on 14.05.2016.
 */
public class Stream implements Table {
    private int OID;
    private String Name;

    public void setName(String name) {
        Name = name;
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

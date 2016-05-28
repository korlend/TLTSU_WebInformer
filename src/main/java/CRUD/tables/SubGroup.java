package CRUD.tables;

/**
 * Created by Артем on 14.05.2016.
 */
public class SubGroup implements Table {
    private int OID;
    private int Group;

    public void setGroup(int group) {
        Group = group;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public int getGroup() {
        return Group;
    }

    public int getOID() {
        return OID;
    }
}

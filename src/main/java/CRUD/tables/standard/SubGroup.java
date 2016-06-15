package CRUD.tables.standard;

import CRUD.tables.Table;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubGroup)) return false;

        SubGroup subGroup = (SubGroup) o;

        if (getOID() != subGroup.getOID()) return false;
        return getGroup() == subGroup.getGroup();

    }

    @Override
    public int hashCode() {
        int result = getOID();
        result = 31 * result + getGroup();
        return result;
    }

    public int getOID() {
        return OID;
    }
}

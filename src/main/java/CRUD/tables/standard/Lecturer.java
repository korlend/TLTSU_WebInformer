package CRUD.tables.standard;

import CRUD.tables.Table;

/**
 * Created by Артем on 14.05.2016.
 */
public class Lecturer implements Table {
    private int OID;
    private String FIO;
    private int Chair;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lecturer)) return false;

        Lecturer lecturer = (Lecturer) o;

        if (getOID() != lecturer.getOID()) return false;
        if (getChair() != lecturer.getChair()) return false;
        return !(getFIO() != null ? !getFIO().equals(lecturer.getFIO()) : lecturer.getFIO() != null);

    }

    @Override
    public int hashCode() {
        int result = getOID();
        result = 31 * result + (getFIO() != null ? getFIO().hashCode() : 0);
        result = 31 * result + getChair();
        return result;
    }

    public void setChair(int chair) {
        Chair = chair;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public int getChair() {
        return Chair;
    }

    public int getOID() {
        return OID;
    }

    public String getFIO() {
        return FIO;
    }
}

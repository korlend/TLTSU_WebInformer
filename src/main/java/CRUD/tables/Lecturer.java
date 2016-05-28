package CRUD.tables;

/**
 * Created by Артем on 14.05.2016.
 */
public class Lecturer implements Table {
    private int OID;
    private String FIO;
    private int Chair;

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

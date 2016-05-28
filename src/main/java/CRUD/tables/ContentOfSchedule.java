package CRUD.tables;

import java.sql.Timestamp;

/**
 * Created by Артем on 14.05.2016.
 */
public class ContentOfSchedule implements Table {
    private int OID;
    private Timestamp StartOn;
    private Timestamp EndOn;
    private Timestamp ModifiedTime;
    private int Discipline;
    private int KindOfWork;
    private int Lecturer;
    private int Auditorium;
    private int Stream;
    private int Group;
    private int SubGroup;

    public void setAuditorium(int auditorium) {
        Auditorium = auditorium;
    }

    public void setDiscipline(int discipline) {
        Discipline = discipline;
    }

    public void setEndOn(Timestamp endOn) {
        EndOn = endOn;
    }

    public void setGroup(int group) {
        Group = group;
    }

    public void setKindOfWork(int kindOfWork) {
        KindOfWork = kindOfWork;
    }

    public void setLecturer(int lecturer) {
        Lecturer = lecturer;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        ModifiedTime = modifiedTime;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public void setStartOn(Timestamp startOn) {
        StartOn = startOn;
    }

    public void setStream(int stream) {
        Stream = stream;
    }

    public void setSubGroup(int subGroup) {
        SubGroup = subGroup;
    }

    public int getAuditorium() {
        return Auditorium;
    }

    public int getDiscipline() {
        return Discipline;
    }

    public int getGroup() {
        return Group;
    }

    public int getKindOfWork() {
        return KindOfWork;
    }

    public int getLecturer() {
        return Lecturer;
    }

    public int getOID() {
        return OID;
    }

    public int getStream() {
        return Stream;
    }

    public int getSubGroup() {
        return SubGroup;
    }

    public Timestamp getEndOn() {
        return EndOn;
    }

    public Timestamp getModifiedTime() {
        return ModifiedTime;
    }

    public Timestamp getStartOn() {
        return StartOn;
    }
}

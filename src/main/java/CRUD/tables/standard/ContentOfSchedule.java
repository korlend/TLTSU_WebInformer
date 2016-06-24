package CRUD.tables.standard;

import CRUD.tables.Table;

import java.sql.Timestamp;
import java.util.Comparator;

/**
 * Created by Артем on 14.05.2016.
 */
public class ContentOfSchedule implements Comparator, Comparable, Table {
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

    public ContentOfSchedule() {
        this.Discipline = 0;
        this.KindOfWork = 0;
        this.Lecturer = 0;
        this.Auditorium = 0;
        this.Stream = 0;
        this.Group = 0;
        this.SubGroup = 0;
    }

    @Override
    public int compare(Object o1, Object o2) {
        return ((ContentOfSchedule) o1).getOID() < ((ContentOfSchedule) o2).getOID() ? -1 :
                ((ContentOfSchedule) o1).getOID() > ((ContentOfSchedule) o2).getOID() ? 1 : 0;
    }

    @Override
    public int compareTo(Object o) {
        return this.compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContentOfSchedule)) return false;

        ContentOfSchedule that = (ContentOfSchedule) o;

        if (getOID() != that.getOID()) return false;
        if (getDiscipline() != that.getDiscipline()) return false;
        if (getKindOfWork() != that.getKindOfWork()) return false;
        if (getLecturer() != that.getLecturer()) return false;
        if (getAuditorium() != that.getAuditorium()) return false;
        if (getStream() != that.getStream()) return false;
        if (getGroup() != that.getGroup()) return false;
        if (getSubGroup() != that.getSubGroup()) return false;
        if (getStartOn() != null ? !getStartOn().equals(that.getStartOn()) : that.getStartOn() != null) return false;
        if (getEndOn() != null ? !getEndOn().equals(that.getEndOn()) : that.getEndOn() != null) return false;
        return !(getModifiedTime() != null ? !getModifiedTime().equals(that.getModifiedTime()) : that.getModifiedTime() != null);

    }

    @Override
    public int hashCode() {
        int result = getOID();
        result = 31 * result + (getStartOn() != null ? getStartOn().hashCode() : 0);
        result = 31 * result + (getEndOn() != null ? getEndOn().hashCode() : 0);
        result = 31 * result + (getModifiedTime() != null ? getModifiedTime().hashCode() : 0);
        result = 31 * result + getDiscipline();
        result = 31 * result + getKindOfWork();
        result = 31 * result + getLecturer();
        result = 31 * result + getAuditorium();
        result = 31 * result + getStream();
        result = 31 * result + getGroup();
        result = 31 * result + getSubGroup();
        return result;
    }

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

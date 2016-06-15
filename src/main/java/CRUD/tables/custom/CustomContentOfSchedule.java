package CRUD.tables.custom;

import java.sql.Timestamp;

/**
 * Created by Артем on 30.05.2016.
 */

/**
 *
 */

public class CustomContentOfSchedule {
    private int OID;
    private String StartOn;
    private String EndOn;
    private String ModifiedTime;
    private String DisciplineName;
    private String KindOfWorkName;
    private String LecturerFIO;
    private String AuditoriumAbbr;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomContentOfSchedule)) return false;

        CustomContentOfSchedule that = (CustomContentOfSchedule) o;

        if (getOID() != that.getOID()) return false;
        if (getStartOn() != null ? !getStartOn().equals(that.getStartOn()) : that.getStartOn() != null) return false;
        if (getEndOn() != null ? !getEndOn().equals(that.getEndOn()) : that.getEndOn() != null) return false;
        if (getModifiedTime() != null ? !getModifiedTime().equals(that.getModifiedTime()) : that.getModifiedTime() != null)
            return false;
        if (getDisciplineName() != null ? !getDisciplineName().equals(that.getDisciplineName()) : that.getDisciplineName() != null)
            return false;
        if (getKindOfWorkName() != null ? !getKindOfWorkName().equals(that.getKindOfWorkName()) : that.getKindOfWorkName() != null)
            return false;
        if (getLecturerFIO() != null ? !getLecturerFIO().equals(that.getLecturerFIO()) : that.getLecturerFIO() != null)
            return false;
        return !(getAuditoriumAbbr() != null ? !getAuditoriumAbbr().equals(that.getAuditoriumAbbr()) : that.getAuditoriumAbbr() != null);

    }

    @Override
    public int hashCode() {
        int result = getOID();
        result = 31 * result + (getStartOn() != null ? getStartOn().hashCode() : 0);
        result = 31 * result + (getEndOn() != null ? getEndOn().hashCode() : 0);
        result = 31 * result + (getModifiedTime() != null ? getModifiedTime().hashCode() : 0);
        result = 31 * result + (getDisciplineName() != null ? getDisciplineName().hashCode() : 0);
        result = 31 * result + (getKindOfWorkName() != null ? getKindOfWorkName().hashCode() : 0);
        result = 31 * result + (getLecturerFIO() != null ? getLecturerFIO().hashCode() : 0);
        result = 31 * result + (getAuditoriumAbbr() != null ? getAuditoriumAbbr().hashCode() : 0);
        return result;
    }

    public void setAuditoriumAbbr(String auditoriumAbbr) {
        AuditoriumAbbr = auditoriumAbbr;
    }

    public void setDisciplineName(String disciplineName) {
        DisciplineName = disciplineName;
    }

    public void setEndOn(String endOn) {
        EndOn = endOn;
    }

    public void setKindOfWorkName(String kindOfWorkName) {
        KindOfWorkName = kindOfWorkName;
    }

    public void setLecturerFIO(String lecturerFIO) {
        LecturerFIO = lecturerFIO;
    }

    public void setModifiedTime(String modifiedTime) {
        ModifiedTime = modifiedTime;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public void setStartOn(String startOn) {
        StartOn = startOn;
    }

    public String getAuditoriumAbbr() {
        return AuditoriumAbbr;
    }

    public String getDisciplineName() {
        return DisciplineName;
    }

    public String getKindOfWorkName() {
        return KindOfWorkName;
    }

    public String getLecturerFIO() {
        return LecturerFIO;
    }

    public int getOID() {
        return OID;
    }

    public String getEndOn() {
        return EndOn;
    }

    public String getModifiedTime() {
        return ModifiedTime;
    }

    public String getStartOn() {
        return StartOn;
    }
}

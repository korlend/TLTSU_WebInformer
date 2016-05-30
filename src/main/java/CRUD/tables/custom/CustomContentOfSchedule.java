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
    private Timestamp StartOn;
    private Timestamp EndOn;
    private Timestamp ModifiedTime;
    private String DisciplineName;
    private String KindOfWorkName;
    private String LecturerFIO;
    private String AuditoriumAbbr;

    public void setAuditoriumAbbr(String auditoriumAbbr) {
        AuditoriumAbbr = auditoriumAbbr;
    }

    public void setDisciplineName(String disciplineName) {
        DisciplineName = disciplineName;
    }

    public void setEndOn(Timestamp endOn) {
        EndOn = endOn;
    }

    public void setKindOfWorkName(String kindOfWorkName) {
        KindOfWorkName = kindOfWorkName;
    }

    public void setLecturerFIO(String lecturerFIO) {
        LecturerFIO = lecturerFIO;
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

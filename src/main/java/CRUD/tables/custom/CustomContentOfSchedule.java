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

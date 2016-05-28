package CRUD.oldtables;

import java.sql.Timestamp;

/**
 * Created by Артем on 02.05.2016.
 */
/*
java.util.Date date = new Date();
Object param = new java.sql.Timestamp(date.getTime());
// The JDBC driver knows what to do with a java.sql type:
preparedStatement.setObject(param);
 */
public class Schedule {
    private Integer idSchedule;
    private Timestamp datetimeBegin;
    private Timestamp datetimeEnd;
    private Integer professorId;
    private Integer lessonTypeId;
    private Integer auditoryId;
    private Integer subjectId;
    private Integer groupTypeId;

    public Integer getAuditoryId() {
        return auditoryId;
    }

    public Integer getGroupTypeId() {
        return groupTypeId;
    }

    public Integer getIdSchedule() {
        return idSchedule;
    }

    public Integer getLessonTypeId() {
        return lessonTypeId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public Timestamp getDatetimeBegin() {
        return datetimeBegin;
    }

    public Timestamp getDatetimeEnd() {
        return datetimeEnd;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public void setAuditoryId(Integer auditoryId) {
        this.auditoryId = auditoryId;
    }

    public void setDatetimeBegin(Timestamp datetimeBegin) {
        this.datetimeBegin = datetimeBegin;
    }

    public void setDatetimeEnd(Timestamp datetimeEnd) {
        this.datetimeEnd = datetimeEnd;
    }

    public void setGroupTypeId(Integer groupTypeId) {
        this.groupTypeId = groupTypeId;
    }

    public void setIdSchedule(Integer idSchedule) {
        this.idSchedule = idSchedule;
    }

    public void setLessonTypeId(Integer lessonTypeId) {
        this.lessonTypeId = lessonTypeId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }
}

package CRUD.oldmappers;

import CRUD.oldtables.Schedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 05.05.2016.
 */
public class ScheduleMapper implements RowMapper<Schedule> {
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Schedule auditories = new Schedule();
        auditories.setIdSchedule(rs.getInt("OID"));
        auditories.setDatetimeBegin(rs.getTimestamp("StartOn"));
        auditories.setDatetimeEnd(rs.getTimestamp("EndOn"));
        auditories.setAuditoryId(rs.getInt("Auditorium"));
        auditories.setGroupTypeId(rs.getInt("CGroup"));//надо через запрос как-то редактировать учитывая Stream
        auditories.setLessonTypeId(rs.getInt("KindOfWork"));
        auditories.setSubjectId(rs.getInt("Discipline"));
        auditories.setProfessorId(rs.getInt("Lecturer"));
        return auditories;
    }
}

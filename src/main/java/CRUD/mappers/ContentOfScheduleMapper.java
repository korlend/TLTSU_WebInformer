package CRUD.mappers;

import CRUD.tables.ContentOfSchedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 14.05.2016.
 */
public class ContentOfScheduleMapper implements RowMapper<ContentOfSchedule> {
    public ContentOfSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        ContentOfSchedule contentOfSchedule = new ContentOfSchedule();
        contentOfSchedule.setOID(rs.getInt("OID"));
        contentOfSchedule.setDiscipline(rs.getInt("Discipline"));
        contentOfSchedule.setGroup(rs.getInt("Group"));
        contentOfSchedule.setKindOfWork(rs.getInt("KindOfWork"));
        contentOfSchedule.setLecturer(rs.getInt("Lecturer"));
        contentOfSchedule.setStream(rs.getInt("Stream"));
        contentOfSchedule.setSubGroup(rs.getInt("SubGroup"));
        contentOfSchedule.setAuditorium(rs.getInt("Auditorium"));
        contentOfSchedule.setEndOn(rs.getTimestamp("EndOn"));
        contentOfSchedule.setStartOn(rs.getTimestamp("StartOn"));
        contentOfSchedule.setModifiedTime(rs.getTimestamp("ModifiedTime"));

        return contentOfSchedule;
    }
}

package com.korlend.CRUD.mappers.custom;

import com.korlend.CRUD.tables.custom.CustomContentOfSchedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 30.05.2016.
 */
public class CustomContentOfScheduleMapper implements RowMapper<CustomContentOfSchedule> {
    public CustomContentOfSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomContentOfSchedule contentOfSchedule = new CustomContentOfSchedule();
        contentOfSchedule.setOID(rs.getInt("OID"));
        contentOfSchedule.setDisciplineName(rs.getString("Discipline"));
        contentOfSchedule.setKindOfWorkName(rs.getString("KindOfWork"));
        contentOfSchedule.setLecturerFIO(rs.getString("Lecturer"));
        contentOfSchedule.setAuditoriumAbbr(rs.getString("Auditorium"));
        contentOfSchedule.setNumberOfPair(rs.getString("ContentTableOfLesson"));
        contentOfSchedule.setGroupAbbr(rs.getString("Group"));
        contentOfSchedule.setEndOn(rs.getTimestamp("EndOn").toString());
        contentOfSchedule.setStartOn(rs.getTimestamp("StartOn").toString());
        contentOfSchedule.setModifiedTime(rs.getTimestamp("ModifiedTime").toString());

        return contentOfSchedule;
    }
}
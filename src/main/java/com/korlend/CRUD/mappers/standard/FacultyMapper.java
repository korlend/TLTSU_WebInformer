package com.korlend.CRUD.mappers.standard;

import com.korlend.CRUD.tables.standard.Faculty;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 03.09.2016.
 */
public class FacultyMapper implements RowMapper<Faculty> {
    public Faculty mapRow(ResultSet rs, int rowNum) throws SQLException {
        Faculty faculty = new Faculty();
        faculty.setOID(rs.getInt("OID"));
        faculty.setName(rs.getString("Name"));
        faculty.setAbbr(rs.getString("Abbr"));
        return faculty;
    }
}

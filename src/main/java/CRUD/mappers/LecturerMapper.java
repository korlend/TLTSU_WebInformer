package CRUD.mappers;

import CRUD.tables.Lecturer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 14.05.2016.
 */
public class LecturerMapper implements RowMapper<Lecturer> {
    public Lecturer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Lecturer lecturer = new Lecturer();
        lecturer.setOID(rs.getInt("OID"));
        lecturer.setFIO(rs.getString("FIO"));
        lecturer.setChair(rs.getInt("Chair"));
        return lecturer;
    }
}

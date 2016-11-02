package CRUD.mappers.standard;

import CRUD.tables.standard.Chair;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 14.05.2016.
 */
public class ChairMapper implements RowMapper<Chair> {
    public Chair mapRow(ResultSet rs, int rowNum) throws SQLException {
        Chair chair = new Chair();
        chair.setOID(rs.getInt("OID"));
        chair.setName(rs.getString("Name"));
        chair.setFaculty(rs.getInt("Faculty"));
        //chair.setAuditorium(rs.getInt("Auditorium")); not exist in Oracle
        return chair;
    }
}

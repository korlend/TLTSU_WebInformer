package CRUD.mappers.standard;

import CRUD.tables.standard.Auditorium;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuditoriumMapper implements RowMapper<Auditorium> {
    public Auditorium mapRow(ResultSet rs, int rowNum) throws SQLException {
        Auditorium auditorium = new Auditorium();
        auditorium.setOID(rs.getInt("OID"));
        auditorium.setAbbr(rs.getString("Abbr"));
        auditorium.setName(rs.getString("Name"));
        auditorium.setBuilding(rs.getInt("Building"));
        auditorium.setTypeOfAuditorium(rs.getInt("TypeOfAuditorium"));
        return auditorium;
    }
}
package CRUD.oldmappers;

import CRUD.oldtables.Auditories;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 04.05.2016.
 */
public class AuditoriesMapper implements RowMapper<Auditories> {
    public Auditories mapRow(ResultSet rs, int rowNum) throws SQLException {
        Auditories auditories = new Auditories();
        auditories.setIdAuditory(rs.getInt("OID"));
        auditories.setName(rs.getString("Abbr"));
        auditories.setFullName(rs.getString("Name"));
        auditories.setAuditoryTypeId(rs.getInt("TypeOfAuditorium"));
        return auditories;
    }
}

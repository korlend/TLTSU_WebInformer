package CRUD.oldmappers;

import CRUD.oldtables.AuditoryTypes;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 04.05.2016.
 */
public class AuditoryTypesMapper implements RowMapper<AuditoryTypes> {
    public AuditoryTypes mapRow(ResultSet rs, int rowNum) throws SQLException {
        AuditoryTypes auditoriesTypes = new AuditoryTypes();
        auditoriesTypes.setIdAuditoryType(rs.getInt("OID"));
        auditoriesTypes.setName(rs.getString("Name"));
        return auditoriesTypes;
    }
}

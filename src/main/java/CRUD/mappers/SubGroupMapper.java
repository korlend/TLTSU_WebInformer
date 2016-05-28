package CRUD.mappers;

import CRUD.tables.SubGroup;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 14.05.2016.
 */
public class SubGroupMapper implements RowMapper<SubGroup> {
    public SubGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
        SubGroup subGroup = new SubGroup();
        subGroup.setOID(rs.getInt("OID"));
        subGroup.setGroup(rs.getInt("Group"));
        return subGroup;
    }
}

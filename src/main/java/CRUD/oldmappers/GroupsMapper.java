package CRUD.oldmappers;

import CRUD.oldtables.Groups;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 04.05.2016.
 */
public class GroupsMapper implements RowMapper<Groups> {
    public Groups mapRow(ResultSet rs, int rowNum) throws SQLException {
        Groups groups = new Groups();
        groups.setIdGroup(rs.getInt("OID"));
        groups.setName(rs.getString("Name"));
        return groups;
    }
}

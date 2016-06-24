package CRUD.mappers.custom;

import CRUD.tables.custom.GroupMaxModTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 10.06.2016.
 */
public class GroupNamesMapper implements RowMapper<GroupMaxModTime> {
    public GroupMaxModTime mapRow(ResultSet rs, int rowNum) throws SQLException {
        GroupMaxModTime groupMaxModTime = new GroupMaxModTime();
        groupMaxModTime.setGroupName(rs.getString("Name"));
        groupMaxModTime.setMaxModTime(rs.getTimestamp("MaxModifiedTime").toString());
        return groupMaxModTime;
    }
}
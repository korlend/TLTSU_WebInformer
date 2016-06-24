package CRUD.mappers.custom;

import CRUD.tables.custom.GroupMaxModTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Артем on 22.06.2016.
 */
public class MMTStreamMapper implements RowMapper<List<GroupMaxModTime>> {
    @Override
    public List<GroupMaxModTime> mapRow(ResultSet resultSet, int i) throws SQLException {
        List<GroupMaxModTime> list = new ArrayList<>();
        for(String entry : resultSet.getString("Name").split(" "))
        {
            if (entry.indexOf(0) == '(') continue;
            list.add(new GroupMaxModTime()
                    .setGroupName(entry)
                    .setMaxModTime(resultSet.getString("MaxModifiedTime")));
        }
        return list;
    }
}
package CRUD.mappers.custom;

import CRUD.tables.custom.ConnectedUsers;
import org.springframework.jdbc.core.RowMapper;
import spring.json.SimpleJsonParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Артем on 13.06.2016.
 */
public class ConnectedUsersMapper implements RowMapper<ConnectedUsers> {
@Override
public ConnectedUsers mapRow(ResultSet resultSet, int i) throws SQLException {
        ConnectedUsers connectedUsers = new ConnectedUsers();
        connectedUsers.setConnected_time(resultSet.getTimestamp("connected_time") == null ?
        "" :
        resultSet.getTimestamp("connected_time").toString());
        connectedUsers.setDevice_id(resultSet.getString("device_id"));
        connectedUsers.setId(resultSet.getInt("id"));
        List<Object> preferred_groups = new SimpleJsonParser().parseList(resultSet.getString("preferred_groups"));
        List<String> pref_gr = new ArrayList<>();
        preferred_groups.forEach(el -> pref_gr.add(el.toString()));
        connectedUsers.setPreferred_groups(pref_gr);


        return connectedUsers;
        }
        }











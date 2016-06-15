package CRUD.DAO;

import CRUD.mappers.custom.ConnectedUsersMapper;
import CRUD.tables.custom.ConnectedUsers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Артем on 13.06.2016.
 */
public class ConnectedUsersDAO {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private SimpleJdbcInsert jdbcInsertMySQL;

    public ConnectedUsersDAO(JdbcTemplate jdbcTemplateMySQL) {
        setDataSourceMySQL(jdbcTemplateMySQL);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("connected_users");
    }

    public List<ConnectedUsers> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("SELECT * FROM connected_users", new ConnectedUsersMapper());
    }

    public List<ConnectedUsers> findAllMySQL(String device_id) {
        return this.jdbcTemplateObjectMySQL.query("SELECT * FROM connected_users where device_id = '" + device_id + "'", new ConnectedUsersMapper());
    }

    private int getId(String device_id) {
        List<ConnectedUsers> list = this.findAllMySQL(device_id);
        if (list.size() == 0) return 0;
        return this.findAllMySQL(device_id).get(0).getId();
    }

    public void addGroupToUser(String device_id, String group) {
        int id = this.getId(device_id);
        if (id == 0) {
            System.out.println("no such user");
            return;
        }
        List<Object> groups = this.getGroupsFromUser(device_id);
        groups.add(group);
        this.jdbcTemplateObjectMySQL.execute("update connected_users set preferred_groups = '" +
                groups.toString() + "'" +
                "where `id` = " + id);
    }

    private List<Object> getGroupsFromUser(String device_id) {
        return this.findAllMySQL(device_id).get(0).getPreferred_groups();
    }

    public void deleteAllMySQL() {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM connected_users WHERE ID > 0");
    }

    public void addListMySQL(List<ConnectedUsers> list) {
        for (ConnectedUsers entry : list) {
            this.addRowMySQL(entry);
        }
    }

    public void addRowMySQL(ConnectedUsers record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("device_id", record.getDevice_id())
                .addValue("preferred_groups", record.getPreferred_groups().toString());
        this.jdbcInsertMySQL.execute(parameters);
    }
}

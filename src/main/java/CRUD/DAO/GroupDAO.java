package CRUD.DAO;

import CRUD.mappers.custom.GroupNamesMapper;
import CRUD.mappers.standard.GroupMapper;
import CRUD.tables.custom.GroupMaxModTime;
import CRUD.tables.standard.Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Артем on 22.05.2016.
 */
public class GroupDAO extends JdbcTemplate {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public GroupDAO(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
        setDataSourceMySQL(jdbcTemplateMySQL);
        setDataSourceOracle(jdbcTemplateOracle);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("`group`");
    }

    public void setDataSourceOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectOracle = jdbcTemplate;
        this.jdbcInsertOracle = new SimpleJdbcInsert(jdbcTemplate).withTableName("Group");
    }

    public List<Group> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select * from `Group`", new GroupMapper());
    }

    public List<Group> findAllOracle() {
        return this.jdbcTemplateObjectOracle.query("select * from \"Group\"", new GroupMapper());
    }

    public List<String> findAllGroupsMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select `name` from Group", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("Name");
            }
        });
    }

    public List<String> findAllGroupsOracle() {
        return this.jdbcTemplateObjectOracle.query("select \"name\" from \"Group\"", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("Name");
            }
        });
    }

    public List<GroupMaxModTime> findAllGroupsMaxModTimeMySQL() {
        return this.jdbcTemplateObjectMySQL.query("SELECT " +
                "max(c.ModifiedTime) as `MaxModifiedTime`, " +
                "g.Name " +
                "from contentofschedule c " +
                "INNER JOIN `group` g ON g.OID = c.GroupOID " +
                "group by g.Name " +
                "order by g.Name",
                new GroupNamesMapper());
    }

    public List<GroupMaxModTime> findAllGroupsMaxModTimeOracle() {
        return this.jdbcTemplateObjectOracle.query("SELECT " +
                "MAX(c.\"ModifiedTime\") as \"MaxModifiedTime\", " +
                "g.\"Name\" " +
                "from \"ContentOfSchedule\" c " +
                "INNER JOIN \"Group\" g ON g.\"OID\" = c.\"Group\" " +
                "group by g.\"Name\" " +
                "order by g.\"Name\"",
                new GroupNamesMapper());
    }

    public void deleteAllMySQL() {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM `group` WHERE OID > 0");
    }

    public void addListMySQL(List<Group> list) {
        for (Group entry : list) {
            this.addRowMySQL(entry);
        }
    }

    public void addListOracle(List<Group> list) {
        for (Group entry : list) {
            this.addRowOracle(entry);
        }
    }

    public void addRowMySQL(Group record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Course", record.getCourse())
                .addValue("Name", record.getName())
                .addValue("OID", record.getOID());
        this.jdbcInsertMySQL.execute(parameters);
    }

    public void addRowOracle(Group record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Course", record.getCourse())
                .addValue("Name", record.getName())
                .addValue("OID", record.getOID());
        this.jdbcInsertOracle.execute(parameters);
    }
}

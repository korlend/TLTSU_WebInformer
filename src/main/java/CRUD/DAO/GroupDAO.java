package CRUD.DAO;

import CRUD.mappers.standard.GroupMapper;
import CRUD.tables.standard.Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

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
        return this.jdbcTemplateObjectMySQL.query("select * from Group", new GroupMapper());
    }

    public List<Group> findAllOracle() {
        return this.jdbcTemplateObjectOracle.query("select * from \"Group\"", new GroupMapper());
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

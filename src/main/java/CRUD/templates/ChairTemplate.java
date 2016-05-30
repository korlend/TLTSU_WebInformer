package CRUD.templates;

import CRUD.mappers.ChairMapper;
import CRUD.tables.Chair;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class ChairTemplate extends JdbcTemplate {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public ChairTemplate(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
        setDataSourceMySQL(jdbcTemplateMySQL);
        setDataSourceOracle(jdbcTemplateOracle);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("`chair`");
    }

    public void setDataSourceOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectOracle = jdbcTemplate;
        this.jdbcInsertOracle = new SimpleJdbcInsert(jdbcTemplate).withTableName("Chair");
    }

    public List<Chair> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select * from chair", new ChairMapper());
    }

    public List<Chair> findAllOracle() {
        return this.jdbcTemplateObjectOracle.query("select * from \"Chair\"", new ChairMapper());
    }

    public void addListMySQL(List<Chair> list) {
        for (Chair entry : list) {
            this.addRowMySQL(entry);
        }
    }

    public void addListOracle(List<Chair> list) {
        for (Chair entry : list) {
            this.addRowOracle(entry);
        }
    }

    public void addRowMySQL(Chair record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                //.addValue("Auditorium", record.getAuditorium())
                .addValue("Name", record.getName())
                .addValue("OID", record.getOID());
        this.jdbcInsertMySQL.execute(parameters);
    }

    public void addRowOracle(Chair record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                //.addValue("Auditorium", record.getAuditorium())
                .addValue("Name", record.getName())
                .addValue("OID", record.getOID());
        this.jdbcInsertOracle.execute(parameters);
    }
}

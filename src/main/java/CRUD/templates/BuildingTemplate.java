package CRUD.templates;

import CRUD.mappers.BuildingMapper;
import CRUD.tables.Building;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class BuildingTemplate {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public BuildingTemplate(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
        setDataSourceMySQL(jdbcTemplateMySQL);
        setDataSourceOracle(jdbcTemplateOracle);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("Building").withSchemaName("tltsudb");
    }

    public void setDataSourceOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectOracle = jdbcTemplate;
        this.jdbcInsertOracle = new SimpleJdbcInsert(jdbcTemplate).withTableName("Building");
    }

    public List<Building> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("SELECT * FROM building", new BuildingMapper());
    }

    public List<Building> findAllOracle() {
        return this.jdbcTemplateObjectOracle.query("select * from \"Building\"", new BuildingMapper());
    }

    public void addListMySQL(List<Building> list) {
        for (Building entry : list) {
            this.addRowMySQL(entry);
        }
    }

    public void addListOracle(List<Building> list) {
        for (Building entry : list) {
            this.addRowOracle(entry);
        }
    }

    public void addRowMySQL(Building record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Name", record.getName())
                //.addValue("Address", record.getAddress())
                .addValue("OID", record.getOID());
        this.jdbcInsertMySQL.execute(parameters);
    }

    public void addRowOracle(Building record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Name", record.getName())
                //.addValue("Address", record.getAddress())
                .addValue("OID", record.getOID());
        this.jdbcInsertOracle.execute(parameters);
    }
}

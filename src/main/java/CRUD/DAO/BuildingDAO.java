package CRUD.DAO;

import CRUD.mappers.standard.BuildingMapper;
import CRUD.tables.standard.Building;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class BuildingDAO {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public BuildingDAO(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
        setDataSourceMySQL(jdbcTemplateMySQL);
        setDataSourceOracle(jdbcTemplateOracle);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("`building`");
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

    public void deleteAllMySQL() {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM building WHERE OID > 0");
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

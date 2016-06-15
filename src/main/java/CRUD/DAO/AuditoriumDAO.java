package CRUD.DAO;

import CRUD.mappers.standard.AuditoriumMapper;
import CRUD.tables.standard.Auditorium;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class AuditoriumDAO {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public AuditoriumDAO(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
        setDataSourceMySQL(jdbcTemplateMySQL);
        setDataSourceOracle(jdbcTemplateOracle);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("`auditorium`");
    }

    public void setDataSourceOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectOracle = jdbcTemplate;
        this.jdbcInsertOracle = new SimpleJdbcInsert(jdbcTemplate).withTableName("Auditorium");
    }

    public List<Auditorium> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select * from Auditorium", new AuditoriumMapper());
    }

    public List<Auditorium> findAllOracle() {
        return this.jdbcTemplateObjectOracle.query("select * from \"Auditorium\"", new AuditoriumMapper());
    }

    public void deleteAllMySQL() {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM auditorium WHERE OID > 0");
    }

    public void addListMySQL(List<Auditorium> list) {
        for (Auditorium entry : list) {
            this.addRowMySQL(entry);
        }
    }

    public void addListOracle(List<Auditorium> list) {
        for (Auditorium entry : list) {
            this.addRowOracle(entry);
        }
    }

    public void addRowMySQL(Auditorium record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Abbr", record.getAbbr())
                .addValue("Name", record.getName())
                .addValue("OID", record.getOID())
                .addValue("Building", record.getBuilding());
        this.jdbcInsertMySQL.execute(parameters);
    }

    public void addRowOracle(Auditorium record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Abbr", record.getAbbr())
                .addValue("Name", record.getName())
                .addValue("OID", record.getOID())
                .addValue("Building", record.getBuilding());
        this.jdbcInsertOracle.execute(parameters);
    }
}

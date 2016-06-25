package CRUD.DAO;

import CRUD.mappers.standard.DisciplineMapper;
import CRUD.tables.standard.Discipline;
import CRUD.tables.standard.Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class DisciplineDAO extends JdbcTemplate {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public DisciplineDAO(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
        setDataSourceMySQL(jdbcTemplateMySQL);
        setDataSourceOracle(jdbcTemplateOracle);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("`discipline`");
    }

    public void setDataSourceOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectOracle = jdbcTemplate;
        this.jdbcInsertOracle = new SimpleJdbcInsert(jdbcTemplate).withTableName("Discipline");
    }

    public List<Discipline> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select * from Discipline", new DisciplineMapper());
    }

    public List<Discipline> findAllOracle() {
        return this.jdbcTemplateObjectOracle.query("select * from \"Discipline\"", new DisciplineMapper());
    }

    public void deleteAllMySQL() {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM discipline WHERE OID > 0");
    }

    public void deleteRowsMySQL(List<Discipline> list) {
        list.forEach(r -> deleteRowMySQL(r));
    }

    public void deleteRowMySQL(Discipline record) {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM discipline WHERE OID = " + record.getOID());
    }

    public void updateRowsMySQL(List<Discipline> records) {
        records.forEach(rec -> this.updateRowMySQL(rec));
    }

    public void updateRowMySQL(Discipline record) {
        jdbcTemplateObjectMySQL.update("UPDATE `tltsudb`.`discipline`\n" +
                        "SET\n" +
                        "`OID` = ?,\n" +
                        "`Name` = ?\n" +
                        "WHERE `OID` = ?\n",
                record.getOID(),
                record.getName(),
                record.getOID());
    }

    public void addListMySQL(List<Discipline> list) {
        for (Discipline entry : list) {
            this.addRowMySQL(entry);
        }
    }

    public void addListOracle(List<Discipline> list) {
        for (Discipline entry : list) {
            this.addRowOracle(entry);
        }
    }

    public void addRowMySQL(Discipline record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Name", record.getName())
                .addValue("OID", record.getOID());
        this.jdbcInsertMySQL.execute(parameters);
    }

    public void addRowOracle(Discipline record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Name", record.getName())
                .addValue("OID", record.getOID());
        this.jdbcInsertOracle.execute(parameters);
    }
}

package CRUD.DAO;

import CRUD.mappers.standard.LecturerMapper;
import CRUD.tables.standard.Lecturer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class LecturerDAO extends JdbcTemplate {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public LecturerDAO(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
        setDataSourceMySQL(jdbcTemplateMySQL);
        setDataSourceOracle(jdbcTemplateOracle);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("`lecturer`");
    }

    public void setDataSourceOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectOracle = jdbcTemplate;
        this.jdbcInsertOracle = new SimpleJdbcInsert(jdbcTemplate).withTableName("Lecturer");
    }

    public List<Lecturer> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select * from Lecturer", new LecturerMapper());
    }

    public List<Lecturer> findAllOracle() {
        return this.jdbcTemplateObjectOracle.query("select * from \"Lecturer\"", new LecturerMapper());
    }

    public void deleteAllMySQL() {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM Lecturer WHERE OID > 0");
    }

    public void addListMySQL(List<Lecturer> list) {
        for (Lecturer entry : list) {
            this.addRowMySQL(entry);
        }
    }

    public void addListOracle(List<Lecturer> list) {
        for (Lecturer entry : list) {
            this.addRowOracle(entry);
        }
    }

    public void addRowMySQL(Lecturer record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Chair", record.getChair())
                .addValue("FIO", record.getFIO())
                .addValue("OID", record.getOID());
        this.jdbcInsertMySQL.execute(parameters);
    }

    public void addRowOracle(Lecturer record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Chair", record.getChair())
                .addValue("FIO", record.getFIO())
                .addValue("OID", record.getOID());
        this.jdbcInsertOracle.execute(parameters);
    }
}

package CRUD.templates;

import CRUD.mappers.TypeOfAuditoriumMapper;
import CRUD.tables.TypeOfAuditorium;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class TypeOfAuditoriumTemplate extends JdbcTemplate {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public TypeOfAuditoriumTemplate(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
        setDataSourceMySQL(jdbcTemplateMySQL);
        setDataSourceOracle(jdbcTemplateOracle);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("`typeofauditorium`");
    }

    public void setDataSourceOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectOracle = jdbcTemplate;
        this.jdbcInsertOracle = new SimpleJdbcInsert(jdbcTemplate).withTableName("TypeOfAuditorium");
    }

    public List<TypeOfAuditorium> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select * from TypeOfAuditorium", new TypeOfAuditoriumMapper());
    }

    public List<TypeOfAuditorium> findAllOracle() {
        return this.jdbcTemplateObjectOracle.query("select * from \"TypeOfAuditorium\"", new TypeOfAuditoriumMapper());
    }

    public void addListMySQL(List<TypeOfAuditorium> list) {
        for (TypeOfAuditorium entry : list) {
            this.addRowMySQL(entry);
        }
    }

    public void addListOracle(List<TypeOfAuditorium> list) {
        for (TypeOfAuditorium entry : list) {
            this.addRowOracle(entry);
        }
    }

    public void addRowMySQL(TypeOfAuditorium record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Name", record.getName())
                .addValue("OID", record.getOID());
        this.jdbcInsertMySQL.execute(parameters);
    }

    public void addRowOracle(TypeOfAuditorium record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Name", record.getName())
                .addValue("OID", record.getOID());
        this.jdbcInsertOracle.execute(parameters);
    }
}

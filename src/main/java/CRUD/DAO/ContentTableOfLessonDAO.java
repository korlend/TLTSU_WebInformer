package CRUD.DAO;

import CRUD.mappers.standard.AuditoriumMapper;
import CRUD.mappers.standard.ContentTableOfLessonMapper;
import CRUD.tables.standard.Auditorium;
import CRUD.tables.standard.ContentTableOfLesson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

/**
 * Created by Артем on 07.07.2016.
 */
public class ContentTableOfLessonDAO {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public ContentTableOfLessonDAO(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
        setDataSourceMySQL(jdbcTemplateMySQL);
        setDataSourceOracle(jdbcTemplateOracle);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("`contenttableoflesson`");
    }

    public void setDataSourceOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectOracle = jdbcTemplate;
        this.jdbcInsertOracle = new SimpleJdbcInsert(jdbcTemplate).withTableName("ContentTableOfLesson");
    }

    public List<ContentTableOfLesson> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select * from ContentTableOfLesson", new ContentTableOfLessonMapper());
    }

    public List<ContentTableOfLesson> findAllOracle() {
        return this.jdbcTemplateObjectOracle.query("select * from \"ContentTableOfLesson\"", new ContentTableOfLessonMapper());
    }

    public void deleteAllMySQL() {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM ContentTableOfLesson WHERE OID > 0");
    }

    public void deleteRowsMySQL(List<ContentTableOfLesson> list) {
        list.forEach(r -> deleteRowMySQL(r));
    }

    public void deleteRowMySQL(ContentTableOfLesson record) {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM ContentTableOfLesson WHERE OID = " + record.getOID());
    }

    public void updateRowsMySQL(List<ContentTableOfLesson> records) {
        records.forEach(rec -> this.updateRowMySQL(rec));
    }

    public void updateRowMySQL(ContentTableOfLesson record) {
        jdbcTemplateObjectMySQL.update("UPDATE `tltsudb`.`ContentTableOfLesson`\n" +
                        "SET\n" +
                        "`OID` = ?,\n" +
                        "`TimeEnd` = ?,\n" +
                        "`TimeBeg` = ?\n" +
                        "WHERE `OID` = ?\n",
                record.getOID(),
                record.getTimeBeg(),
                record.getTimeEnd(),
                record.getOID());
    }

    public void addListMySQL(List<ContentTableOfLesson> list) {
        for (ContentTableOfLesson entry : list) {
            this.addRowMySQL(entry);
        }
    }

    public void addListOracle(List<ContentTableOfLesson> list) {
        for (ContentTableOfLesson entry : list) {
            this.addRowOracle(entry);
        }
    }

    public void addRowMySQL(ContentTableOfLesson record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("TimeBeg", record.getTimeBeg())
                .addValue("TimeEnd", record.getTimeEnd())
                .addValue("OID", record.getOID());
        this.jdbcInsertMySQL.execute(parameters);
    }

    public void addRowOracle(ContentTableOfLesson record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("TimeBeg", record.getTimeBeg())
                .addValue("TimeEnd", record.getTimeEnd())
                .addValue("OID", record.getOID());
        this.jdbcInsertOracle.execute(parameters);
    }

}

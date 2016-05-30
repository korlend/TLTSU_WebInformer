package CRUD.templates;

import CRUD.mappers.ContentOfScheduleMapper;
import CRUD.tables.ContentOfSchedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class ContentOfScheduleTemplate extends JdbcTemplate {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public ContentOfScheduleTemplate(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
        setDataSourceMySQL(jdbcTemplateMySQL);
        setDataSourceOracle(jdbcTemplateOracle);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("`contentofschedule`");
    }

    public void setDataSourceOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectOracle = jdbcTemplate;
        this.jdbcInsertOracle = new SimpleJdbcInsert(jdbcTemplate).withTableName("ContentOfSchedule");
    }

    public List<ContentOfSchedule> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select * from ContentOfSchedule", new ContentOfScheduleMapper());
    }

    public List<ContentOfSchedule> findAllOracle() {
        return this.jdbcTemplateObjectOracle.query("select OID," +
                "\"StartOn\"," +
                "\"EndOn\"," +
                "\"ModifiedTime\"," +
                "\"Discipline\"," +
                "\"KindOfWork\"," +
                "\"Lecturer\"," +
                "\"Auditorium\"," +
                "\"Stream\"," +
                "\"Group\" as GroupOID," +
                "\"SubGroup\" from \"ContentOfSchedule\"", new ContentOfScheduleMapper());
    }

    public void addListMySQL(List<ContentOfSchedule> list) {
        for (ContentOfSchedule entry : list) {
            this.addRowMySQL(entry);
        }
    }

    public void addListOracle(List<ContentOfSchedule> list) {
        for (ContentOfSchedule entry : list) {
            this.addRowOracle(entry);
        }
    }

    public void addRowMySQL(ContentOfSchedule record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Auditorium", record.getAuditorium() == 0 ? null : record.getAuditorium())
                .addValue("Discipline", record.getDiscipline() == 0 ? null : record.getDiscipline())
                .addValue("OID", record.getOID())
                .addValue("KindOfWork", record.getKindOfWork() == 0 ? null : record.getKindOfWork())
                .addValue("Lecturer", record.getLecturer() == 0 ? null : record.getLecturer())
                .addValue("Stream", record.getStream() == 0 ? null : record.getStream())
                .addValue("SubGroup", record.getSubGroup() == 0 ? null : record.getSubGroup())
                .addValue("ModifiedTime", record.getModifiedTime())
                .addValue("StartOn", record.getStartOn())
                .addValue("EndOn", record.getEndOn())
                .addValue("GroupOID", record.getGroup() == 0 ? null : record.getGroup());
        this.jdbcInsertMySQL.execute(parameters);
    }

    public void addRowOracle(ContentOfSchedule record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Auditorium", record.getAuditorium())
                .addValue("Discipline", record.getDiscipline())
                .addValue("OID", record.getOID())
                .addValue("KindOfWork", record.getKindOfWork())
                .addValue("Lecturer", record.getLecturer())
                .addValue("Stream", record.getStream())
                .addValue("SubGroup", record.getSubGroup())
                .addValue("ModifiedTime", record.getModifiedTime())
                .addValue("StartOn", record.getStartOn())
                .addValue("EndOn", record.getEndOn())
                .addValue("Group", record.getGroup());
        this.jdbcInsertOracle.execute(parameters);
    }
}

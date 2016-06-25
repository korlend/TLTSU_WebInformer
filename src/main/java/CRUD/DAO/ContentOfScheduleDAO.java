package CRUD.DAO;

import CRUD.mappers.custom.CustomContentOfScheduleMapper;
import CRUD.mappers.standard.ContentOfScheduleMapper;
import CRUD.tables.custom.CustomContentOfSchedule;
import CRUD.tables.standard.ContentOfSchedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class ContentOfScheduleDAO extends JdbcTemplate {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public ContentOfScheduleDAO(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
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

    public Timestamp getMaxModifiedTimeByGroupMySQL(Integer GroupOID) {
        String sql = "SELECT max(ModifiedTime) from contentofschedule\n" +
                "where GroupOID = "+ GroupOID +" " +
                "or subgroup in " +
                "(select OID from `subgroup` where `GroupOID` = "+ GroupOID +") " +
                "or stream in (select OID from `stream` where `Name` like " +
                "(select `Name` from `group` where `OID` = " + GroupOID + "))";
        return jdbcTemplateObjectMySQL.queryForObject(sql, Timestamp.class);
    }

    public Timestamp getMaxModifiedTimeByGroupOracle(Integer GroupOID) {
        String sql = "SELECT max(\"ModifiedTime\") from \"ContentOfSchedule\"\n" +
                "              where \"Group\" = " + GroupOID + "\n" +
                "                or \"SubGroup\" in \n" +
                "                (select \"OID\" from \"SubGroup\" where \"Group\" = " + GroupOID + ") \n" +
                "                or \"Stream\" in (select \"OID\" from \"Stream\" where \"Name\" like \n" +
                "                (select \"Name\" from \"Group\" where \"OID\" = " + GroupOID + "))";
        return jdbcTemplateObjectOracle.queryForObject(sql, Timestamp.class);
    }

    public List<ContentOfSchedule> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select * from ContentOfSchedule", new ContentOfScheduleMapper());
    }

    public List<CustomContentOfSchedule> findAllMySQL(String GroupName) {
        String sql = "select " +
                "cos.OID," +
                "cos.StartOn," +
                "cos.EndOn," +
                "cos.ModifiedTime," +
                "dis.`Name` as Discipline," +
                "kow.`Name` as KindOfWork," +
                "lect.FIO as Lecturer," +
                "aud.`Abbr` as Auditorium " +
                "from contentofschedule cos " +
                "inner join discipline dis on cos.`Discipline` = dis.`OID` " +
                "inner join kindofwork kow on cos.`KindOfWork` = kow.`OID` " +
                "inner join lecturer lect on cos.`Lecturer` = lect.`OID` " +
                "inner join auditorium aud  on cos.`Auditorium` = aud.`OID` " +
                "where GroupOID = (select OID from `group` where `name` like '%" + GroupName + "%' limit 1) " +
                "or subgroup in (select OID from `subgroup` where `GroupOID` in (select OID from `group` where `name` like '%" + GroupName + "%')) " +
                "or stream in (select OID from `stream` where `Name` like '%" + GroupName + "%');";
        return this.jdbcTemplateObjectMySQL.query(sql, new CustomContentOfScheduleMapper());
    }

    public List<CustomContentOfSchedule> findAllMySQL(String GroupName, String StartOn, String EndOn) {
        String sql = "select " +
                "cos.OID," +
                "cos.StartOn," +
                "cos.EndOn," +
                "cos.ModifiedTime," +
                "dis.`Name` as Discipline," +
                "kow.`Name` as KindOfWork," +
                "lect.FIO as Lecturer," +
                "aud.`Abbr` as Auditorium " +
                "from contentofschedule cos " +
                "inner join discipline dis on cos.`Discipline` = dis.`OID` " +
                "inner join kindofwork kow on cos.`KindOfWork` = kow.`OID` " +
                "inner join lecturer lect on cos.`Lecturer` = lect.`OID` " +
                "inner join auditorium aud  on cos.`Auditorium` = aud.`OID` " +
                "where GroupOID = (select OID from `group` where `name` like '%" + GroupName + "%' limit 1) " +
                "or subgroup in (select OID from `subgroup` where `GroupOID` in (select OID from `group` where `name` like '%" + GroupName + "%')) " +
                "or stream in (select OID from `stream` where `Name` like '%" + GroupName + "%') " +
                "and StartOn > '" + StartOn+ "' " +
                "and StartOn < '" + EndOn + "' ";
        return this.jdbcTemplateObjectMySQL.query(sql, new CustomContentOfScheduleMapper());
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

    public void deleteAllMySQL() {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM contentofschedule WHERE OID > 0");
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
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
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
        this.jdbcInsertMySQL.execute(parameterSource);
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

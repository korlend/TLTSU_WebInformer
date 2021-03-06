package com.korlend.CRUD.DAO;

import com.korlend.CRUD.mappers.custom.GroupNamesMapper;
import com.korlend.CRUD.mappers.custom.MMTStreamMapper;
import com.korlend.CRUD.mappers.standard.GroupMapper;
import com.korlend.CRUD.tables.custom.GroupMaxModTime;
import com.korlend.CRUD.tables.standard.Chair;
import com.korlend.CRUD.tables.standard.Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Артем on 22.05.2016.
 */
public class GroupDAO extends JdbcTemplate {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public GroupDAO(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
        setDataSourceMySQL(jdbcTemplateMySQL);
        setDataSourceOracle(jdbcTemplateOracle);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("`group`");
    }

    public void setDataSourceOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectOracle = jdbcTemplate;
        this.jdbcInsertOracle = new SimpleJdbcInsert(jdbcTemplate).withTableName("Group");
    }

    public List<Group> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select * from `group`", new GroupMapper());
    }

    public List<Group> findAllOracle() {
        return this.jdbcTemplateObjectOracle.query("select * from \"Group\"", new GroupMapper());
    }

    public List<Integer> findAllGroupOIDMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select `OID` from `group`", new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("OID");
            }
        });
    }

    public List<Integer> findAllGroupOIDOracle() {
        return this.jdbcTemplateObjectOracle.query("select \"OID\" from \"Group\"", new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getInt("OID");
            }
        });
    }

    public List<String> findAllGroupNamesMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select `name` from `group` group by `name`", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("Name");
            }
        });
    }

    public List<String> findAllGroupNamesOracle() {
        return this.jdbcTemplateObjectOracle.query("select \"Name\" from \"Group\" group by \"Name\"", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("Name");
            }
        });
    }

    public List<GroupMaxModTime> findAllGroupsMMTMySQL() {
        return this.jdbcTemplateObjectMySQL.query("SELECT " +
                        "max(c.ModifiedTime) as `MaxModifiedTime`, " +
                        "g.`Name` " +
                        "from contentofschedule c " +
                        "INNER JOIN `group` g ON g.OID = c.GroupOID " +
                        "group by g.`Name` ",
                new GroupNamesMapper());
    }

    public List<GroupMaxModTime> findAllGroupsMMTOracle() {
        return this.jdbcTemplateObjectOracle.query("SELECT " +
                        "MAX(c.\"ModifiedTime\") as \"MaxModifiedTime\", " +
                        "g.\"Name\" " +
                        "from \"ContentOfSchedule\" c " +
                        "INNER JOIN \"Group\" g ON g.\"OID\" = c.\"Group\" " +
                        "group by g.\"Name\"",
                new GroupNamesMapper());
    }

    public List<GroupMaxModTime> findAllSubGroupsMMTMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select \n" +
                        "max(c.ModifiedTime) as MaxModifiedTime, \n" +
                        "cg.`Name`\n" +
                        "from contentofschedule c\n" +
                        "inner join\n" +
                        "(SELECT\n" +
                        "g.`Name`,\n" +
                        "sg.OID\n" +
                        "FROM subgroup sg\n" +
                        "left join `group` g on sg.GroupOID = g.OID) cg\n" +
                        "on c.SubGroup = cg.OID\n" +
                        "group by cg.`Name`",
                new GroupNamesMapper());
    }

    public List<GroupMaxModTime> findAllSubGroupsMMTOracle() {
        return this.jdbcTemplateObjectOracle.query("select \n" +
                        "MAX(c.\"ModifiedTime\") as \"MaxModifiedTime\", \n" +
                        "cg.\"Name\" as \"Name\"\n" +
                        "from \"ContentOfSchedule\" c\n" +
                        "inner join\n" +
                        "(SELECT\n" +
                        "g.\"Name\",\n" +
                        "sg.\"OID\"\n" +
                        "FROM \"SubGroup\" sg\n" +
                        "left join \"Group\" g on sg.\"Group\" = g.\"OID\") cg\n" +
                        "on c.\"SubGroup\" = cg.\"OID\"\n" +
                        "group by cg.\"Name\"",
                new GroupNamesMapper());
    }

    public List<GroupMaxModTime> findAllStreamsMMTMySQL() {
        List<GroupMaxModTime> list = new ArrayList<>();
        this.jdbcTemplateObjectMySQL.query("select\n" +
                        "  max(c.ModifiedTime) as MaxModifiedTime,\n" +
                        "  s.`Name`,\n" +
                        "  c.`Stream`\n" +
                        "from contentofschedule c\n" +
                        "inner join stream s on s.OID = c.Stream\n" +
                        "  where s.`Name` not like '%Во%'\n" +
                        "  and s.`Name` not like '%Фи%'\n" +
                        "group by c.`Stream`,s.`Name`",
                new MMTStreamMapper()).stream().forEach(l -> list.addAll(l));
        return list;
    }

    public List<GroupMaxModTime> findAllStreamsMMTOracle() {
        List<GroupMaxModTime> list = new ArrayList<>();
        this.jdbcTemplateObjectOracle.query("select\n" +
                        "max(c.\"ModifiedTime\") as \"MaxModifiedTime\",\n" +
                        "s.\"Name\",\n" +
                        "c.\"Stream\"\n" +
                        "from \"ContentOfSchedule\" c\n" +
                        "inner join\n" +
                        "\"Stream\" s on s.\"OID\" = c.\"Stream\"\n" +
                        "where s.\"Name\" not like '%Во%'\n" +
                        "and s.\"Name\" not like '%Фи%'\n" +
                        "group by c.\"Stream\", s.\"Name\"",
                new MMTStreamMapper()).stream().forEach(l -> list.addAll(l));
        return list;
    }

    public void deleteAllMySQL() {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM `group` WHERE OID > 0");
    }

    public void deleteRowsMySQL(List<Group> list) {
        list.forEach(r -> deleteRowMySQL(r));
    }

    public void deleteRowMySQL(Group record) {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM `group` WHERE OID = " + record.getOID());
    }

    public void updateRowsMySQL(List<Group> records) {
        records.forEach(rec -> this.updateRowMySQL(rec));
    }

    public void updateRowMySQL(Group record) {
        jdbcTemplateObjectMySQL.update("UPDATE `tltsudb`.`group`\n" +
                        "SET\n" +
                        "`OID` = ?,\n" +
                        "`Course` = ?,\n" +
                        "`Faculty` = ?,\n" +
                        "`Name` = ?\n" +
                        "WHERE `OID` = ?\n",
                record.getOID(),
                record.getCourse(),
                record.getName(),
                record.getFaculty(),
                record.getOID());
    }

    public void addListMySQL(List<Group> list) {
        for (Group entry : list) {
            this.addRowMySQL(entry);
        }
    }

    public void addListOracle(List<Group> list) {
        for (Group entry : list) {
            this.addRowOracle(entry);
        }
    }

    public void addRowMySQL(Group record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Course", record.getCourse())
                .addValue("Name", record.getName())
                .addValue("Faculty", record.getFaculty())
                .addValue("OID", record.getOID());
        this.jdbcInsertMySQL.execute(parameters);
    }

    public void addRowOracle(Group record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Course", record.getCourse())
                .addValue("Name", record.getName())
                .addValue("Faculty", record.getFaculty())
                .addValue("OID", record.getOID());
        this.jdbcInsertOracle.execute(parameters);
    }
}

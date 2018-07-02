package com.korlend.CRUD.DAO;

import com.korlend.CRUD.mappers.standard.DisciplineMapper;
import com.korlend.CRUD.mappers.standard.FacultyMapper;
import com.korlend.CRUD.tables.standard.Discipline;
import com.korlend.CRUD.tables.standard.Faculty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Артем on 03.09.2016.
 */
public class FacultyDAO extends JdbcTemplate {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public FacultyDAO(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
        setDataSourceMySQL(jdbcTemplateMySQL);
        setDataSourceOracle(jdbcTemplateOracle);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("`faculty`");
    }

    public void setDataSourceOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectOracle = jdbcTemplate;
        this.jdbcInsertOracle = new SimpleJdbcInsert(jdbcTemplate).withTableName("Faculty");
    }

    public List<Faculty> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select * from faculty", new FacultyMapper());
    }

    public List<Faculty> findAllOracle() {
        return this.jdbcTemplateObjectOracle.query("select * from \"Faculty\"", new FacultyMapper());
    }

    public List<String> findAllFacultyNamesMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select `Abbr` from `Faculty` group by `Abbr`", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("Abbr");
            }
        });
    }

    public void deleteAllMySQL() {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM faculty WHERE OID > 0");
    }

    public void deleteRowsMySQL(List<Faculty> list) {
        list.forEach(r -> deleteRowMySQL(r));
    }

    public void deleteRowMySQL(Faculty record) {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM faculty WHERE OID = " + record.getOID());
    }

    public void updateRowsMySQL(List<Faculty> records) {
        records.forEach(rec -> this.updateRowMySQL(rec));
    }

    public void updateRowMySQL(Faculty record) {
        jdbcTemplateObjectMySQL.update("UPDATE `tltsudb`.`faculty`\n" +
                        "SET\n" +
                        "`OID` = ?,\n" +
                        "`Abbr` = ?,\n" +
                        "`Name` = ?\n" +
                        "WHERE `OID` = ?\n",
                record.getOID(),
                record.getAbbr(),
                record.getName(),
                record.getOID());
    }

    public void addListMySQL(List<Faculty> list) {
        for (Faculty entry : list) {
            this.addRowMySQL(entry);
        }
    }

    public void addListOracle(List<Faculty> list) {
        for (Faculty entry : list) {
            this.addRowOracle(entry);
        }
    }

    public void addRowMySQL(Faculty record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Name", record.getName())
                .addValue("Abbr", record.getAbbr())
                .addValue("OID", record.getOID());
        this.jdbcInsertMySQL.execute(parameters);
    }

    public void addRowOracle(Faculty record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Name", record.getName())
                .addValue("Abbr", record.getAbbr())
                .addValue("OID", record.getOID());
        this.jdbcInsertOracle.execute(parameters);
    }
}
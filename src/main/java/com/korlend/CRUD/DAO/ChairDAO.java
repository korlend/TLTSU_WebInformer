package com.korlend.CRUD.DAO;

import com.korlend.CRUD.mappers.standard.ChairMapper;
import com.korlend.CRUD.tables.standard.Chair;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class ChairDAO extends JdbcTemplate {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public ChairDAO(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
        setDataSourceMySQL(jdbcTemplateMySQL);
        setDataSourceOracle(jdbcTemplateOracle);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("`chair`");
    }

    public void setDataSourceOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectOracle = jdbcTemplate;
        this.jdbcInsertOracle = new SimpleJdbcInsert(jdbcTemplate).withTableName("Chair");
    }

    public List<Chair> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select * from chair", new ChairMapper());
    }

    public List<Chair> findAllOracle() {
        return this.jdbcTemplateObjectOracle.query("select * from \"Chair\"", new ChairMapper());
    }

    public void deleteAllMySQL() {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM chair WHERE OID > 0");
    }

    public void deleteRowsMySQL(List<Chair> list) {
        list.forEach(r -> deleteRowMySQL(r));
    }

    public void deleteRowMySQL(Chair record) {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM chair WHERE OID = " + record.getOID());
    }

    public void updateRowsMySQL(List<Chair> records) {
        records.forEach(rec -> this.updateRowMySQL(rec));
    }

    public void updateRowMySQL(Chair record) {
        jdbcTemplateObjectMySQL.update("UPDATE `tltsudb`.`chair`\n" +
                        "SET\n" +
                        "`OID` = ?,\n" +
                        "`Name` = ?,\n" +
                        "`Faculty` = ?\n" +
                        "WHERE `OID` = ?\n",
                record.getOID(),
                record.getName(),
                record.getFaculty(),
                record.getOID());
    }

    public void addListMySQL(List<Chair> list) {
        for (Chair entry : list) {
            this.addRowMySQL(entry);
        }
    }

    public void addListOracle(List<Chair> list) {
        for (Chair entry : list) {
            this.addRowOracle(entry);
        }
    }

    public void addRowMySQL(Chair record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                //.addValue("Auditorium", record.getAuditorium())
                .addValue("Name", record.getName())
                .addValue("Faculty", record.getFaculty())
                .addValue("OID", record.getOID());
        this.jdbcInsertMySQL.execute(parameters);
    }

    public void addRowOracle(Chair record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                //.addValue("Auditorium", record.getAuditorium())
                .addValue("Name", record.getName())
                .addValue("Faculty", record.getFaculty())
                .addValue("OID", record.getOID());
        this.jdbcInsertOracle.execute(parameters);
    }
}

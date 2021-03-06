package com.korlend.CRUD.DAO;

import com.korlend.CRUD.mappers.standard.StreamMapper;
import com.korlend.CRUD.tables.standard.Stream;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class StreamDAO extends JdbcTemplate {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public StreamDAO(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
        setDataSourceMySQL(jdbcTemplateMySQL);
        setDataSourceOracle(jdbcTemplateOracle);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("`stream`");
    }

    public void setDataSourceOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectOracle = jdbcTemplate;
        this.jdbcInsertOracle = new SimpleJdbcInsert(jdbcTemplate).withTableName("Stream");
    }

    public List<Stream> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select * from Stream", new StreamMapper());
    }

    public List<Stream> findAllOracle() {
        return this.jdbcTemplateObjectOracle.query("select * from \"Stream\"", new StreamMapper());
    }

    public void deleteAllMySQL() {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM Stream WHERE OID > 0");
    }

    public void deleteRowsMySQL(List<Stream> list) {
        list.forEach(r -> deleteRowMySQL(r));
    }

    public void deleteRowMySQL(Stream record) {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM stream WHERE OID = " + record.getOID());
    }

    public void updateRowsMySQL(List<Stream> records) {
        records.forEach(rec -> this.updateRowMySQL(rec));
    }

    public void updateRowMySQL(Stream record) {
        jdbcTemplateObjectMySQL.update("UPDATE `tltsudb`.`stream`\n" +
                        "SET\n" +
                        "`OID` = ?,\n" +
                        "`Name` = ?\n" +
                        "WHERE `OID` = ?\n",
                record.getOID(),
                record.getName(),
                record.getOID());
    }

    public void addListMySQL(List<Stream> list) {
        for (Stream entry : list) {
            this.addRowMySQL(entry);
        }
    }

    public void addListOracle(List<Stream> list) {
        for (Stream entry : list) {
            this.addRowOracle(entry);
        }
    }

    public void addRowMySQL(Stream record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Name", record.getName())
                .addValue("OID", record.getOID());
        this.jdbcInsertMySQL.execute(parameters);
    }

    public void addRowOracle(Stream record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Name", record.getName())
                .addValue("OID", record.getOID());
        this.jdbcInsertOracle.execute(parameters);
    }
}

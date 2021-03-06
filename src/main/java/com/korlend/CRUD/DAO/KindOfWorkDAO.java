package com.korlend.CRUD.DAO;

import com.korlend.CRUD.mappers.standard.KindOfWorkMapper;
import com.korlend.CRUD.tables.standard.Discipline;
import com.korlend.CRUD.tables.standard.KindOfWork;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class KindOfWorkDAO extends JdbcTemplate {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public KindOfWorkDAO(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
        setDataSourceMySQL(jdbcTemplateMySQL);
        setDataSourceOracle(jdbcTemplateOracle);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("`kindOfWork`");
    }

    public void setDataSourceOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectOracle = jdbcTemplate;
        this.jdbcInsertOracle = new SimpleJdbcInsert(jdbcTemplate).withTableName("KindOfWork");
    }

    public List<KindOfWork> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select * from KindOfWork", new KindOfWorkMapper());
    }

    public List<KindOfWork> findAllOracle() {
        return this.jdbcTemplateObjectOracle.query("select * from \"KindOfWork\"", new KindOfWorkMapper());
    }

    public void deleteAllMySQL() {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM kindofwork WHERE OID > 0");
    }

    public void deleteRowsMySQL(List<KindOfWork> list) {
        list.forEach(r -> deleteRowMySQL(r));
    }

    public void deleteRowMySQL(KindOfWork record) {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM kindofwork WHERE OID = " + record.getOID());
    }

    public void updateRowsMySQL(List<KindOfWork> records) {
        records.forEach(rec -> this.updateRowMySQL(rec));
    }

    public void updateRowMySQL(KindOfWork record) {
        jdbcTemplateObjectMySQL.update("UPDATE `tltsudb`.`kindofwork`\n" +
                        "SET\n" +
                        "`OID` = ?,\n" +
                        "`Name` = ?,\n" +
                        "`Abbr` = ?\n" +
                        "WHERE `OID` = ?\n",
                record.getOID(),
                record.getName(),
                record.getAbbr(),
                record.getOID());
    }

    public void addListMySQL(List<KindOfWork> list) {
        for (KindOfWork entry : list) {
            this.addRowMySQL(entry);
        }
    }

    public void addListOracle(List<KindOfWork> list) {
        for (KindOfWork entry : list) {
            this.addRowOracle(entry);
        }
    }

    public void addRowMySQL(KindOfWork record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Abbr", record.getAbbr())
                .addValue("Name", record.getName())
                .addValue("OID", record.getOID());
        this.jdbcInsertMySQL.execute(parameters);
    }

    public void addRowOracle(KindOfWork record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Abbr", record.getAbbr())
                .addValue("Name", record.getName())
                .addValue("OID", record.getOID());
        this.jdbcInsertOracle.execute(parameters);
    }
}

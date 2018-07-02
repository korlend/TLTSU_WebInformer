package com.korlend.CRUD.DAO;

import com.korlend.CRUD.mappers.standard.SubGroupMapper;
import com.korlend.CRUD.tables.standard.SubGroup;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class SubGroupDAO extends JdbcTemplate {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public SubGroupDAO(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
        setDataSourceMySQL(jdbcTemplateMySQL);
        setDataSourceOracle(jdbcTemplateOracle);
    }

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectMySQL = jdbcTemplate;
        this.jdbcInsertMySQL = new SimpleJdbcInsert(jdbcTemplate).withTableName("`subgroup`");
    }

    public void setDataSourceOracle(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplateObjectOracle = jdbcTemplate;
        this.jdbcInsertOracle = new SimpleJdbcInsert(jdbcTemplate).withTableName("SubGroup");
    }

    public List<SubGroup> findAllMySQL() {
        return this.jdbcTemplateObjectMySQL.query("select * from SubGroup", new SubGroupMapper());
    }

    public List<SubGroup> findAllOracle() {
        return this.jdbcTemplateObjectOracle.query("select \"OID\", \"Group\" as GroupOID from \"SubGroup\"", new SubGroupMapper());
    }

    public void deleteAllMySQL() {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM SubGroup WHERE OID > 0");
    }

    public void deleteRowsMySQL(List<SubGroup> list) {
        list.forEach(r -> deleteRowMySQL(r));
    }

    public void deleteRowMySQL(SubGroup record) {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM subgroup WHERE OID = " + record.getOID());
    }

    public void updateRowsMySQL(List<SubGroup> records) {
        records.forEach(rec -> this.updateRowMySQL(rec));
    }

    public void updateRowMySQL(SubGroup record) {
        jdbcTemplateObjectMySQL.update("UPDATE `tltsudb`.`subgroup`\n" +
                        "SET\n" +
                        "`OID` = ?,\n" +
                        "`GroupOID` = ?\n" +
                        "WHERE `OID` = ?\n",
                record.getOID(),
                record.getGroup(),
                record.getOID());
    }

    public void addListMySQL(List<SubGroup> list) {
        for (SubGroup entry : list) {
            this.addRowMySQL(entry);
        }
    }

    public void addListOracle(List<SubGroup> list) {
        for (SubGroup entry : list) {
            this.addRowOracle(entry);
        }
    }

    public void addRowMySQL(SubGroup record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("GroupOID", record.getGroup())
                .addValue("OID", record.getOID());
        this.jdbcInsertMySQL.execute(parameters);
    }

    public void addRowOracle(SubGroup record) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("Group", record.getGroup())
                .addValue("OID", record.getOID());
        this.jdbcInsertOracle.execute(parameters);
    }
}

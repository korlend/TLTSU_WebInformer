package com.korlend.CRUD.DAO;

import com.korlend.CRUD.mappers.standard.TypeOfAuditoriumMapper;
import com.korlend.CRUD.tables.standard.SubGroup;
import com.korlend.CRUD.tables.standard.TypeOfAuditorium;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class TypeOfAuditoriumDAO extends JdbcTemplate {
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;
    private SimpleJdbcInsert jdbcInsertMySQL;
    private SimpleJdbcInsert jdbcInsertOracle;

    public TypeOfAuditoriumDAO(JdbcTemplate jdbcTemplateMySQL, JdbcTemplate jdbcTemplateOracle) {
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

    public void deleteAllMySQL() {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM TypeOfAuditorium WHERE OID > 0");
    }

    public void deleteRowsMySQL(List<TypeOfAuditorium> list) {
        list.forEach(r -> deleteRowMySQL(r));
    }

    public void deleteRowMySQL(TypeOfAuditorium record) {
        this.jdbcTemplateObjectMySQL.execute("DELETE FROM typeofauditorium WHERE OID = " + record.getOID());
    }

    public void updateRowsMySQL(List<TypeOfAuditorium> records) {
        records.forEach(rec -> this.updateRowMySQL(rec));
    }

    public void updateRowMySQL(TypeOfAuditorium record) {
        jdbcTemplateObjectMySQL.update("UPDATE `tltsudb`.`typeofauditorium`\n" +
                        "SET\n" +
                        "`OID` = <?,\n" +
                        "`Name` = ?\n" +
                        "WHERE `OID` = ?\n",
                record.getOID(),
                record.getName(),
                record.getOID());
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

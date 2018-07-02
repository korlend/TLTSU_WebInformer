package com.korlend.CRUD.DAO;

import com.korlend.CRUD.tables.Table;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public interface Template<T extends Table> {

    public void setDataSourceMySQL(JdbcTemplate jdbcTemplate);

    public void setDataSourceOracle(JdbcTemplate jdbcTemplate);

    public List<Class<T>> findAllMySQL();

    public List<Class<T>> findAllOracle();

    public void addListMySQL(List<T> list);

    public void addListOracle(List<T> list);

    public void addRowMySQL(T record);

    public void addRowOracle(T record);
}

package CRUD.templates;

import CRUD.mappers.AuditoriumMapper;
import CRUD.tables.Auditorium;
import CRUD.tables.Table;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

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

package CRUD.mappers.standard;

import CRUD.tables.standard.Discipline;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 14.05.2016.
 */
public class DisciplineMapper implements RowMapper<Discipline> {
    public Discipline mapRow(ResultSet rs, int rowNum) throws SQLException {
        Discipline discipline = new Discipline();
        discipline.setOID(rs.getInt("OID"));
        discipline.setName(rs.getString("Name"));
        return discipline;
    }
}

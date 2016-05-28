package CRUD.mappers;

import CRUD.tables.TypeOfAuditorium;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 14.05.2016.
 */
public class TypeOfAuditoriumMapper implements RowMapper<TypeOfAuditorium> {
    public TypeOfAuditorium mapRow(ResultSet rs, int rowNum) throws SQLException {
        TypeOfAuditorium typeOfAuditorium = new TypeOfAuditorium();
        typeOfAuditorium.setOID(rs.getInt("OID"));
        typeOfAuditorium.setName(rs.getString("Name"));
        return typeOfAuditorium;
    }
}

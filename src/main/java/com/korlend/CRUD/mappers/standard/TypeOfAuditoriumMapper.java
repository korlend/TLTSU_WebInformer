package com.korlend.CRUD.mappers.standard;

import com.korlend.CRUD.tables.standard.TypeOfAuditorium;
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

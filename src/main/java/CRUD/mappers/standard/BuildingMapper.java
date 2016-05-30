package CRUD.mappers.standard;

import CRUD.tables.standard.Building;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 14.05.2016.
 */
public class BuildingMapper implements RowMapper<Building> {
    public Building mapRow(ResultSet rs, int rowNum) throws SQLException {
        Building building = new Building();
        building.setOID(rs.getInt("OID"));
        building.setName(rs.getString("Name"));
        //building.setAddress(); TODO: got no address in Oracle database, set it internally
        return building;
    }
}
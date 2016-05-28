package CRUD.oldmappers;

import CRUD.oldtables.Chairs;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 04.05.2016.
 */
public class ChairsMapper implements RowMapper<Chairs> {
    public Chairs mapRow(ResultSet rs, int rowNum) throws SQLException {
        Chairs chairs = new Chairs();
        chairs.setIdChair(rs.getInt("OID"));
        chairs.setName(rs.getString("Abbr"));
        chairs.setFullName(rs.getString("Name"));
        return chairs;
    }
}

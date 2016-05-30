package CRUD.mappers.standard;

import CRUD.tables.standard.Stream;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 14.05.2016.
 */
public class StreamMapper implements RowMapper<Stream> {
    public Stream mapRow(ResultSet rs, int rowNum) throws SQLException {
        Stream stream = new Stream();
        stream.setOID(rs.getInt("OID"));
        stream.setName(rs.getString("Name"));
        return stream;
    }
}

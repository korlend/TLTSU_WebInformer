package CRUD.mappers;

import CRUD.tables.KindOfWork;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 14.05.2016.
 */
public class KindOfWorkMapper implements RowMapper<KindOfWork> {
    public KindOfWork mapRow(ResultSet rs, int rowNum) throws SQLException {
        KindOfWork kindOfWork = new KindOfWork();
        kindOfWork.setOID(rs.getInt("OID"));
        kindOfWork.setName(rs.getString("Name"));
        kindOfWork.setAbbr(rs.getString("Abbr"));
        return kindOfWork;
    }
}

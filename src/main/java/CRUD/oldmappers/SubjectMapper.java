package CRUD.oldmappers;

import CRUD.oldtables.Subjects;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 05.05.2016.
 */
public class SubjectMapper implements RowMapper<Subjects> {
    public Subjects mapRow(ResultSet rs, int rowNum) throws SQLException {
        Subjects subjects = new Subjects();
        subjects.setIdSubject(rs.getInt("OID"));
        subjects.setName(rs.getString("Name"));
        return subjects;
    }
}

package CRUD.oldmappers;

import CRUD.oldtables.LessonTypes;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 04.05.2016.
 */
public class LessonTypesMapper implements RowMapper<LessonTypes> {
    public LessonTypes mapRow(ResultSet rs, int rowNum) throws SQLException {
        LessonTypes lessonTypes = new LessonTypes();
        lessonTypes.setIdLessonType(rs.getInt("OID"));
        lessonTypes.setName(rs.getString("Name"));
        return lessonTypes;
    }
}

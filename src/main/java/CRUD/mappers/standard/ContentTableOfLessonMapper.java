package CRUD.mappers.standard;

import java.sql.ResultSet;
import java.sql.SQLException;
import CRUD.tables.standard.ContentTableOfLesson;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by Артем on 07.07.2016.
 */
public class ContentTableOfLessonMapper implements RowMapper<ContentTableOfLesson> {
public ContentTableOfLesson mapRow(ResultSet rs, int rowNum) throws SQLException {
        ContentTableOfLesson contentTableOfLesson = new ContentTableOfLesson();
        contentTableOfLesson.setOID(rs.getInt("OID"));
        contentTableOfLesson.setTimeBeg(rs.getInt("TimeBeg"));
        contentTableOfLesson.setTimeEnd(rs.getInt("TimeEnd"));
        return contentTableOfLesson;
        }
}

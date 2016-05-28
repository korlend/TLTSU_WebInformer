package CRUD.oldmappers;

/**
 * Created by Артем on 01.05.2016.
 */
import java.sql.ResultSet;
import java.sql.SQLException;

import CRUD.oldtables.Student;
import org.springframework.jdbc.core.RowMapper;

public class StudentMapper implements RowMapper<Student> {
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("OID"));
        student.setFullName(rs.getString("FIO"));
        student.setGroupId(rs.getInt("Group"));
        return student;

    }
}

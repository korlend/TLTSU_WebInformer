package CRUD.oldmappers;

import CRUD.oldtables.Professors;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Артем on 05.05.2016.
 */
public class ProfessorsMapper implements RowMapper<Professors> {
    public Professors mapRow(ResultSet rs, int rowNum) throws SQLException {
        Professors professors = new Professors();
        professors.setIdProfessor(rs.getInt("OID"));
        String name[] = rs.getString("Name").split(" ");
        String splittedName = name[0];
        splittedName += " " + name[1].charAt(0) + ". " + name[2].charAt(0) + ".";
        professors.setName(splittedName);
        professors.setFullName(rs.getString("Name"));
        professors.setChairId(rs.getInt("Chair"));
        return professors;
    }
}

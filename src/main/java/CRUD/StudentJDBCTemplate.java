package CRUD;

/**
 * Created by Артем on 01.05.2016.
 */
import java.util.List;
import javax.sql.DataSource;

import CRUD.oldmappers.StudentMapper;
import CRUD.oldtables.Student;
import org.springframework.jdbc.core.JdbcTemplate;

public class StudentJDBCTemplate{
    private DataSource dataSourceMySQL;
    private DataSource dataSourceOracle;
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;

    public void setDataSourceMySQL(DataSource dataSource) {
        this.dataSourceMySQL = dataSource;
        this.jdbcTemplateObjectMySQL = new JdbcTemplate(this.dataSourceMySQL);
    }

    public void setDataSourceOracle(DataSource dataSource) {
        this.dataSourceOracle = dataSource;
        this.jdbcTemplateObjectOracle = new JdbcTemplate(this.dataSourceOracle);
    }

    public void create(String name, Integer age) {
        String SQL = "insert into Student (name, age) values (?, ?)";

        jdbcTemplateObjectMySQL.update(SQL, name, age);
        System.out.println("Created Record Name = " + name + " Age = " + age);
        return;
    }

    public Student getStudent(Integer id) {
        String SQL = "select * from Student where id = ?";
        Student student = jdbcTemplateObjectMySQL.queryForObject(SQL,
                new Object[]{id}, new StudentMapper());
        return student;
    }

    public List<Student> listStudents() {
        String SQL = "select * from Student";
        List <Student> students = jdbcTemplateObjectMySQL.query(SQL,
                new StudentMapper());
        return students;
    }

    public void delete(Integer id){
        String SQL = "delete from Student where id = ?";
        jdbcTemplateObjectMySQL.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id );
        return;
    }

    public void update(Integer id, Integer age){
        String SQL = "update Student set age = ? where id = ?";
        jdbcTemplateObjectMySQL.update(SQL, age, id);
        System.out.println("Updated Record with ID = " + id );
        return;
    }

}

package CRUD;

import CRUD.tables.*;
import CRUD.mappers.*;
import CRUD.templates.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Артем on 04.05.2016.
 */
public class MainTemplateJDBC {

    /**
     * tables to collect data
     */
    private List<Auditorium> auditorium;
    private List<Building> building;
    private List<Chair> chairs;
    private List<ContentOfSchedule> contentOfSchedule;
    private List<Discipline> discipline;
    private List<Group> group;
    private List<KindOfWork> kindOfWork;
    private List<Lecturer> lecturer;
    private List<Stream> stream;
    private List<SubGroup> subGroup;
    private List<TypeOfAuditorium> typeOfAuditorium;


    private BuildingTemplate buildingTemplate;
    private ChairTemplate chairTemplate;
    private DisciplineTemplate disciplineTemplate;
    private StreamTemplate streamTemplate;
    private TypeOfAuditoriumTemplate typeOfAuditoriumTemplate;
    private LecturerTemplate lecturerTemplate;
    private KindOfWorkTemplate kindOfWorkTemplate;
    private GroupTemplate groupTemplate;
    private SubGroupTemplate subGroupTemplate;
    private AuditoriumTemplate auditoriumTemplate;
    private ContentOfScheduleTemplate contentOfScheduleTemplate;

    /**
     * connect data
     * setted in Beans.xml
     */
    //private DataSource dataSourceMySQL;
    //private DataSource dataSourceOracle;
    private JdbcTemplate jdbcTemplateObjectMySQL;
    private JdbcTemplate jdbcTemplateObjectOracle;

    public void setDataSourceMySQL(DataSource dataSource) {
        //this.dataSourceMySQL = dataSource;
        this.jdbcTemplateObjectMySQL = new JdbcTemplate(dataSource);
    }

    public void setDataSourceOracle(DataSource dataSource) {
        //this.dataSourceOracle = dataSource;
        this.jdbcTemplateObjectOracle = new JdbcTemplate(dataSource);
    }

    public void setTemplates() {
        /**
         * initialization of templates
         */
        buildingTemplate = new BuildingTemplate(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        chairTemplate = new ChairTemplate(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        disciplineTemplate = new DisciplineTemplate(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        streamTemplate = new StreamTemplate(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        typeOfAuditoriumTemplate = new TypeOfAuditoriumTemplate(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        lecturerTemplate = new LecturerTemplate(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        kindOfWorkTemplate = new KindOfWorkTemplate(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        groupTemplate = new GroupTemplate(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        subGroupTemplate = new SubGroupTemplate(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        auditoriumTemplate = new AuditoriumTemplate(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        contentOfScheduleTemplate = new ContentOfScheduleTemplate(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
    }

    public void findAllOracle() {

        buildingTemplate.findAllOracle();
        chairTemplate.findAllOracle();
        disciplineTemplate.findAllOracle();
        streamTemplate.findAllOracle();
        typeOfAuditoriumTemplate.findAllOracle();
        lecturerTemplate.findAllOracle();
        kindOfWorkTemplate.findAllOracle();
        groupTemplate.findAllOracle();
        subGroupTemplate.findAllOracle();
        auditoriumTemplate.findAllOracle();

        contentOfScheduleTemplate.findAllOracle();
    }

    public void findAllMySQL() {
        buildingTemplate.findAllMySQL();
    }

    public List<ContentOfSchedule> ret() {
        return contentOfScheduleTemplate.findAllOracle();
    }

    public void collectData() {
        /**
         * 0 lvl

        buildingTemplate.addListMySQL(buildingTemplate.findAllOracle());
        chairTemplate.addListMySQL(chairTemplate.findAllOracle());
        disciplineTemplate.addListMySQL(disciplineTemplate.findAllOracle());
        streamTemplate.addListMySQL(streamTemplate.findAllOracle());
        typeOfAuditoriumTemplate.addListMySQL(typeOfAuditoriumTemplate.findAllOracle());
        lecturerTemplate.addListMySQL(lecturerTemplate.findAllOracle());
        kindOfWorkTemplate.addListMySQL(kindOfWorkTemplate.findAllOracle());
        */
        /**
         * 1 lvl

        groupTemplate.addListMySQL(groupTemplate.findAllOracle());
        subGroupTemplate.addListMySQL(subGroupTemplate.findAllOracle());
        auditoriumTemplate.addListMySQL(auditoriumTemplate.findAllOracle());
        */
        /**
         * 2 lvl
         */

        contentOfScheduleTemplate.addListMySQL(contentOfScheduleTemplate.findAllOracle());
    }
    /**
     *
     */
    public void unknownFunction() {



    }


}

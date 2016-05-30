package CRUD;

import CRUD.tables.standard.*;
import CRUD.DAO.*;
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


    private BuildingDAO buildingDAO;
    private ChairDAO chairDAO;
    private DisciplineDAO disciplineDAO;
    private StreamDAO streamDAO;
    private TypeOfAuditoriumDAO typeOfAuditoriumDAO;
    private LecturerDAO lecturerDAO;
    private KindOfWorkDAO kindOfWorkDAO;
    private GroupDAO groupDAO;
    private SubGroupDAO subGroupDAO;
    private AuditoriumDAO auditoriumDAO;
    private ContentOfScheduleDAO contentOfScheduleDAO;

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
        buildingDAO = new BuildingDAO(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        chairDAO = new ChairDAO(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        disciplineDAO = new DisciplineDAO(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        streamDAO = new StreamDAO(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        typeOfAuditoriumDAO = new TypeOfAuditoriumDAO(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        lecturerDAO = new LecturerDAO(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        kindOfWorkDAO = new KindOfWorkDAO(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        groupDAO = new GroupDAO(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        subGroupDAO = new SubGroupDAO(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        auditoriumDAO = new AuditoriumDAO(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
        contentOfScheduleDAO = new ContentOfScheduleDAO(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
    }

    public void findAllOracle() {

        buildingDAO.findAllOracle();
        chairDAO.findAllOracle();
        disciplineDAO.findAllOracle();
        streamDAO.findAllOracle();
        typeOfAuditoriumDAO.findAllOracle();
        lecturerDAO.findAllOracle();
        kindOfWorkDAO.findAllOracle();
        groupDAO.findAllOracle();
        subGroupDAO.findAllOracle();
        auditoriumDAO.findAllOracle();

        contentOfScheduleDAO.findAllOracle();
    }

    public void findAllMySQL() {
        buildingDAO.findAllMySQL();
    }

    public List<ContentOfSchedule> ret() {
        return contentOfScheduleDAO.findAllOracle();
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

        contentOfScheduleDAO.addListMySQL(contentOfScheduleDAO.findAllOracle());
    }
    /**
     *
     */
    public void unknownFunction() {



    }


}

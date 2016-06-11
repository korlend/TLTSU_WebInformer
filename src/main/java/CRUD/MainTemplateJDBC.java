package CRUD;

import CRUD.tables.custom.CustomContentOfSchedule;
import CRUD.tables.custom.GroupMaxModTime;
import CRUD.tables.standard.*;
import CRUD.DAO.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
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

    public void initializeTemplates() {
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

    public List<String> getAllChangedGroups() {
        List<String> groupsList = new ArrayList<>();

        return groupsList;
    }

    public List<ContentOfSchedule> findAllMySQL() {
        return contentOfScheduleDAO.findAllMySQL();
    }

    public List<CustomContentOfSchedule> findAllMySQL(String group) {
        return contentOfScheduleDAO.findAllMySQL(group);
    }

    public List<CustomContentOfSchedule> findAllMySQL(String group, String startOn, String endOn) {
        return contentOfScheduleDAO.findAllMySQL(group, startOn, endOn);
    }

    public List<ContentOfSchedule> findAllOracle() {
        return contentOfScheduleDAO.findAllOracle();
    }

    public List<String> updateDatabase() {
        List<String> updatedGroups = new ArrayList<>();
        List<GroupMaxModTime> mysqlGroups = groupDAO.findAllGroupsMaxModTimeMySQL();
        List<GroupMaxModTime> oracleGroups = groupDAO.findAllGroupsMaxModTimeOracle();
        groupDAO.findAllOracle();
        if (mysqlGroups.size() != oracleGroups.size()) {
            System.out.println("очень плохо");
            //возможно появилась новая группа, как в случае нового учебного года
            //надо добавить недостающие группы и вернуть саму себя функцию
            //return this.getAllChangedGroups();
            return new ArrayList<>();//заглушка пока что
        }
        else
        {
            System.out.println("все в порядке");
        }
        for (int i = 0; i < mysqlGroups.size(); i++) {
            GroupMaxModTime groupMySQL = mysqlGroups.get(i);
            GroupMaxModTime groupOracle = oracleGroups.get(i);

            if (!groupMySQL.equals(groupOracle)) {
                if (!groupMySQL.getGroupName().equals(groupOracle.getGroupName())) {
                    //надо добавить недостающие группы и вернуть саму себя функцию
                    //return this.getAllChangedGroups();
                    return new ArrayList<>();//заглушка пока что
                }
                if (!groupMySQL.getMaxModTime().equals(groupOracle.getMaxModTime())) {
                    //оставляем эту группу, в ее расписании что-то поменялось
                    //добавляем ее в возвращаемый список измененных групп
                    updatedGroups.add(groupMySQL.getGroupName());
                }
            }
            else {
                mysqlGroups.remove(i);
                oracleGroups.remove(i);
                i--;
            }
        }
        return updatedGroups;
    }

    public void simpleCollectData() {

        /**
         * этот метод просто копирует таблицы из oracle в mysql
         * соблюден порядок формирования внешних ключей
         * первыми создаются таблицы, где не используются ключи и т.д.
         */
        /**
         * 0 lvl
         */
        buildingDAO.addListMySQL(buildingDAO.findAllOracle());
        chairDAO.addListMySQL(chairDAO.findAllOracle());
        disciplineDAO.addListMySQL(disciplineDAO.findAllOracle());
        streamDAO.addListMySQL(streamDAO.findAllOracle());
        typeOfAuditoriumDAO.addListMySQL(typeOfAuditoriumDAO.findAllOracle());
        lecturerDAO.addListMySQL(lecturerDAO.findAllOracle());
        kindOfWorkDAO.addListMySQL(kindOfWorkDAO.findAllOracle());

        /**
         * 1 lvl
         */
        groupDAO.addListMySQL(groupDAO.findAllOracle());
        subGroupDAO.addListMySQL(subGroupDAO.findAllOracle());
        auditoriumDAO.addListMySQL(auditoriumDAO.findAllOracle());

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


    public AuditoriumDAO getAuditoriumDAO() {
        return auditoriumDAO;
    }

    public BuildingDAO getBuildingDAO() {
        return buildingDAO;
    }

    public ChairDAO getChairDAO() {
        return chairDAO;
    }

    public ContentOfScheduleDAO getContentOfScheduleDAO() {
        return contentOfScheduleDAO;
    }

    public DisciplineDAO getDisciplineDAO() {
        return disciplineDAO;
    }

    public GroupDAO getGroupDAO() {
        return groupDAO;
    }

    public KindOfWorkDAO getKindOfWorkDAO() {
        return kindOfWorkDAO;
    }

    public LecturerDAO getLecturerDAO() {
        return lecturerDAO;
    }

    public StreamDAO getStreamDAO() {
        return streamDAO;
    }

    public SubGroupDAO getSubGroupDAO() {
        return subGroupDAO;
    }

    public TypeOfAuditoriumDAO getTypeOfAuditoriumDAO() {
        return typeOfAuditoriumDAO;
    }
}

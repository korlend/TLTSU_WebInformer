package CRUD;

import CRUD.tables.custom.ConnectedUsers;
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
    private ConnectedUsersDAO connectedUsersDAO;

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
        connectedUsersDAO = new ConnectedUsersDAO(jdbcTemplateObjectMySQL);
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

    public void addConnUser(String device_id, String preferred_groups) {
        if (this.connectedUsersDAO.findAllMySQL(device_id).size() == 0)
            this.connectedUsersDAO.addRowMySQL(new ConnectedUsers(device_id, preferred_groups));
    }

    public List<ContentOfSchedule> findAllOracle() {
        return contentOfScheduleDAO.findAllOracle();
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

    public List<String> updateDatabase() {
        List<String> updatedGroups = new ArrayList<>();
        List<GroupMaxModTime> mysqlGroups = groupDAO.findAllGroupsMaxModTimeMySQL();
        List<GroupMaxModTime> oracleGroups = groupDAO.findAllGroupsMaxModTimeOracle();
        groupDAO.findAllOracle();
        /*
        if (mysqlGroups.size() != oracleGroups.size()) {
            updateAllExceptSchedule();
            return this.updateDatabase();
        }
        */
        for (int i = 0; i < mysqlGroups.size(); i++) {
            GroupMaxModTime groupMySQL = mysqlGroups.get(i);
            GroupMaxModTime groupOracle = oracleGroups.get(i);
            System.out.println("M: "+groupMySQL.getGroupName() + ": " + groupMySQL.getMaxModTime());
            System.out.println("O: "+groupOracle.getGroupName() + ": " + groupOracle.getMaxModTime());
            /*
            if (!groupMySQL.equals(groupOracle)) {
                if (!groupMySQL.getGroupName().equals(groupOracle.getGroupName())) {
                    //updateAllExceptSchedule();
                    //return this.updateDatabase();
                    return updatedGroups;
                }
                if (!groupMySQL.getMaxModTime().equals(groupOracle.getMaxModTime())) {
                    updatedGroups.add(groupMySQL.getGroupName());
                }
            }
            else {
                mysqlGroups.remove(i);
                oracleGroups.remove(i);
                i--;
            }
            */
        }
        if (!updatedGroups.isEmpty()) {
            //contentOfScheduleDAO.deleteAllMySQL();
            //contentOfScheduleDAO.addListMySQL(contentOfScheduleDAO.findAllOracle());
        }
        return updatedGroups;
    }

    private void collectDataPrevCOS(List<ContentOfSchedule> prevDataCOS) {
        /**
         * этот метод копирует все таблицы из oracle в mysql, кроме
         * contentOfSchedule, чтобы узнать какие были изменения
         * соблюден порядок формирования внешних ключей
         * первыми создаются таблицы, где не используются ключи и т.д.
         */

        /** 0 lvl **/
        buildingDAO.addListMySQL(buildingDAO.findAllOracle());
        chairDAO.addListMySQL(chairDAO.findAllOracle());
        disciplineDAO.addListMySQL(disciplineDAO.findAllOracle());
        streamDAO.addListMySQL(streamDAO.findAllOracle());
        typeOfAuditoriumDAO.addListMySQL(typeOfAuditoriumDAO.findAllOracle());
        lecturerDAO.addListMySQL(lecturerDAO.findAllOracle());
        kindOfWorkDAO.addListMySQL(kindOfWorkDAO.findAllOracle());

        /** 1 lvl **/
        groupDAO.addListMySQL(groupDAO.findAllOracle());
        subGroupDAO.addListMySQL(subGroupDAO.findAllOracle());
        auditoriumDAO.addListMySQL(auditoriumDAO.findAllOracle());

        /** 2 lvl **/
        contentOfScheduleDAO.addListMySQL(prevDataCOS);
    }

    public void updateAllExceptSchedule() {
        /**
         * перепишу этот метод потом
         *
         * есть идея распихать не в лист, а в мапу
         * например: Map<Integer, Auditorium>
         *     тут Integer это id аудитории
         *     таким образом можно сравнивать по индексам
         *     потом просто обновлять данные выборочно,
         *     там где есть изменения, буду делать update
         */
        /*
        List<Auditorium> auditorium = new ArrayList<>();
        List<Building> building = new ArrayList<>();
        List<Chair> chairs = new ArrayList<>();
        List<ContentOfSchedule> contentOfSchedule = new ArrayList<>();
        List<Discipline> discipline = new ArrayList<>();
        List<Group> group = new ArrayList<>();
        List<KindOfWork> kindOfWork = new ArrayList<>();
        List<Lecturer> lecturer = new ArrayList<>();
        List<Stream> stream = new ArrayList<>();
        List<SubGroup> subGroup = new ArrayList<>();
        List<TypeOfAuditorium> typeOfAuditorium = new ArrayList<>();
        */
        collectDataPrevCOS(deleteAllMySQL());
    }

    private List<ContentOfSchedule> deleteAllMySQL() {
        /**
         * удаляет все данные из таблиц в правильном порядке
         * возвращает предыдущие записи
         */

        /** 2 lvl **/
        List<ContentOfSchedule> dataContentOfSchedule = contentOfScheduleDAO.findAllMySQL();
        contentOfScheduleDAO.deleteAllMySQL();

        /** 1 lvl **/
        auditoriumDAO.deleteAllMySQL();
        subGroupDAO.deleteAllMySQL();
        groupDAO.deleteAllMySQL();

        /** 0 lvl **/
        kindOfWorkDAO.deleteAllMySQL();
        lecturerDAO.deleteAllMySQL();
        typeOfAuditoriumDAO.deleteAllMySQL();
        streamDAO.deleteAllMySQL();
        disciplineDAO.deleteAllMySQL();
        chairDAO.deleteAllMySQL();
        buildingDAO.deleteAllMySQL();
        return dataContentOfSchedule;
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

    public ConnectedUsersDAO getConnectedUsersDAO() { return connectedUsersDAO;}
}

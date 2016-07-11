package CRUD;

import CRUD.tables.Table;
import CRUD.tables.custom.ConnectedUsers;
import CRUD.tables.custom.CustomContentOfSchedule;
import CRUD.tables.custom.GroupMaxModTime;
import CRUD.tables.standard.*;
import CRUD.DAO.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Артем on 04.05.2016.
 */
public class MainTemplateJDBC {
    /**
     * tables to collect data
     */

    private ContentTableOfLessonDAO contentTableOfLessonDAO;
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
        contentTableOfLessonDAO = new ContentTableOfLessonDAO(jdbcTemplateObjectMySQL, jdbcTemplateObjectOracle);
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

        contentTableOfLessonDAO.addListMySQL(contentTableOfLessonDAO.findAllOracle());
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
        List<GroupMaxModTime> mysqlGroups = groupDAO.findAllGroupsMMTMySQL();
        Map<String, Timestamp> mapMysql = groupDAO.findAllGroupsMMTMySQL().stream().collect(
                Collectors.toMap(k -> k.getGroupName(), v -> v.getMaxModTime()));
        groupDAO.findAllSubGroupsMMTMySQL().stream().collect(
                Collectors.toMap(kk -> kk.getGroupName(), vv -> vv.getMaxModTime()))
                .forEach((k, v) -> {
                    if (mapMysql.containsKey(k)) {
                        if (mapMysql.get(k).compareTo(v) == -1)
                            mapMysql.put(k, v);
                    }
                    else mapMysql.put(k, v);
                });
        groupDAO.findAllStreamsMMTMySQL().stream()
                .forEach((k) -> {
                    if (mapMysql.containsKey(k.getGroupName())) {
                        if (mapMysql.get(k.getGroupName()).compareTo(k.getMaxModTime()) == -1)
                            mapMysql.put(k.getGroupName(), k.getMaxModTime());
                    }
                    else mapMysql.put(k.getGroupName(), k.getMaxModTime());
                });

        Map<String, Timestamp> mapOracle = groupDAO.findAllGroupsMMTOracle().stream().collect(
                Collectors.toMap(k -> k.getGroupName(), v -> v.getMaxModTime()));
        groupDAO.findAllSubGroupsMMTOracle().stream().collect(
                Collectors.toMap(kk -> kk.getGroupName(), vv -> vv.getMaxModTime()))
                .forEach((k, v) -> {
                    if (mapOracle.containsKey(k)) {
                        if (mapOracle.get(k).compareTo(v) == -1)
                            mapOracle.put(k, v);
                    }
                    else mapOracle.put(k, v);
                });
        groupDAO.findAllStreamsMMTOracle().stream()
                .forEach((k) -> {
                    if (mapOracle.containsKey(k.getGroupName())) {
                        if (mapOracle.get(k.getGroupName()).compareTo(k.getMaxModTime()) == -1)
                            mapOracle.put(k.getGroupName(), k.getMaxModTime());
                    }
                    else mapOracle.put(k.getGroupName(), k.getMaxModTime());
                });

        mapOracle.forEach((group, MMTOracle) -> {
            Timestamp MMTMysql = mapMysql.get(group);
            if (MMTMysql == null || !MMTMysql.equals(MMTOracle)) {
                updatedGroups.add(group);
            }
        });
        fullDatabaseUpdate();
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
        contentTableOfLessonDAO.addListMySQL(contentTableOfLessonDAO.findAllOracle());
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
        contentTableOfLessonDAO.deleteAllMySQL();
        return dataContentOfSchedule;
    }

    public  void fullDatabaseUpdate() {
        AbstractMap.SimpleEntry insertDelete;
        /**
         * 2 lvl
         */
        insertDelete = equalLists(contentOfScheduleDAO.findAllMySQL(), contentOfScheduleDAO.findAllOracle());
        contentOfScheduleDAO.deleteRowsMySQL((List<ContentOfSchedule>)insertDelete.getValue());
        List<ContentOfSchedule> toInsertContentOfSchedule = (List<ContentOfSchedule>)insertDelete.getKey();
        /**
         * 1 lvl
         */
        insertDelete = equalLists(auditoriumDAO.findAllMySQL(), auditoriumDAO.findAllOracle());
        auditoriumDAO.deleteRowsMySQL((List<Auditorium>)insertDelete.getValue());
        List<Auditorium> toInsertAuditorium = (List<Auditorium>)insertDelete.getKey();

        insertDelete = equalLists(subGroupDAO.findAllMySQL(), subGroupDAO.findAllOracle());
        subGroupDAO.deleteRowsMySQL((List<SubGroup>)insertDelete.getValue());
        List<SubGroup> toInsertSubGroup = (List<SubGroup>)insertDelete.getKey();

        insertDelete = equalLists(groupDAO.findAllMySQL(), groupDAO.findAllOracle());
        groupDAO.deleteRowsMySQL((List<Group>)insertDelete.getValue());
        List<Group> toInsertGroup = (List<Group>)insertDelete.getKey();
        /**
         * 0 lvl
         */
        insertDelete = equalLists(kindOfWorkDAO.findAllMySQL(), kindOfWorkDAO.findAllOracle());
        kindOfWorkDAO.deleteRowsMySQL((List<KindOfWork>)insertDelete.getValue());
        List<KindOfWork> toInsertKindOfWork = (List<KindOfWork>)insertDelete.getKey();

        insertDelete = equalLists(lecturerDAO.findAllMySQL(), lecturerDAO.findAllOracle());
        lecturerDAO.deleteRowsMySQL((List<Lecturer>)insertDelete.getValue());
        List<Lecturer> toInsertLecturer = (List<Lecturer>)insertDelete.getKey();

        insertDelete = equalLists(typeOfAuditoriumDAO.findAllMySQL(), typeOfAuditoriumDAO.findAllOracle());
        typeOfAuditoriumDAO.deleteRowsMySQL((List<TypeOfAuditorium>)insertDelete.getValue());
        List<TypeOfAuditorium> toInsertTypeOfAuditorium = (List<TypeOfAuditorium>)insertDelete.getKey();

        insertDelete = equalLists(streamDAO.findAllMySQL(), streamDAO.findAllOracle());
        streamDAO.deleteRowsMySQL((List<Stream>)insertDelete.getValue());
        List<Stream> toInsertStream = (List<Stream>)insertDelete.getKey();

        insertDelete = equalLists(disciplineDAO.findAllMySQL(), disciplineDAO.findAllOracle());
        disciplineDAO.deleteRowsMySQL((List<Discipline>)insertDelete.getValue());
        List<Discipline> toInsertDiscipline = (List<Discipline>)insertDelete.getKey();

        insertDelete = equalLists(chairDAO.findAllMySQL(), chairDAO.findAllOracle());
        chairDAO.deleteRowsMySQL((List<Chair>)insertDelete.getValue());
        List<Chair> toInsertChair = (List<Chair>)insertDelete.getKey();

        insertDelete = equalLists(buildingDAO.findAllMySQL(), buildingDAO.findAllOracle());
        buildingDAO.deleteRowsMySQL((List<Building>)insertDelete.getValue());
        List<Building> toInsertBuilding = (List<Building>)insertDelete.getKey();

        insertDelete = equalLists(contentTableOfLessonDAO.findAllMySQL(), contentTableOfLessonDAO.findAllOracle());
        contentTableOfLessonDAO.deleteRowsMySQL((List<ContentTableOfLesson>)insertDelete.getValue());
        List<ContentTableOfLesson> toInsertContentTableOfLesson = (List<ContentTableOfLesson>)insertDelete.getKey();

        /**
         * now insert this
         */

        contentTableOfLessonDAO.addListMySQL(toInsertContentTableOfLesson);
        buildingDAO.addListMySQL(toInsertBuilding);
        chairDAO.addListMySQL(toInsertChair);
        disciplineDAO.addListMySQL(toInsertDiscipline);
        streamDAO.addListMySQL(toInsertStream);
        typeOfAuditoriumDAO.addListMySQL(toInsertTypeOfAuditorium);
        lecturerDAO.addListMySQL(toInsertLecturer);
        kindOfWorkDAO.addListMySQL(toInsertKindOfWork);
        groupDAO.addListMySQL(toInsertGroup);
        subGroupDAO.addListMySQL(toInsertSubGroup);
        auditoriumDAO.addListMySQL(toInsertAuditorium);
        contentOfScheduleDAO.addListMySQL(toInsertContentOfSchedule);

    }

    public void simpleDeleteAllMySQL() {
        /**
         * удаляет все данные из таблиц в правильном порядке
         */

        /** 2 lvl **/
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
        contentTableOfLessonDAO.deleteAllMySQL();
    }

    private Map<Integer, String> getMaxTimeSchedGroupsChangeMysql() {
        return groupDAO.findAllGroupOIDMySQL().stream().collect(
                Collectors.toMap(
                        g -> g,
                        t -> contentOfScheduleDAO.getMaxModifiedTimeByGroupMySQL(t) == null ?
                            "null":
                            contentOfScheduleDAO.getMaxModifiedTimeByGroupMySQL(t).toString()
                ));
    }

    private Map<Integer, String> getMaxTimeSchedGroupsChangeOracle() {
        return groupDAO.findAllGroupOIDOracle().stream().collect(
                Collectors.toMap(
                        g -> g,
                        t -> contentOfScheduleDAO.getMaxModifiedTimeByGroupOracle(t) == null ?
                                "null":
                                contentOfScheduleDAO.getMaxModifiedTimeByGroupOracle(t).toString()
                ));
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

    public ContentTableOfLessonDAO getContentTableOfLessonDAO() {return  contentTableOfLessonDAO;}

    public ConnectedUsersDAO getConnectedUsersDAO() { return connectedUsersDAO;}

    public JdbcTemplate getJdbcTemplateObjectMySQL() {return jdbcTemplateObjectMySQL;}

    public JdbcTemplate getJdbcTemplateObjectOracle() {return jdbcTemplateObjectOracle;}

    /**
     * method search redundant and not equal records in mysql list using oracle list as reference
     * @param mysql List(T) from mysql
     * @param oracle List(T) from oracle
     * @param <T> Comparable Table to update
     * @return SimpleEntry Key(T objects to insert) Value(T objects to delete)
     */
    public <T extends Table & Comparable<? super T>> AbstractMap.SimpleEntry<List<T>, List<T>> equalLists
            (List<T> mysql,
             List<T> oracle)
    {
        if (mysql == null && oracle == null) {
            return new AbstractMap.SimpleEntry<List<T>, List<T>>(new ArrayList<>(), new ArrayList<>());
        }
        if (mysql == null)
            return new AbstractMap.SimpleEntry<List<T>, List<T>>(oracle, new ArrayList<>());
        if (oracle == null)
            return new AbstractMap.SimpleEntry<List<T>, List<T>>(new ArrayList<>(), mysql);

        Collections.sort(mysql);
        Collections.sort(oracle);

        if (mysql.equals(oracle)) return new AbstractMap.SimpleEntry<List<T>, List<T>>(new ArrayList<>(), new ArrayList<>());;
        List<T> list = new ArrayList<>();
        oracle.forEach(r -> {//if row from oracle not exist in mysql then this row needed to be deleted and inserted
            if (!mysql.contains(r)) {
                list.add(r);
            } else mysql.remove(r);
        });
        /* Для тестирования
        list.forEach(r -> {
            System.out.println(r);
        });
        */
        return new AbstractMap.SimpleEntry<List<T>, List<T>>(list, mysql);
    }
}

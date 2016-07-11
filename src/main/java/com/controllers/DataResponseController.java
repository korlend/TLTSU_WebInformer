package com.controllers;

/**
 * Created by Артем on 30.04.2016.
 */

import CRUD.MainTemplateJDBC;
import CRUD.tables.custom.ConnectedUsers;
import CRUD.tables.custom.CustomContentOfSchedule;
import CRUD.tables.standard.ContentOfSchedule;
import com.response.classes.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonSimpleJsonParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.json.SimpleJsonParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/data")
public class DataResponseController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    private ApplicationContext context = new ClassPathXmlApplicationContext("DatabaseBeans.xml");
    MainTemplateJDBC mainTemplateJDBC = (MainTemplateJDBC)context.getBean("mainTemplateJDBC");

    @RequestMapping("/copy")
    public void dataCopy() {
        //mainTemplateJDBC.unknownFunction();
        //mainTemplateJDBC.simpleCollectData();
        //mainTemplateJDBC.updateDatabase();
    }

    @RequestMapping("/dropmysql")
    public void dataDropMySQL() {
        mainTemplateJDBC.simpleDeleteAllMySQL();
    }

    @RequestMapping("/test")
    public void test() {
        /*
        JdbcTemplate jdbcTemplateObjectOracle = mainTemplateJDBC.getJdbcTemplateObjectOracle();
        JdbcTemplate jdbcTemplatemysql = mainTemplateJDBC.getJdbcTemplateObjectMySQL();
        String sqlSelect = "select \"OID\", \"ContentTableOfLesson\" from \"ContentOfSchedule\" where \"ContentTableOfLesson\" is not NULL";
        List<AbstractMap.SimpleEntry<Integer, Integer>> list = jdbcTemplateObjectOracle.query(sqlSelect, new RowMapper<AbstractMap.SimpleEntry<Integer, Integer>>() {
            @Override
            public AbstractMap.SimpleEntry<Integer, Integer> mapRow(ResultSet resultSet, int i) throws SQLException {
                AbstractMap.SimpleEntry<Integer, Integer> simpleEntry = new AbstractMap.SimpleEntry<>(
                        resultSet.getInt("OID"),
                        resultSet.getInt("ContentTableOfLesson")
                );
                return simpleEntry;
            }
        });
        list.stream().forEach(entry -> {
            if (entry == null) {
                System.out.println("entry null");

            }
            else {
                if (entry.getValue() == null) {
                    System.out.println("entry value null " + entry.getKey());
                } else
                jdbcTemplatemysql.update("UPDATE `ContentOfSchedule` " +
                                "set ContentTableOfLesson = ? " +
                                "where OID = ?",
                        entry.getValue(),
                        entry.getKey());
            }
        });
        */
    }

    @RequestMapping("/update")
    public void dataUpdate() {
        List<ConnectedUsers> connectedUsersList = mainTemplateJDBC.getConnectedUsersDAO().findAllMySQL();
        mainTemplateJDBC.updateDatabase().forEach((group) -> {
            List<CustomContentOfSchedule> schedule = mainTemplateJDBC.findAllMySQL(group);
            simpMessagingTemplate.convertAndSend("/topic/" + group, new Message(schedule));
            connectedUsersList.forEach(r -> {
                if (r.getPreferred_groups().contains(group))
                    simpMessagingTemplate.convertAndSend("/topic/" + r.getDevice_id(),
                            new Message(schedule));
            });
        });
    }

    @RequestMapping("/GetAllGroupsOracle")
    public List<String> GetAllGroupsOracle() {
        return mainTemplateJDBC.getGroupDAO().findAllGroupNamesOracle();
    }

    @RequestMapping("/GetAllGroupsMySQL")
    public List<String> GetAllGroupsMySQL() {
        return mainTemplateJDBC.getGroupDAO().findAllGroupNamesMySQL();
    }

    @RequestMapping("/SelectOracle")
    public List<ContentOfSchedule> dataSelectOracle() {
        //return mainTemplateJDBC.findAllOracle();
        return new ArrayList<>();
    }

    @RequestMapping("/get/mysql/schedule/{groupName}/{from}/{to}")
    public List<CustomContentOfSchedule> GetMySQLScheduleByGroupDate(
            @PathVariable(value = "groupName") String group,
            @PathVariable(value = "from") String fromDate,
            @PathVariable(value = "to") String toDate
    ) {
        Calendar fromCalendarDate = Calendar.getInstance();
        Calendar toCalendarDate = Calendar.getInstance();
        try {
            fromCalendarDate.setTime((new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)).parse(fromDate));
            toCalendarDate.setTime((new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)).parse(toDate));
            return mainTemplateJDBC.findAllMySQL(
                    group,
                    Timestamp.from(fromCalendarDate.toInstant()).toString(),
                    Timestamp.from(toCalendarDate.toInstant()).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @RequestMapping("/get/mysql/schedule/{groupName}/{day}")
    public List<CustomContentOfSchedule> GetMySQLScheduleByGroupDay(
            @PathVariable(value = "groupName") String group,
            @PathVariable(value = "day") String day){
        Calendar referredDate = Calendar.getInstance();
        Calendar incrementedDate = Calendar.getInstance();
        try {
            referredDate.setTime((new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)).parse(day));
            incrementedDate.setTime((new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)).parse(day));
            incrementedDate.add(Calendar.DATE, 1);
            return mainTemplateJDBC.findAllMySQL(
                    group,
                    Timestamp.from(referredDate.toInstant()).toString(),
                    Timestamp.from(incrementedDate.toInstant()).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
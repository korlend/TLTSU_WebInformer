package com.controllers;

/**
 * Created by Артем on 30.04.2016.
 */

import CRUD.MainTemplateJDBC;
import CRUD.tables.custom.CustomContentOfSchedule;
import CRUD.tables.standard.ContentOfSchedule;
import org.springframework.boot.json.JsonSimpleJsonParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.json.SimpleJsonParser;

import java.util.List;

@RestController
@RequestMapping("/Data")
public class DataResponseController {
    /**если добавить method=GET или POST то они странно работают, если нужен будет POST то создавай новый контроллер **/
    /**if you need to add method=GET or POST, create new Controller for each RequestMapping **/

    private ApplicationContext context = new ClassPathXmlApplicationContext("DatabaseBeans.xml");
    MainTemplateJDBC mainTemplateJDBC = (MainTemplateJDBC)context.getBean("mainTemplateJDBC");

    @RequestMapping("/Copy")
    public void dataCopy() {
        //mainTemplateJDBC.unknownFunction();
        //mainTemplateJDBC.simpleCollectData();
        //mainTemplateJDBC.updateDatabase();
    }

    @RequestMapping("/Test")
    public void createConnectedUser() {
        //mainTemplateJDBC.unknownFunction();
        //mainTemplateJDBC.simpleCollectData();
        mainTemplateJDBC.updateDatabase();
    }

    @RequestMapping("/GetAllGroupsOracle")
    public List<String> GetAllGroupsOracle() {
        return mainTemplateJDBC.getGroupDAO().findAllGroupsOracle();
    }

    @RequestMapping("/GetAllGroupsMySQL")
    public List<String> GetAllGroupsMySQL() {
        return mainTemplateJDBC.getGroupDAO().findAllGroupsMySQL();
    }

    @RequestMapping("/SelectOracle")
    public List<ContentOfSchedule> dataSelectOracle() {
        return mainTemplateJDBC.findAllOracle();
    }

    @RequestMapping("/SelectMySQL")
    public List<CustomContentOfSchedule> dataSelectMySQL(@RequestParam(value="group", defaultValue="") String group,
                                           @RequestParam(value="starton", defaultValue="") String starton,
                                           @RequestParam(value="endon", defaultValue="") String endon) {
        if (!group.isEmpty()) {
            if (!starton.isEmpty() && !endon.isEmpty()) {
                return mainTemplateJDBC.findAllMySQL(group, starton, endon);
            }
            return mainTemplateJDBC.findAllMySQL(group);
        }
        return mainTemplateJDBC.findAllMySQL("");

    }
}
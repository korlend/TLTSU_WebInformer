package com.controllers;

/**
 * Created by Артем on 30.04.2016.
 */

import CRUD.MainTemplateJDBC;
import com.response.classes.DataCopy;
import com.response.classes.DataSelectMySQL;
import com.response.classes.DataSelectOracle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Data")
public class DataResponseController {
    /**если добавить method=GET или POST то они странно работают, если нужен будет POST то создавай новый контроллер **/
    /**if you need to add method=GET or POST, create new Controller for each RequestMapping **/

    private ApplicationContext context = new ClassPathXmlApplicationContext("DatabaseBeans.xml");
    MainTemplateJDBC mainTemplateJDBC = (MainTemplateJDBC)context.getBean("mainTemplateJDBC");

    @RequestMapping("/Copy")
    public DataCopy dataCopy() {
        return new DataCopy(mainTemplateJDBC);
    }

    @RequestMapping("/SelectOracle")
    public DataSelectOracle dataSelectOracle(String data) {
        return new DataSelectOracle(mainTemplateJDBC);
    }

    @RequestMapping("/SelectMySQL")
    public DataSelectMySQL dataSelectMySQL(@RequestParam(value="group", defaultValue="") String group,
                                           @RequestParam(value="starton", defaultValue="") String starton,
                                           @RequestParam(value="endon", defaultValue="") String endon) {
        if (!group.isEmpty()) {
            if (!starton.isEmpty() && !endon.isEmpty()) {
                return new DataSelectMySQL(mainTemplateJDBC, group, starton, endon);
            }
            return new DataSelectMySQL(mainTemplateJDBC, group);
        }
        return new DataSelectMySQL(mainTemplateJDBC);

    }
}
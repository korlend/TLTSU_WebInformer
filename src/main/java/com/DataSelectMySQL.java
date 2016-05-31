package com;

import CRUD.MainTemplateJDBC;
import CRUD.tables.custom.CustomContentOfSchedule;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class DataSelectMySQL {
    public List<CustomContentOfSchedule> result;

    DataSelectMySQL(MainTemplateJDBC mainTemplateJDBC) {
        mainTemplateJDBC.findAllMySQL();
    }

    DataSelectMySQL(MainTemplateJDBC mainTemplateJDBC, String group) {
        //System.out.println(group);
        result = mainTemplateJDBC.findAllMySQL(group);
    }

    DataSelectMySQL(MainTemplateJDBC mainTemplateJDBC, String group, String starton, String endon) {
        //System.out.println(group + "\n" + starton + "\n" +  endon);
        result = mainTemplateJDBC.findAllMySQL(group, starton, endon);
    }

    public List<CustomContentOfSchedule> getResult() {
        return result;
    }
}

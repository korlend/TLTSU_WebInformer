package com.response.classes;

import CRUD.MainTemplateJDBC;
import CRUD.tables.custom.CustomContentOfSchedule;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class DataSelectMySQL {
    public List<CustomContentOfSchedule> result;

    public DataSelectMySQL(MainTemplateJDBC mainTemplateJDBC) {
        mainTemplateJDBC.findAllMySQL();
    }

    public DataSelectMySQL(MainTemplateJDBC mainTemplateJDBC, String group) {
        result = mainTemplateJDBC.findAllMySQL(group);
    }

    public DataSelectMySQL(MainTemplateJDBC mainTemplateJDBC, String group, String starton, String endon) {
        result = mainTemplateJDBC.findAllMySQL(group, starton, endon);
    }

    public List<CustomContentOfSchedule> getResult() {
        return result;
    }
}

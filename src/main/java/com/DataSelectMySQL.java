package com;

import CRUD.MainTemplateJDBC;

/**
 * Created by Артем on 22.05.2016.
 */
public class DataSelectMySQL {
    public Boolean result;

    DataSelectMySQL(MainTemplateJDBC mainTemplateJDBC) {
        //mainTemplateJDBC.unknownFunction();
        mainTemplateJDBC.findAllMySQL();
        result = true;
    }

    public Boolean getResult() {
        return result;
    }
}

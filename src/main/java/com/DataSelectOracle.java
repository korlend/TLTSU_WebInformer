package com;

import CRUD.MainTemplateJDBC;
import CRUD.tables.standard.ContentOfSchedule;

import java.util.List;

/**
 * Created by Артем on 22.05.2016.
 */
public class DataSelectOracle {
    public List<ContentOfSchedule> result;

    DataSelectOracle(MainTemplateJDBC mainTemplateJDBC) {
        //mainTemplateJDBC.unknownFunction();
        //mainTemplateJDBC.findAllOracle();

        result = mainTemplateJDBC.ret();
    }

    public List<ContentOfSchedule> getResult() {
        return result;
    }
}

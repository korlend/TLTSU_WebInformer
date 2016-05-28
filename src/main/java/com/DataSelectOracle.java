package com;

import CRUD.MainTemplateJDBC;

/**
 * Created by Артем on 22.05.2016.
 */
public class DataSelectOracle {
    public Boolean result;

    DataSelectOracle(MainTemplateJDBC mainTemplateJDBC) {
        //mainTemplateJDBC.unknownFunction();
        mainTemplateJDBC.findAllOracle();
        result = true;
    }

    public Boolean getResult() {
        return result;
    }
}

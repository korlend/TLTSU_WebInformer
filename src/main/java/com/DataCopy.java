package com;

import CRUD.MainTemplateJDBC;

/**
 * Created by Артем on 22.05.2016.
 */
public class DataCopy {
    public Boolean result;

    DataCopy(MainTemplateJDBC mainTemplateJDBC) {
        //mainTemplateJDBC.unknownFunction();
        mainTemplateJDBC.collectData();
        result = true;
    }

    public Boolean getResult() {
        return result;
    }
}

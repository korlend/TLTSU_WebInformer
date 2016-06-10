package com.response.classes;

import CRUD.MainTemplateJDBC;

/**
 * Created by Артем on 22.05.2016.
 */
public class DataCopy {
    public Boolean result;

    public DataCopy(MainTemplateJDBC mainTemplateJDBC) {
        //mainTemplateJDBC.unknownFunction();
        //mainTemplateJDBC.simpleCollectData();
        result = true;
    }

    public Boolean getResult() {
        return result;
    }
}

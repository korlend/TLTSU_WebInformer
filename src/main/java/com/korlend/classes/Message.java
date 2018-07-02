package com.korlend.classes;

import com.korlend.CRUD.tables.custom.CustomContentOfSchedule;

import java.util.List;

/**
 * Created by ����� on 22.04.2016.
 */
public class Message {

    private List<CustomContentOfSchedule> schedule;

    public Message(List<CustomContentOfSchedule> schedule) {
        this.schedule = schedule;
    }

    public List<CustomContentOfSchedule> getContent() {
        return schedule;
    }

}

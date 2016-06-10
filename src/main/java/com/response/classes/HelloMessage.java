package com.response.classes;

public class HelloMessage {

    private String name;
    private String id;

    public void setName(String name) {
        System.out.println(name + " - я вывель");
        this.name = name;
    }

    public void setId(String id) {
        System.out.println(id + " - я вывель");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
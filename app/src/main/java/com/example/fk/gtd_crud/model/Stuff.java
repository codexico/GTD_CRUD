package com.example.fk.gtd_crud.model;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Stuff extends SugarRecord implements Serializable {
    public Boolean done = false;

    public String title;

    public String description;
    public String contact;
    public String context;
    public String location;

    public Stuff() {

    }

    public Stuff(String title) {
        this.title = title;
    }

    public Stuff(String title, String description, String contact, String context, String location) {
        this.title = title;
        this.description = description;
        this.contact = contact;
        this.context = context;
        this.location = location;
    }
}



package com.example.fk.gtd_crud.model;

import java.io.Serializable;

public class Stuff implements Serializable {
    private int id;
    private int isDone = 0;
    private String title;
    private String description;
    private String contact;
    private String context;
    private String location;


    public boolean isDone() {
        return (isDone == 1);
    }

    ///////////////////
    // getters and setters
    ///////////////////
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}



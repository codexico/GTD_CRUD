package com.example.fk.gtd_crud.model;

public class StuffBuilder {
    private String title;

    private String description = "";
    private String contact = "";
    private String context = "";
    private String location = "";

    public StuffBuilder setTitle(String s) {
        this.title = s;
        return this;
    }

    public StuffBuilder setDescription(String s) {
        this.description = s;
        return this;
    }

    public StuffBuilder setContact(String s) {
        this.contact = s;
        return this;
    }

    public StuffBuilder setContext(String s) {
        this.context = s;
        return this;
    }

    public StuffBuilder setLocation(String s) {
        this.location = s;
        return this;
    }

    public Stuff build() {
        return new Stuff(title, description, contact, context, location);
    }
}

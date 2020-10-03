package com.urise.webapp.model;

import sun.plugin.javascript.navig.Link;

public class Experience {

    private String date;
    private Link organizationLink;
    private String position;
    private String duties;

    public Experience(String date, Link organizationLink, String position, String duties) {
        this.date = date;
        this.organizationLink = organizationLink;
        this.position = position;
        this.duties = duties;
    }
}

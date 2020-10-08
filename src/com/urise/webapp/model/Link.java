package com.urise.webapp.model;

public class Link {

    private String name;
    private String url;

    public Link(String contactName, String contactLink) {
        this.name = contactName;
        this.url = contactLink;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
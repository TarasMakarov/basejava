package com.urise.webapp.model;

public class LinkInResume {

    private String contactName;
    private String contactLink;

    public LinkInResume(String contactName, String contactLink) {
        this.contactName = contactName;
        this.contactLink = contactLink;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactLink() {
        return contactLink;
    }

    public void setContactLink(String contactLink) {
        this.contactLink = contactLink;
    }
}
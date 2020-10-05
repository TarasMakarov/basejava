package com.urise.webapp.model;

import sun.plugin.javascript.navig.Link;

public class Contact {

    private String textContact;
    private Link link;

    public Contact(String textContact) {
        this.textContact = textContact;
    }

    public Contact(Link link) {
        this.link = link;
    }

    public Contact(String textContact, Link link) {
        this.textContact = textContact;
        this.link = link;
    }
}

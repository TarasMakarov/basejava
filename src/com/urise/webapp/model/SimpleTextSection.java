package com.urise.webapp.model;

public class SimpleTextSection extends AbstractSection {

    private String text;

    public SimpleTextSection(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
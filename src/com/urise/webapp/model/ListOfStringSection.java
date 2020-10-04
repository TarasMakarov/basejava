package com.urise.webapp.model;

import java.util.List;

public class ListOfStringSection extends AbstractSection {

    private List<String> listText;

    public ListOfStringSection(List<String> listText) {
        this.listText = listText;
    }

    public List<String> getListText() {
        return listText;
    }
}
package com.urise.webapp.model;

import java.util.List;

public class BulletListSection extends AbstractSection {

    private List<String> listText;

    public BulletListSection(List<String> listText) {
        this.listText = listText;
    }

    public void setListText(List<String> listText) {
        this.listText = listText;
    }

    public List<String> getListText() {
        return listText;
    }
}
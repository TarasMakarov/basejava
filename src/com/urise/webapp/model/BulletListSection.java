package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BulletListSection extends AbstractSection {

    private final List<String> listText;

    public BulletListSection(List<String> listText) {
        Objects.requireNonNull(listText, "listText must not be null");
        this.listText = listText;
    }

    public BulletListSection(String... strings) {
        this.listText = Arrays.asList(strings);
    }

    public List<String> getListText() {
        return listText;
    }

    @Override
    public String toString() {
        return listText.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BulletListSection that = (BulletListSection) o;

        return listText.equals(that.listText);

    }

    @Override
    public int hashCode() {
        return listText.hashCode();
    }
}
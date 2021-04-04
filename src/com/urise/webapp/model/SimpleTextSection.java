package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleTextSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    public static final SimpleTextSection EMPTY = new SimpleTextSection("");

    private String text;

    public SimpleTextSection() {
    }

    public SimpleTextSection(String text) {
        Objects.requireNonNull(text, "Text must not be null");
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleTextSection that = (SimpleTextSection) o;

        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
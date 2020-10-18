package com.urise.webapp.model;


import java.time.YearMonth;
import java.util.Objects;

public class Experience {
    private final Link organizationLink;

    private final YearMonth start;
    private final YearMonth finish;
    private final String position;
    private final String duties;

    public Experience(String name, String url, YearMonth start, YearMonth finish, String position, String duties) {

        Objects.requireNonNull(start, "start must not be null");
        Objects.requireNonNull(finish, "finish must not be null");
        Objects.requireNonNull(position, "position must not be null");

        this.organizationLink = new Link(name, url);
        this.start = start;
        this.finish = finish;
        this.position = position;
        this.duties = duties;
    }

    @Override
    public String toString() {
        return " " +
                organizationLink + " " +
                start + " " +
                finish + " " +
                position +
                duties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Experience that = (Experience) o;

        if (!organizationLink.equals(that.organizationLink)) return false;
        if (!start.equals(that.start)) return false;
        if (!finish.equals(that.finish)) return false;
        if (!position.equals(that.position)) return false;
        return duties.equals(that.duties);
    }

    @Override
    public int hashCode() {
        int result = organizationLink.hashCode();
        result = 31 * result + start.hashCode();
        result = 31 * result + finish.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + duties.hashCode();
        return result;
    }
}
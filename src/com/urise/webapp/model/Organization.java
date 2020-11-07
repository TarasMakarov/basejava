package com.urise.webapp.model;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {

    private final Link organizationLink;
    private final List<Experience> experience;
//    private List<Position> positions = new ArrayList<>();

    public Organization(Link organizationLink, List<Experience> experience) {
        this.organizationLink = organizationLink;
        this.experience = experience;
    }

    public Organization(String name, String url, Experience... experience) {
        this(new Link(name, url), Arrays.asList(experience));
    }

    public List<Experience> getExperience() {
        return experience;
    }

    public Link getOrganizationLink() {
        return organizationLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!organizationLink.equals(that.organizationLink)) return false;
        return experience.equals(that.experience);
    }

    @Override
    public int hashCode() {
        int result = organizationLink.hashCode();
        result = 31 * result + experience.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return " " +
                organizationLink +
                experience;
    }


    public static class Experience {

        private final YearMonth start;
        private final YearMonth finish;
        private final String position;
        private final String duties;

//        public static final YearMonth NOW = YearMonth.of(3000, 1);
//
//        public Experience(YearMonth start, String position, String duties) {
//            this(start, NOW, position, duties);
//        }

        public Experience(YearMonth start, YearMonth finish, String position, String duties) {
            Objects.requireNonNull(start, "start must not be null");
            Objects.requireNonNull(finish, "finish must not be null");
            Objects.requireNonNull(position, "position must not be null");

            this.start = start;
            this.finish = finish;
            this.position = position;
            this.duties = duties;
        }

        public YearMonth getStart() {
            return start;
        }

        public YearMonth getFinish() {
            return finish;
        }

        public String getPosition() {
            return position;
        }

        public String getDuties() {
            return duties;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Experience that = (Experience) o;

            if (!start.equals(that.start)) return false;
            if (!finish.equals(that.finish)) return false;
            if (!position.equals(that.position)) return false;
            return duties.equals(that.duties);
        }

        @Override
        public int hashCode() {
            int result = start.hashCode();
            result = 31 * result + finish.hashCode();
            result = 31 * result + position.hashCode();
            result = 31 * result + duties.hashCode();
            return result;
        }

        @Override
        public String toString() {
            if (duties != null) {
                return " " + start + " " + finish + " " + position + " " + duties;
            } else {
                return " " + start + " " + finish + " " + position;
            }
        }
    }
}
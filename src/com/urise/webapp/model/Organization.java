package com.urise.webapp.model;

import com.urise.webapp.util.YearMonthAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.urise.webapp.util.YearMonthUtil.NOW;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final Organization EMPTY = new Organization("", "", Experience.EMPTY);

    private Link organizationLink;
    private List<Experience> experience;

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
        return Objects.equals(organizationLink, that.organizationLink) &&
                Objects.equals(experience, that.experience);
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

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Experience implements Serializable {
        public static final Experience EMPTY = new Experience();
        @XmlJavaTypeAdapter(YearMonthAdapter.class)
        private YearMonth start;
        @XmlJavaTypeAdapter(YearMonthAdapter.class)
        private YearMonth finish;
        private String position;
        private String duties;

        public Experience(YearMonth start, String position, String duties) {
            this(start, NOW, position, duties);
        }

        public Experience(YearMonth start, YearMonth finish, String position, String duties) {
            Objects.requireNonNull(start, "start must not be null");
            Objects.requireNonNull(finish, "finish must not be null");
            Objects.requireNonNull(position, "position must not be null");

            this.start = start;
            this.finish = finish;
            this.position = position;
            this.duties = (duties == null) ? "" : duties;
        }

        public Experience() {
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
            return Objects.equals(start, that.start) &&
                    Objects.equals(finish, that.finish) &&
                    Objects.equals(position, that.position) &&
                    Objects.equals(duties, that.duties);
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
package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;

public class Organization {

    private final Link organizationLink;
    private final List<Experience> experience;

    public Organization(Link organizationLink, List<Experience> experience) {
        this.organizationLink = organizationLink;
        this.experience = experience;
    }

    public Organization(String name, String url, Experience... experience) {
        this.experience = Arrays.asList(experience);
        this.organizationLink = new Link(name, url);
    }

    public Organization(Experience... experience) {
        this.experience = Arrays.asList(experience);
        organizationLink = null;
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
        if (organizationLink == null) {
            return "" + experience;
        } else
            return " " +
                    organizationLink +
                    experience;
    }
}
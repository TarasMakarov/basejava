package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {

    private final List<Experience> experience;

    private final Link organizationLink;

    public Organization(List<Experience> experience, Link organizationLink) {
        this.organizationLink = organizationLink;
        Objects.requireNonNull(experience, "Experience must not be null");
        this.experience = experience;
    }

    public List<Experience> getExperience() {
        return experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        return experience.equals(that.experience);
    }

    @Override
    public int hashCode() {
        return experience.hashCode();
    }

    @Override
    public String toString() {
        return experience.toString();
    }
}
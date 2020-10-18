package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection {

    private final List<Experience> experience;

    public OrganizationSection(List<Experience> experience) {
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

        OrganizationSection that = (OrganizationSection) o;

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
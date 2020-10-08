package com.urise.webapp.model;

import java.util.List;

public class OrganizationSection extends AbstractSection {

    private List<Experience> experience;

    public OrganizationSection(List<Experience> experience) {
        this.experience = experience;
    }

    public List<Experience> getExperience() {
        return experience;
    }
}
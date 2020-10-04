package com.urise.webapp.model;

import java.util.List;

public class OrganizationSection extends AbstractSection {

    private List<Experience> textOrganizationSection;

    public OrganizationSection(List<Experience> textOrganizationSection) {
        this.textOrganizationSection = textOrganizationSection;
    }

    public List<Experience> getTextOrganizationSection() {
        return textOrganizationSection;
    }
}
package com.urise.webapp.model;

import java.util.List;

public class OrganizationSection extends AbstractSection{

    private final List<Organization> organizationList;

    public OrganizationSection(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }
}

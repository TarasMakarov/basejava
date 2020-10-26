package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Organization {

    private final Link organizationLink;
    private final Experience experience;

    private final List<Link> links = new ArrayList<>();

    public Organization(Link organizationLink, Experience experience) {
        this.organizationLink = organizationLink;
        this.experience = experience;
    }

    public Organization(String name, String url, Experience experience) {
        this.experience = experience;
        this.organizationLink = new Link(name, url);
        links.add(this.organizationLink);
    }

    public Experience getExperience() {
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
//        for (int i = 0; i < links.size(); i++) {
//            for (int y = 1; y < links.size(); y++) {
//                if (links.get(i).equals(links.get(y))) {
//                    return " " + experience;
//                }
//            }
//        }
        return " " +
                organizationLink +
                experience;
    }
}
package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;

    public static final Resume EMPTY = new Resume();

    static {
        EMPTY.setSection(SectionType.OBJECTIVE, SimpleTextSection.EMPTY);
        EMPTY.setSection(SectionType.PERSONAL, SimpleTextSection.EMPTY);
        EMPTY.setSection(SectionType.ACHIEVEMENT, BulletListSection.EMPTY);
        EMPTY.setSection(SectionType.QUALIFICATIONS, BulletListSection.EMPTY);
        EMPTY.setSection(SectionType.EDUCATION, new OrganizationSection(Organization.EMPTY));
        EMPTY.setSection(SectionType.EXPERIENCE, new OrganizationSection(Organization.EMPTY));
    }
    // Unique identifier
    private String uuid;
    private String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, AbstractSection> section = new EnumMap<>(SectionType.class);

    public Resume() {
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Map<ContactType, String> getContactsMap() {
        return contacts;
    }

    public Map<SectionType, AbstractSection> getSectionMap() {
        return section;
    }

    public String getContacts(ContactType contactType) {
        return contacts.get(contactType);
    }

    public AbstractSection getSection(SectionType sectionType) {
        return section.get(sectionType);
    }

    public void setContacts(ContactType contactType, String contact) {
        contacts.put(contactType, contact);
    }

    public void setSection(SectionType sectionType, AbstractSection abstractSection) {
        section.put(sectionType, abstractSection);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(section, resume.section);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + contacts.hashCode();
        result = 31 * result + section.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return uuid + '\'' + fullName + '\'';
    }

    @Override
    public int compareTo(Resume resume) {
        int result = fullName.compareTo(resume.fullName);
        return result != 0 ? result : uuid.compareTo(resume.fullName);
    }
}
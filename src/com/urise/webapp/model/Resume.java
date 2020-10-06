package com.urise.webapp.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>{

    // Unique identifier
    private final String uuid;
    private final String fullName;
    private final Map<ContactType, Contact> contactsMap = new EnumMap<>(ContactType.class);
    private final Map<SectionType, AbstractSection> sectionMap = new EnumMap<>(SectionType.class);

//    public Resume(String fullName, Map contactsMap, Map sectionMap) {
//        this(UUID.randomUUID().toString(), fullName, contactsMap, sectionMap);
//    }
//
//    public Resume(String uuid, String fullName, Map contactsMap, Map sectionMap) {
//        Objects.requireNonNull(uuid, "uuid must not be null");
//        Objects.requireNonNull(fullName, "fullName must not be null");
//        this.uuid = uuid;
//        this.fullName = fullName;
//    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public void setContactsMap (ContactType contactType, Contact contact) {
        contactsMap.put(contactType, contact);
    }

    public void setSectionMap (SectionType sectionType, AbstractSection abstractSection) {
        sectionMap.put(sectionType, abstractSection);
    }

    public String getUuid() {
        return uuid;
    }

    public Contact getContactsMap(ContactType contactType) {
        return contactsMap.get(contactType);
    }

    public AbstractSection getAbstractSection(SectionType sectionType) {
        return sectionMap.get(sectionType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
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
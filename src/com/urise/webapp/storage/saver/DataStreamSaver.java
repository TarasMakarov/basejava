package com.urise.webapp.storage.saver;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSaver implements Saver {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContactsMap();
            writeWithException(contacts.entrySet(), dos, n -> {
                dos.writeUTF(n.getKey().name());
                dos.writeUTF(n.getValue());
            });
            Map<SectionType, AbstractSection> sections = r.getSectionMap();
            writeWithException(sections.entrySet(), dos, n -> {
                dos.writeUTF(n.getKey().name());
                switch (n.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((SimpleTextSection) n.getValue()).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> stringList = ((BulletListSection) n.getValue()).getListText();
                        writeWithException(stringList, dos, dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> orgList = ((OrganizationSection) n.getValue()).getOrganizationList();
                        writeWithException(orgList, dos, organization -> {
                            dos.writeUTF(organization.getOrganizationLink().getName());
                            dos.writeUTF(organization.getOrganizationLink().getUrl());
                            List<Organization.Experience> expList = organization.getExperience();
                            writeWithException(expList, dos, experience -> {
                                writeDate(dos, experience.getStart());
                                writeDate(dos, experience.getFinish());
                                dos.writeUTF(experience.getPosition());
                                dos.writeUTF(experience.getDuties());
                            });
                        });
                }
            });
        }
    }

    private void writeDate(DataOutputStream dos, YearMonth yearMonth) throws IOException {
        dos.writeInt(yearMonth.getYear());
        dos.writeInt(yearMonth.getMonthValue());
    }

    interface EachWriter<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeWithException(Collection<T> c, DataOutputStream dos, EachWriter<T> t) throws IOException {
        dos.writeInt(c.size());
        for (T part : c) {
            t.write(part);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readWithException(dis, () -> resume.setContacts(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readWithException(dis, () ->
            {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.setSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new SimpleTextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                List<String> stringList = readList(dis, dis::readUTF);
                return new BulletListSection(stringList);
            case EXPERIENCE:
            case EDUCATION:
                List<Organization.Experience> orgList = readList(dis,
                        () -> new Organization.Experience(readDate(dis), readDate(dis), dis.readUTF(), dis.readUTF()));
                Link orgLink = new Link(dis.readUTF(), dis.readUTF());
                Organization organization = new Organization(orgLink, orgList);
                return new OrganizationSection(organization);
        }
        return null;
    }

    private YearMonth readDate(DataInputStream dis) throws IOException {
        int year = dis.readInt();
        int month = dis.readInt();
        return YearMonth.of(year, month);
    }

    interface ListReader<T> {
        T readElement() throws IOException;
    }

    private <T> List<T> readList(DataInputStream dis, ListReader<T> t) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            list.add(t.readElement());
        }
        return list;
    }

    interface EachReader {
        void read() throws IOException;
    }

    private void readWithException(DataInputStream dis, EachReader e) throws IOException {
        int size = dis.readInt();
        while (size > 0) {
            e.read();
            size--;
        }
    }
}
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
//            readWithException(dis, () -> resume.setSection(SectionType.valueOf(dis.readUTF()), new SimpleTextSection(dis.readUTF())));
            int sizeSection = dis.readInt();
            for (int i = 0; i < sizeSection; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.setSection(sectionType, new SimpleTextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        int quantityStringsBullet = dis.readInt();
                        List<String> stringList = new ArrayList<>();
                        for (int y = 0; y < quantityStringsBullet; y++) {
                            stringList.add(dis.readUTF());
                        }
                        resume.setSection(sectionType, new BulletListSection(stringList));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        int quantityStringsOrg = dis.readInt();
                        List<Organization> orgList = new ArrayList<>();
                        Link orgLink;
                        for (int y = 0; y < quantityStringsOrg; y++) {
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            if (url.equals("")) {
                                url = null;
                            }
                            orgLink = new Link(name, url);
                            List<Organization.Experience> expList = new ArrayList<>();
                            int quantityStringsExp = dis.readInt();
                            for (int z = 0; z < quantityStringsExp; z++) {
                                YearMonth start = readDate(dis);
                                YearMonth finish = readDate(dis);
                                String position = dis.readUTF();
                                String duties = dis.readUTF();
                                if (duties.equals("")) {
                                    duties = null;
                                }
                                Organization.Experience experience = new Organization.Experience(start, finish, position, duties);
                                expList.add(experience);
                            }
                            Organization organization = new Organization(orgLink, expList);
                            orgList.add(organization);
                        }
                        resume.setSection(sectionType, new OrganizationSection(orgList));
                }
            }
            return resume;
        }
    }

    private YearMonth readDate(DataInputStream dis) throws IOException {
        int year = dis.readInt();
        int month = dis.readInt();
        return YearMonth.of(year, month);
    }

    interface EachReader<T> {
        void read() throws IOException;
    }

    private <T> void readWithException(DataInputStream dis, EachReader<T> t) throws IOException {
        int size = dis.readInt();
        while (size > 0) {
            t.read();
            size--;
        }
    }
}

//    private List<Organization.Experience> readListExp(DataInputStream dis) throws IOException {
//        List<Organization.Experience> expList = new ArrayList<>();
//        int size = dis.readInt();
//        for (int i = 0; i < size; i++) {
//            YearMonth start = YearMonth.of(dis.readInt(), dis.readInt());
//            YearMonth finish = YearMonth.of(dis.readInt(), dis.readInt());
//            String position = dis.readUTF();
//            String duties = dis.readUTF();
//            if (duties.equals("")) {
//                duties = null;
//            }
//            Organization.Experience experience = new Organization.Experience(start, finish, position, duties);
//            expList.add(experience);
//        }
//        return expList;
//    }

//    private <T> List<T> readList(Class<T> c, DataInputStream dis) throws IOException {
//        int size = dis.readInt();
//        List<T> list = new ArrayList<T>();
//        for (Object o : list) {
//            T t = c.cast(o);
//            list.add(t);
//        }
//        return list;
//    }
//}
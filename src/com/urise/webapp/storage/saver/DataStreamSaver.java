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
//            dos.writeInt(contacts.size());
//            for (Map.Entry<ContactType, String> entry : r.getContactsMap().entrySet()) {
//                dos.writeUTF(entry.getKey().name());
//                dos.writeUTF(entry.getValue());
//            }
//            EachWriter <T>eWriter = x -> {
//                dos.writeUTF(x.getKey().name());
//                dos.writeUTF(x.getValue());
//            };
            writeWithException(contacts.entrySet(), dos, n -> {
                dos.writeUTF(n.getKey().name());
                dos.writeUTF(n.getValue());
            });
            Map<SectionType, AbstractSection> sections = r.getSectionMap();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : r.getSectionMap().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                switch (entry.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((SimpleTextSection) entry.getValue()).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        dos.writeInt(((BulletListSection) entry.getValue()).getListText().size());
                        for (int i = 0; i < ((BulletListSection) entry.getValue()).getListText().size(); i++) {
                            dos.writeUTF(((BulletListSection) entry.getValue()).getListText().get(i));
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        dos.writeInt(((OrganizationSection) entry.getValue()).getOrganizationList().size());
                        for (int i = 0; i < ((OrganizationSection) entry.getValue()).getOrganizationList().size(); i++) {
                            Organization organization = ((OrganizationSection) entry.getValue()).getOrganizationList().get(i);
                            dos.writeUTF(organization.getOrganizationLink().getName());
                            dos.writeUTF(organization.getOrganizationLink().getUrl());
                            dos.writeInt(organization.getExperience().size());
                            for (int y = 0; y < organization.getExperience().size(); y++) {
                                YearMonth start = organization.getExperience().get(y).getStart();
                                dos.writeInt(start.getYear());
                                dos.writeInt(start.getMonthValue());
                                YearMonth finish = organization.getExperience().get(y).getFinish();
                                dos.writeInt(finish.getYear());
                                dos.writeInt(finish.getMonthValue());
                                String position = organization.getExperience().get(y).getPosition();
                                dos.writeUTF(position);
                                String duties = organization.getExperience().get(y).getDuties();
                                dos.writeUTF(duties);
                            }
                        }
                        break;
                }
            }
        }
    }

    //    теперь надо зарефакторить запись всех коллекций (т.е. все for в doWrite) через функц интерфейс
//    посмотри на реализацию forEach


    interface EachWriter<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeWithException(Collection<T> c, DataOutputStream dos, EachWriter<T> t) throws IOException {
        dos.writeInt(c.size());
        for (T part : c) {
            t.write(part);
        }
    }


//    default void forEach(Consumer<? super T> action) {
//        Objects.requireNonNull(action);
//        for (T t : this) {
//            action.accept(t);
//        }
//    }
//надо сделать что-то подобное, т.к. использование готового forEach в нашей ситуации не подходит, нам нужен метод который прокидывает IOException дальше,
// и свой кастомный функциональный интерфейс (как записывать каждый отд элемент коллекции) который тоже прокидывает IOException
//    т.е. должен получится некий метод writeWithExeption (...) throws IOException, который как параметры принимает коллекцию, DataOutputStream и
//    твой функциональный интерфейс

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int sizeContact =
                    dis.readInt();
            for (int i = 0; i < sizeContact; i++) {
                resume.setContacts(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
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
                                int yearStart = dis.readInt();
                                int monthStart = dis.readInt();
                                int yearFinish = dis.readInt();
                                int monthFinish = dis.readInt();
                                YearMonth start = YearMonth.of(yearStart, monthStart);
                                YearMonth finish = YearMonth.of(yearFinish, monthFinish);
                                String position = dis.readUTF();
                                String duties = dis.readUTF();
                                if (duties.equals("")) {
                                    duties = null;
                                }
                                Organization.Experience experience = new Organization.Experience(start, finish, position, duties);
                                expList.add(experience);
                            }
//                            List<Organization.Experience> expList = readList(, dis);
                            Organization organization = new Organization(orgLink, expList);
                            orgList.add(organization);
                        }
                        resume.setSection(sectionType, new OrganizationSection(orgList));
                }
            }
            return resume;
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
}
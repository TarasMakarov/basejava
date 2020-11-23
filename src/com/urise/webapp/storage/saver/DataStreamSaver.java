package com.urise.webapp.storage.saver;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.YearMonth;
import java.util.Map;

public class DataStreamSaver implements Saver {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContactsMap();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : r.getContactsMap().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, AbstractSection> sections = r.getSectionMap();
            dos.writeInt(contacts.size() + sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : r.getSectionMap().entrySet()) {
                dos.writeUTF(entry.getKey().name());
                switch (entry.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((SimpleTextSection) entry.getValue()).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        for (int i = 0; i < ((BulletListSection) entry.getValue()).getListText().size(); i++) {
                            dos.writeUTF(((BulletListSection) entry.getValue()).getListText().get(i));
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        for (int i = 0; i < ((OrganizationSection) entry.getValue()).getOrganizationList().size(); i++) {
                            Organization organization = ((OrganizationSection) entry.getValue()).getOrganizationList().get(i);
                            String name = organization.getOrganizationLink().getName();
                            String url = organization.getOrganizationLink().getUrl();
                            dos.writeUTF(name + url);
                            for (int y = 0; y < organization.getExperience().size(); y++) {
                                YearMonth start = organization.getExperience().get(y).getStart();
                                YearMonth finish = organization.getExperience().get(y).getFinish();
                                String position = organization.getExperience().get(y).getPosition();
                                String duties = organization.getExperience().get(y).getDuties();
                                dos.writeUTF("" + start + finish + position + duties);
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.setContacts(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int size2 = dis.readInt();
            for (int i = 0; i < size2 - size; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());





            }
            return resume;
        }
    }
}
package com.urise.webapp;

import com.urise.webapp.model.*;

import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {

        Resume gKislin = new Resume("Григорий Кислин");

        String phoneKislin = "Тел.:+7(921)855-0482";
        String skypeKislin = "Skype:grigory.kislin";
        String emailKislin = "Почта:gkislin@yandex.ru";
        String linkedinKislin = "Профиль LinkedIn";
        String githubKislin = "Профиль GitHub";
        String stackOverFlowKislin = "Профиль StackOverFlow";
        String homepageKislin = "Домашняя страница";

        LinkInResume skypeK = new LinkInResume("Skype", "grigory.kislin");

        gKislin.setContactsMap(ContactType.PHONE, phoneKislin);
        gKislin.setContactsMap(ContactType.SKYPE, skypeKislin);
        gKislin.setContactsMap(ContactType.EMAIL, emailKislin);
        gKislin.setContactsMap(ContactType.LINKEDIN, linkedinKislin);
        gKislin.setContactsMap(ContactType.GITHUB, githubKislin);
        gKislin.setContactsMap(ContactType.STACKOVERFLOW, stackOverFlowKislin);
        gKislin.setContactsMap(ContactType.HOMEPAGE, homepageKislin);

//        gKislin.setContactsMap(ContactType.SKYPE, skypeK);


        OneStringSection personalString = new OneStringSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям.");
        OneStringSection objectiveString = new OneStringSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");

        List<String> list = new ArrayList<>();

        list.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). "+
                "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");

        ListOfStringSection listOfStringSection = new ListOfStringSection(list);


        gKislin.setSectionMap(SectionType.PERSONAL, personalString);
        gKislin.setSectionMap(SectionType.OBJECTIVE, objectiveString);
        gKislin.setSectionMap(SectionType.ACHIEVEMENT, listOfStringSection);
//        gKislin.setSectionMap(SectionType.QUALIFICATIONS, );
//        gKislin.setSectionMap(SectionType.EXPERIENCE, );
//        gKislin.setSectionMap(SectionType.EDUCATION, );

        for (ContactType type : ContactType.values()) {
            System.out.println(gKislin.getContactsMap(type));
        }

        for (SectionType type : SectionType.values()) {
            System.out.println(gKislin.getSectionMap(type));
        }
    }
}
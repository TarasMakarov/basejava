package com.urise.webapp;

import com.urise.webapp.model.Contact;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.OneStringSection;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {



        Contact phoneKislin = new Contact("+7(921)855-0482)");
        Contact skypeKislin = new Contact("grigory.kislin");

        Resume gKislin = new Resume("Григорий Кислин");

        gKislin.setContactsMap(ContactType.PHONE, phoneKislin);
        gKislin.setContactsMap(ContactType.SKYPE, skypeKislin);

        OneStringSection personalString = new OneStringSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям.");
        OneStringSection objectiveString = new OneStringSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        List<String> list = new ArrayList<>();
        list.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). "+
                "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        List<String> achievementList = list;

    }
}
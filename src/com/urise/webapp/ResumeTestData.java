package com.urise.webapp;

import com.urise.webapp.model.Contact;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;

import java.net.MalformedURLException;

public class ResumeTestData {
    public static void main(String[] args) throws MalformedURLException {



//        Link skype = new Link(132, "grigory.kislin");

        Resume gKislin = new Resume("Григорий Кислин");

        Contact phoneKislin = new Contact("+7(921)855-0482)");
        Contact skypeKislin = new Contact("skype:grigory.kislin");
        Contact emailKislin = new Contact("mailto:gkislin@yandex.ru");
        Contact linkedinKislin = new Contact("https://www.linkedin.com/in/gkislin");
        Contact githubKislin = new Contact("https://github.com/gkislin");
        Contact stackOverFlowKislin = new Contact("https://stackoverflow.com/users/548473");
        Contact homepageKislin = new Contact("http://gkislin.ru/");

        gKislin.setContactsMap(ContactType.PHONE, phoneKislin);
        gKislin.setContactsMap(ContactType.SKYPE, skypeKislin);
        gKislin.setContactsMap(ContactType.EMAIL, emailKislin);
        gKislin.setContactsMap(ContactType.LINKEDIN, linkedinKislin);
        gKislin.setContactsMap(ContactType.GITHUB, githubKislin);
        gKislin.setContactsMap(ContactType.STACKOVERFLOW, stackOverFlowKislin);
        gKislin.setContactsMap(ContactType.HOMEPAGE, homepageKislin);




//        OneStringSection personalString = new OneStringSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям.");
//        OneStringSection objectiveString = new OneStringSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
//        List<String> list = new ArrayList<>();
//        list.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). "+
//                "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
//        List<String> achievementList = list;


    }
}
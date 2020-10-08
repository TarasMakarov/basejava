package com.urise.webapp;

import com.urise.webapp.model.*;

import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {

        Resume gKislin = new Resume("Григорий Кислин");

        String phoneKislin = "+7(921)855-0482";
        String skypeKislin = "grigory.kislin";
        String emailKislin = "gkislin@yandex.ru";
        String linkedinKislin = "https://www.linkedin.com/in/gkislin";
        String githubKislin = "https://github.com/gkislin";
        String stackOverFlowKislin = "https://stackoverflow.com/users/548473";
        String homepageKislin = "http://gkislin.ru/";

        gKislin.setContactsMap(ContactType.PHONE, phoneKislin);
        gKislin.setContactsMap(ContactType.SKYPE, skypeKislin);
        gKislin.setContactsMap(ContactType.EMAIL, emailKislin);
        gKislin.setContactsMap(ContactType.LINKEDIN, linkedinKislin);
        gKislin.setContactsMap(ContactType.GITHUB, githubKislin);
        gKislin.setContactsMap(ContactType.STACKOVERFLOW, stackOverFlowKislin);
        gKislin.setContactsMap(ContactType.HOMEPAGE, homepageKislin);

        OneStringSection personalString = new OneStringSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям.");
        OneStringSection objectiveString = new OneStringSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");

        List<String> achievementList = new ArrayList<>();

        achievementList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). " +
                "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievementList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievementList.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. " +
                "Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " +
                "интеграция CIFS/SMB java сервера.");
        achievementList.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, " +
                "Highstock для алгоритмического трейдинга.");
        achievementList.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). " +
                "Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX " +
                "(Jython/ Django).");
        achievementList.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        ListOfStringSection achievementSection = new ListOfStringSection(achievementList);

        List<String> qualificationsList = new ArrayList<>();

        qualificationsList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationsList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationsList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualificationsList.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualificationsList.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        qualificationsList.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        qualificationsList.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), " +
                "Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualificationsList.add("Python: Django.");
        qualificationsList.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualificationsList.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualificationsList.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, " +
                "HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualificationsList.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        qualificationsList.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualificationsList.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        qualificationsList.add("Родной русский, английский \"upper intermediate\"");

        ListOfStringSection qualificationSection = new ListOfStringSection(qualificationsList);

        LinkInResume javaOnlineProjects = new LinkInResume("Java Online Projects", "http://javaops.ru/");
        LinkInResume wrike = new LinkInResume("Wrike", "https://www.wrike.com/");
        LinkInResume rITCenter = new LinkInResume("RIT Center", null);
        LinkInResume luxoft = new LinkInResume("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/");
        LinkInResume yota = new LinkInResume("Yota", "https://www.yota.ru/");

        gKislin.setSectionMap(SectionType.PERSONAL, personalString);
        gKislin.setSectionMap(SectionType.OBJECTIVE, objectiveString);
        gKislin.setSectionMap(SectionType.ACHIEVEMENT, achievementSection);
        gKislin.setSectionMap(SectionType.QUALIFICATIONS, qualificationSection);
//        gKislin.setSectionMap(SectionType.EXPERIENCE, );
//        gKislin.setSectionMap(SectionType.EDUCATION, );

        for (ContactType type : ContactType.values()) {
            System.out.print(type.getTitle());
            System.out.println(gKislin.getContactsMap(type));
        }


        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle());
            System.out.println(gKislin.getSectionMap(type));
        }

    }
}
package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static java.time.YearMonth.of;

public class ResumeTestData {

    public static Resume fillResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.setContacts(ContactType.PHONE, "212-85-06");
        resume.setContacts(ContactType.EMAIL, "SkyBot@ye.fi");
        resume.setContacts(ContactType.SKYPE, "skype");
        resume.setSection(SectionType.PERSONAL, new SimpleTextSection("Simple clever"));
        resume.setSection(SectionType.OBJECTIVE, new SimpleTextSection("Boss"));
        resume.setSection(SectionType.ACHIEVEMENT, new BulletListSection("Created the steering wheel. ", "Designed nut"));
        resume.setSection(SectionType.QUALIFICATIONS, new BulletListSection("Tester, " + "design engineer"));
        resume.setSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization("AvtoVaz", null,
                new Organization.Experience(of(2000, 1) , of(2005, 6), "assistant engineer", "carry papers"),
                new Organization.Experience(of(2005, 7), "engineer", "make a smart face"))));
        resume.setSection(SectionType.EDUCATION, new OrganizationSection(new Organization("СПбГАСУ", "https://www.spbgasu.ru/",
                new Organization.Experience(of(1995, 9), of(2000, 4), "student", null))));
        return resume;
    }

    public static void main(String[] args) {
        Resume gKislin = new Resume("Григорий Кислин");

        String phoneKislin = "+7(921)855-0482";
        String skypeKislin = "grigory.kislin";
        String emailKislin = "gkislin@yandex.ru";
        String linkedinKislin = "https://www.linkedin.com/in/gkislin";
        String githubKislin = "https://github.com/gkislin";
        String stackOverFlowKislin = "https://stackoverflow.com/users/548473";
        String homepageKislin = "http://gkislin.ru/";

        gKislin.setContacts(ContactType.PHONE, phoneKislin);
        gKislin.setContacts(ContactType.SKYPE, skypeKislin);
        gKislin.setContacts(ContactType.EMAIL, emailKislin);
        gKislin.setContacts(ContactType.LINKEDIN, linkedinKislin);
        gKislin.setContacts(ContactType.GITHUB, githubKislin);
        gKislin.setContacts(ContactType.STACKOVERFLOW, stackOverFlowKislin);
        gKislin.setContacts(ContactType.HOMEPAGE, homepageKislin);

        SimpleTextSection personalString = new SimpleTextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям.");
        SimpleTextSection objectiveString = new SimpleTextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");

        List<String> achievementList = new ArrayList<>();

        achievementList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). " + "\n" +
                "Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievementList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievementList.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. " + "\n" +
                "Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " + "\n" +
                "интеграция CIFS/SMB java сервера.");
        achievementList.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, " + "\n" +
                "Highstock для алгоритмического трейдинга.");
        achievementList.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). " + "\n" +
                "Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX " + "\n" +
                "(Jython/ Django).");
        achievementList.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        BulletListSection achievementSection = new BulletListSection(achievementList);

        List<String> qualificationsList = new ArrayList<>();

        qualificationsList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationsList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationsList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualificationsList.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualificationsList.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        qualificationsList.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        qualificationsList.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), " + "\n" +
                "Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualificationsList.add("Python: Django.");
        qualificationsList.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualificationsList.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualificationsList.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, " + "\n" +
                "HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        qualificationsList.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        qualificationsList.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        qualificationsList.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        qualificationsList.add("Родной русский, английский \"upper intermediate\"");

        BulletListSection qualificationSection = new BulletListSection(qualificationsList);

        Organization.Experience javaOnlineExp = new Organization.Experience(of(2013, 10), YearMonth.now(), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        Organization.Experience wrikeExp = new Organization.Experience(of(2014, 10), of(2016, 1), "Старший разработчик (backend)", "Проектирование и разработка " +
                "онлайн платформы управления " +
                "проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, " +
                "OAuth2, JWT SSO.");
        Organization.Experience ritCenterExp = new Organization.Experience(of(2012, 4), of(2014, 10), "Java архитектор", "Организация процесса разработки " +
                "системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), " +
                "конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, " +
                "BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из " +
                "браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, " +
                "Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        Organization.Experience luxoftExp = new Organization.Experience(of(2010, 12), of(2012, 4), "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, " +
                "Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и " +
                "анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        Organization.Experience yotaExp = new Organization.Experience(of(2008, 6), of(2010, 12), "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела " +
                "\"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга " +
                "фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        Organization.Experience enkataExp = new Organization.Experience(of(2007, 3), of(2008, 6), "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, " +
                "Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        Organization.Experience siemensExp = new Organization.Experience(of(2005, 1), of(2007, 2), "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, " +
                "реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        Organization.Experience alcatelExp = new Organization.Experience(of(1997, 9), of(2005, 1), "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, " +
                "внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");

        Organization javaOnlineOrg = new Organization("Java Online Projects", "http://javaops.ru/", javaOnlineExp);
        Organization wrikeOrg = new Organization("Wrike", "https://www.wrike.com/", wrikeExp);
        Organization ritCenterOrg = new Organization("RIT Center", null, ritCenterExp);
        Organization luxoftOrg = new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/", luxoftExp);
        Organization yotaOrg = new Organization("Yota", "https://www.yota.ru/", yotaExp);
        Organization enkataOrg = new Organization("Enkata", "http://enkata.com/", enkataExp);
        Organization siemensOrg = new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html", siemensExp);
        Organization alcatelOrg = new Organization("Alcatel", "http://www.alcatel.ru/", alcatelExp);

        List<Organization> jobList = new ArrayList<>();

        jobList.add(javaOnlineOrg);
        jobList.add(wrikeOrg);
        jobList.add(ritCenterOrg);
        jobList.add(luxoftOrg);
        jobList.add(yotaOrg);
        jobList.add(enkataOrg);
        jobList.add(siemensOrg);
        jobList.add(alcatelOrg);

        OrganizationSection jobSection = new OrganizationSection(jobList);

        Organization.Experience courseraExp = new Organization.Experience(of(2013, 3), of(2013, 5), "\t\"Functional Programming Principles in Scala\" by Martin Odersky", null);
        Organization.Experience luxoftEduExp = new Organization.Experience(of(2011, 3), of(2011, 4), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                null);
        Organization.Experience siemensEduExp = new Organization.Experience(of(2005, 1), of(2005, 4), "3 месяца обучения мобильным IN сетям (Берлин)", null);
        Organization.Experience alcatelEduExp = new Organization.Experience(of(1997, 9), of(1998, 3), "6 месяцев обучения цифровым телефонным сетям (Москва)", null);
        Organization.Experience graduateExp = new Organization.Experience(of(1993, 9), of(1996, 7), "Аспирантура (программист С, С++)", null);
        Organization.Experience engineerExp = new Organization.Experience(of(1987, 9), of(1993, 7), "\tИнженер (программист Fortran, C)", null);
        Organization.Experience mftiExp = new Organization.Experience(of(1984, 9), of(1987, 6), "\tЗакончил с отличием", null);

        List<Organization.Experience> educationExperience = new ArrayList<>();

        educationExperience.add(graduateExp);
        educationExperience.add(engineerExp);

        Link ifmoLink = new Link("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "http://www.ifmo.ru/");

        Organization courseraOrg = new Organization("Coursera", "https://www.coursera.org/course/progfun", courseraExp);
        Organization luxoftEduOrg = new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366", luxoftEduExp);
        Organization siemensEduOrg = new Organization("Siemens AG", "http://www.siemens.ru/", siemensEduExp);
        Organization alcatelEduOrg = new Organization("Alcatel", "http://www.alcatel.ru/", alcatelEduExp);
        Organization ifmoEduOrg = new Organization(ifmoLink, educationExperience);
        Organization mftiEduOrg = new Organization("Заочная физико-техническая школа при МФТИ", " http://www.school.mipt.ru/", mftiExp);

        List<Organization> educationOrgList = new ArrayList<>();

        educationOrgList.add(courseraOrg);
        educationOrgList.add(luxoftEduOrg);
        educationOrgList.add(siemensEduOrg);
        educationOrgList.add(alcatelEduOrg);
        educationOrgList.add(ifmoEduOrg);
        educationOrgList.add(mftiEduOrg);

        OrganizationSection educationSection = new OrganizationSection(educationOrgList);

        gKislin.setSection(SectionType.PERSONAL, personalString);
        gKislin.setSection(SectionType.OBJECTIVE, objectiveString);
        gKislin.setSection(SectionType.ACHIEVEMENT, achievementSection);
        gKislin.setSection(SectionType.QUALIFICATIONS, qualificationSection);
        gKislin.setSection(SectionType.EXPERIENCE, jobSection);
        gKislin.setSection(SectionType.EDUCATION, educationSection);

        for (ContactType type : ContactType.values()) {
            System.out.print(type.getTitle());
            System.out.println(gKislin.getContacts(type));
        }
        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle());
            switch (type) {
                case PERSONAL:
                    System.out.println(personalString.getText());
                    break;
                case OBJECTIVE:
                    System.out.println(objectiveString.getText());
                    break;
                case ACHIEVEMENT:
                    for (int i = 0; i < achievementList.size(); i++) {
                        System.out.println(achievementSection.getListText().get(i));
                    }
                    break;
                case QUALIFICATIONS:
                    for (String s : qualificationsList) {
                        System.out.println(s);
                    }
                    break;
                case EXPERIENCE:
                    for (Organization value : jobSection.getOrganizationList()) {
                        System.out.println(value);
                    }
                    break;
                default:
                    for (Organization value : educationOrgList) {
                        System.out.println(value);
                    }
            }
        }
    }
}
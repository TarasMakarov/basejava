package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.YearMonth;
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

        BulletListSection achievementSection = new BulletListSection(achievementList);

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

        BulletListSection qualificationSection = new BulletListSection(qualificationsList);

        List<Experience> jobList = new ArrayList<>();

        Link javaOnline = new Link("Java Online Projects", "http://javaops.ru/");
        Link wrike = new Link("Wrike", "https://www.wrike.com/");
        Link ritCenter = new Link("RIT Center", null);
        Link luxoft = new Link("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/");
        Link yota = new Link("Yota", "https://www.yota.ru/");
        Link enkata = new Link("Enkata", "http://enkata.com/");
        Link siemens = new Link("Siemens AG", "https://www.siemens.com/ru/ru/home.html");
        Link alcatel = new Link("Alcatel", "http://www.alcatel.ru/");

        YearMonth javaStart = YearMonth.of(2013, 10);
        YearMonth javaFinish = YearMonth.now();
        YearMonth wrikeStart = YearMonth.of(2014, 10);
        YearMonth wrikeFinish = YearMonth.of(2016, 1);
        YearMonth ritCenterStart = YearMonth.of(2012, 4);
        YearMonth ritCenterFinish = YearMonth.of(2014, 10);
        YearMonth luxoftStart = YearMonth.of(2010, 12);
        YearMonth luxoftFinish = YearMonth.of(2012, 4);
        YearMonth yotatStart = YearMonth.of(2008, 6);
        YearMonth yotaFinish = YearMonth.of(2010, 12);
        YearMonth enkataStart = YearMonth.of(2007, 3);
        YearMonth enkataFinish = YearMonth.of(2008, 6);
        YearMonth siemensStart = YearMonth.of(2005, 1);
        YearMonth siemensFinish = YearMonth.of(2007, 2);
        YearMonth alcatelStart = YearMonth.of(1997, 9);
        YearMonth alcatelFinish = YearMonth.of(2005, 1);

        Experience javaOnlineExp = new Experience(javaStart, javaFinish, javaOnline, "Автор проекта.", "Создание, организация и проведение Java " +
                "онлайн проектов и стажировок.");
        Experience wrikeExp = new Experience(wrikeStart, wrikeFinish, wrike, "Старший разработчик (backend)", "Проектирование и разработка " +
                "онлайн платформы управления " +
                "проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, " +
                "OAuth2, JWT SSO.");
        Experience ritCenterExp = new Experience(ritCenterStart, ritCenterFinish, ritCenter, "Java архитектор", "Организация процесса разработки " +
                "системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), " +
                "конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, " +
                "BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из " +
                "браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, " +
                "Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        Experience luxoftExp = new Experience(luxoftStart, luxoftFinish, luxoft, "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, " +
                "Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и " +
                "анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        Experience yotaExp = new Experience(yotatStart, yotaFinish, yota, "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела " +
                "\"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга " +
                "фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        Experience enkataExp = new Experience(enkataStart, enkataFinish, enkata, "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, " +
                "Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        Experience siemensExp = new Experience(siemensStart, siemensFinish, siemens, "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, " +
                "реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        Experience alcatelExp = new Experience(alcatelStart, alcatelFinish, alcatel, "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, " +
                "внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");

        jobList.add(javaOnlineExp);
        jobList.add(wrikeExp);
        jobList.add(ritCenterExp);
        jobList.add(luxoftExp);
        jobList.add(yotaExp);
        jobList.add(enkataExp);
        jobList.add(siemensExp);
        jobList.add(alcatelExp);

        OrganizationSection jobSection = new OrganizationSection(jobList);

        List<Experience> educationList = new ArrayList<>();

        Link coursera = new Link("Coursera", "https://www.coursera.org/course/progfun");
        Link luxoftEdu = new Link("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366");
        Link siemensEdu = new Link("Siemens AG", "http://www.siemens.ru/");
        Link alcatelEdu = new Link("Alcatel", "http://www.alcatel.ru/");
        Link university = new Link("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "http://www.ifmo.ru/");
        Link mfti = new Link("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/");

        YearMonth courseraStart = YearMonth.of(2013, 3);
        YearMonth courseraFinish = YearMonth.of(2013, 5);
        YearMonth luxoftEduStart = YearMonth.of(2011, 3);
        YearMonth luxoftEduFinish = YearMonth.of(2011, 4);
        YearMonth siemensEduStart = YearMonth.of(2005, 1);
        YearMonth siemensEduFinish = YearMonth.of(2005, 4);
        YearMonth alcatelEduStart = YearMonth.of(1997, 9);
        YearMonth alcatelEduFinish = YearMonth.of(1998, 3);
        YearMonth graduateStart = YearMonth.of(1993, 9);
        YearMonth graduateFinish = YearMonth.of(19, 7);
        YearMonth engineerStart = YearMonth.of(1987, 9);
        YearMonth engineerFinish = YearMonth.of(1993, 7);
        YearMonth mftiStart = YearMonth.of(1984, 9);
        YearMonth mftiFinish = YearMonth.of(1987, 6);

        Experience courseraExp = new Experience(courseraStart, courseraFinish, coursera, "\t\"Functional Programming Principles in Scala\" by Martin Odersky", null);
        Experience luxoftEduExp = new Experience(luxoftEduStart, luxoftEduFinish, luxoftEdu, "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                null);
        Experience siemensEduExp = new Experience(siemensEduStart, siemensEduFinish, siemensEdu, "3 месяца обучения мобильным IN сетям (Берлин)", null);
        Experience alcatelEduExp = new Experience(alcatelEduStart, alcatelEduFinish, alcatelEdu, "6 месяцев обучения цифровым телефонным сетям (Москва)", null);
        Experience graduateExp = new Experience(graduateStart, graduateFinish, university, "Аспирантура (программист С, С++)", null);
        Experience engineerExp = new Experience(engineerStart, engineerFinish, null, "\tИнженер (программист Fortran, C)", null);
        Experience mftiExp = new Experience(mftiStart, mftiFinish, mfti, "\tЗакончил с отличием", null);

        educationList.add(courseraExp);
        educationList.add(luxoftEduExp);
        educationList.add(siemensEduExp);
        educationList.add(alcatelEduExp);
        educationList.add(graduateExp);
        educationList.add(engineerExp);
        educationList.add(mftiExp);

        OrganizationSection educationSection = new OrganizationSection(educationList);

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
                        System.out.println(achievementList.get(i));
                    }
                    break;
                case QUALIFICATIONS:
                    for (int i = 0; i < qualificationsList.size(); i++) {
                        System.out.println(qualificationsList.get(i));
                    }
                    break;
                case EXPERIENCE:
                    for (int i = 0; i < jobList.size(); i++) {
                        System.out.println(jobSection.getExperience());
                    }
                    break;
                default:
                    for (int i = 0; i < educationList.size(); i++) {
                        System.out.println(educationSection.getExperience().get(i));
                    }
            }
        }
    }
}
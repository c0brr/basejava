package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.Month;
import java.util.ArrayList;

public class ResumeTestData {
    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.addContact(ContactType.PHONE_NUMBER, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin/");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin/");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin/");
        resume.addContact(ContactType.HOME_PAGE, "http://gkislin.ru/");

        // Text Sections
        TextSection personal = new TextSection("Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. " +
                "Пурист кода и архитектуры.");
        resume.addSection(SectionType.PERSONAL, personal);

        TextSection objective = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web " +
                "и Enterprise технологиям");
        resume.addSection(SectionType.OBJECTIVE, objective);

        //List Sections
        ListSection achievement = new ListSection(new ArrayList<>());
        achievement.addContent("Организация команды и успешная реализация Java проектов для сторонних заказчиков: " +
                "приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей " +
                "спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin " +
                "проект для комплексных DIY смет");
        achievement.addContent("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", " +
                "\"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). " +
                "Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. " +
                "Более 3500 выпускников.");
        achievement.addContent("Реализация двухфакторной аутентификации для онлайн платформы управления проектами " +
                "Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievement.addContent("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. " +
                "Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: " +
                "Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, " +
                "интеграция CIFS/SMB java сервера.");
        achievement.addContent("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, " +
                "Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievement.addContent("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных " +
                "сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о " +
                "состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и " +
                "мониторинга системы по JMX (Jython/ Django).");
        achievement.addContent("Реализация протоколов по приему платежей всех основных платежных системы России " +
                "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");

        ListSection qualifications = new ListSection(new ArrayList<>());
        qualifications.addContent("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.addContent("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualifications.addContent("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, " +
                "MySQL, SQLite, MS SQL, HSQLDB");
        qualifications.addContent("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        qualifications.addContent("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        qualifications.addContent("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, " +
                "Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, " +
                "ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        qualifications.addContent("Python: Django.");
        qualifications.addContent("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        qualifications.addContent("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        qualifications.addContent("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, " +
                "SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, " +
                "OAuth1, OAuth2, JWT.");
        qualifications.addContent("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
        qualifications.addContent("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, " +
                "Nagios, iReport, OpenCmis, Bonita, pgBouncer");
        qualifications.addContent("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, " +
                "архитектурных шаблонов, UML, функционального программирования");
        qualifications.addContent("Родной русский, английский \"upper intermediate\"");

        //Company Sections
        CompanySection experience = new CompanySection(new ArrayList<>());

        Period period = new Period(DateUtil.of(2013, Month.OCTOBER),
                DateUtil.now(),
                "Автор проекта.",
                "Создание, организация и проведение Java онлайн проектов и стажировок.");
        Company company = new Company(new ArrayList<>(),
                "Java Online Projects",
                "https://www.javaops.ru/");
        company.addPeriod(period);
        experience.addCompany(company);

        period = new Period(DateUtil.of(2014, Month.OCTOBER),
                DateUtil.of(2016, Month.JANUARY),
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, " +
                        "Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, " +
                        "авторизация по OAuth1, OAuth2, JWT SSO.");
        company = new Company(new ArrayList<>(), "Wrike", "https://www.wrike.com/");
        company.addPeriod(period);
        experience.addCompany(company);

        period = new Period(DateUtil.of(2012, Month.APRIL),
                DateUtil.of(21014, Month.OCTOBER),
                "Java архитектор",
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, " +
                        "версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование " +
                        "системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка " +
                        "интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт " +
                        "в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS " +
                        "Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, " +
                        "xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        company = new Company(new ArrayList<>(), "RIT Center");
        company.addPeriod(period);
        experience.addCompany(company);

        period = new Period(DateUtil.of(2010, Month.JANUARY),
                DateUtil.of(2012, Month.APRIL),
                "Ведущий программист",
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, " +
                        "SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация " +
                        "RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического " +
                        "трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        company = new Company(new ArrayList<>(), "Luxoft (Deutsche Bank)", "https://www.luxoft.com/");
        company.addPeriod(period);
        experience.addCompany(company);

        period = new Period(DateUtil.of(2008, Month.JUNE),
                DateUtil.of(2010, Month.JANUARY),
                "Ведущий специалист",
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                        "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                        "Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента " +
                        "(Python/ Jython, Django, ExtJS)");
        company = new Company(new ArrayList<>(), "Yota", "https://www.yota.ru/");
        company.addPeriod(period);
        experience.addCompany(company);

        period = new Period(DateUtil.of(2007, Month.MARCH),
                DateUtil.of(2008, Month.JUNE),
                "Разработчик ПО",
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, " +
                        "JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        company = new Company(new ArrayList<>(), "Enkata", "https://www.enkata.com/");
        company.addPeriod(period);
        experience.addCompany(company);

        period = new Period(DateUtil.of(2005, Month.JANUARY),
                DateUtil.of(2007, Month.FEBRUARY),
                "Разработчик ПО",
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка " +
                        "ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        company = new Company(new ArrayList<>(), "Siemens AG", "https://www.siemens.com/");
        company.addPeriod(period);
        experience.addCompany(company);

        period = new Period(DateUtil.of(1997, Month.SEPTEMBER),
                DateUtil.of(2005, Month.JANUARY),
                "Инженер по аппаратному и программному тестированию",
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 " +
                        "(CHILL, ASM).");
        company = new Company(new ArrayList<>(), "Alcatel", "https://www.alcatel.ru/");
        company.addPeriod(period);
        experience.addCompany(company);

        CompanySection education = new CompanySection(new ArrayList<>());

        period = new Period(DateUtil.of(2013, Month.MARCH),
                DateUtil.of(2013, Month.MAY),
                "'Functional Programming Principles in Scala' by Martin Odersky");
        company = new Company(new ArrayList<>(), "Coursera", "https://www.coursera.org/course/progfun");
        company.addPeriod(period);
        education.addCompany(company);

        period = new Period(DateUtil.of(2011, Month.MARCH),
                DateUtil.of(2011, Month.APRIL),
                "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'");
        company = new Company(new ArrayList<>(), "Luxoft",
                "https://www.luxoft-training.ru/training/catalog/course.html?ID=22366");
        company.addPeriod(period);
        education.addCompany(company);

        period = new Period(DateUtil.of(2005, Month.JANUARY),
                DateUtil.of(2005, Month.APRIL),
                "3 месяца обучения мобильным IN сетям (Берлин)");
        company = new Company(new ArrayList<>(), "Siemens AG", "https://www.siemens.com/");
        company.addPeriod(period);
        education.addCompany(company);

        period = new Period(DateUtil.of(1997, Month.SEPTEMBER),
                DateUtil.of(1998, Month.MARCH),
                "6 месяцев обучения цифровым телефонным сетям (Москва)");
        company = new Company(new ArrayList<>(), "Alcatel", "https://www.alcatel.ru/");
        company.addPeriod(period);
        education.addCompany(company);

        period = new Period(DateUtil.of(1993, Month.SEPTEMBER),
                DateUtil.of(1996, Month.JULY),
                "Аспирантура (программист С, С++)");
        company = new Company(new ArrayList<>(),
                "Санкт-Петербургский национальный исследовательский университет информационных " +
                        "технологий, механики и оптики",
                "https://itmo.ru/");
        company.addPeriod(period);
        period = new Period(DateUtil.of(1987, Month.SEPTEMBER),
                DateUtil.of(1993, Month.JULY),
                "Инженер (программист Fortran, C)");
        company.addPeriod(period);
        education.addCompany(company);

        period = new Period(DateUtil.of(1984, Month.SEPTEMBER),
                DateUtil.of(1987, Month.JUNE),
                "Закончил с отличием");
        company = new Company(new ArrayList<>(),
                "Заочная физико-техническая школа при МФТИ",
                "https://mipt.ru/");
        company.addPeriod(period);
        education.addCompany(company);

        resume.addSection(SectionType.PERSONAL, personal);
        resume.addSection(SectionType.OBJECTIVE, objective);
        resume.addSection(SectionType.ACHIEVEMENT, achievement);
        resume.addSection(SectionType.QUALIFICATIONS, qualifications);
        resume.addSection(SectionType.EXPERIENCE, experience);
        resume.addSection(SectionType.EDUCATION, education);

        // Output
        System.out.println(resume.getFullName() + "\n");

        for (ContactType type : ContactType.values()) {
            System.out.println(type.getTitle() + ": " + resume.getContact(type));
        }

        System.out.println();

        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle());
            AbstractSection section = resume.getSection(type);
            if (section instanceof TextSection) {
                System.out.println("-" + section + "\n");
            } else if (section instanceof ListSection) {
                for (String string : ((ListSection) section).getContentList()) {
                    System.out.println("-" + string);
                }
                System.out.println();
            } else if (section instanceof CompanySection) {
                for (Company comp : ((CompanySection) section).getCompanies()) {
                    System.out.println(comp);
                }
            }
        }
        return resume;
    }
}
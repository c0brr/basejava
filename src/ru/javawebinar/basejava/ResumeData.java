package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.*;

public class ResumeData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        resume.addContact(ContactType.PHONE_NUMBER, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin/");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin/");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin/");
        resume.addContact(ContactType.HOME_PAGE, "http://gkislin.ru/");

        // Text Sections
        TextSection personal = new TextSection();
        personal.setContent("Аналитический склад ума, сильная логика, креативность, инициативность. " +
                "Пурист кода и архитектуры.");

        TextSection objective = new TextSection();
        objective.setContent("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

        //List Sections
        ListSection achievement = new ListSection();
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

        ListSection qualifications = new ListSection();
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
        CompanySection experience = new CompanySection();

        Period period = new Period();
        period.setStartDate(LocalDate.of(2013, 10, 1));
        period.setEndDate(LocalDate.now());
        period.setDescription("Создание, организация и проведение Java онлайн проектов и стажировок.");
        period.setTitle("Автор проекта.");
        Company company = new Company();
        company.setWebsite("https://www.javaops.ru/");
        company.setName("Java Online Projects");
        company.addPeriod(period);
        experience.addCompany(company);

        period = new Period();
        period.setStartDate(LocalDate.of(2014, 10, 1));
        period.setEndDate(LocalDate.of(2016, 1, 1));
        period.setDescription("Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, " +
                "Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, " +
                "авторизация по OAuth1, OAuth2, JWT SSO.");
        period.setTitle("Старший разработчик (backend)");
        company = new Company();
        company.setWebsite("https://www.wrike.com/");
        company.setName("Wrike");
        company.addPeriod(period);
        experience.addCompany(company);

        period = new Period();
        period.setStartDate(LocalDate.of(2012, 4, 1));
        period.setEndDate(LocalDate.of(2014, 10, 1));
        period.setDescription("Организация процесса разработки системы ERP для разных окружений: релизная политика, " +
                "версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование " +
                "системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка " +
                "интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт " +
                "в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS " +
                "Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, " +
                "xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        period.setTitle("Java архитектор");
        company = new Company();
        company.setWebsite(null);
        company.setName("RIT Center");
        company.addPeriod(period);
        experience.addCompany(company);

        period = new Period();
        period.setStartDate(LocalDate.of(2010, 12, 1));
        period.setEndDate(LocalDate.of(2012, 4, 1));
        period.setDescription("Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, " +
                "SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация " +
                "RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического " +
                "трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        period.setTitle("Ведущий программист");
        company = new Company();
        company.setWebsite("https://www.luxoft.com/");
        company.setName("Luxoft (Deutsche Bank)");
        company.addPeriod(period);
        experience.addCompany(company);

        period = new Period();
        period.setStartDate(LocalDate.of(2008, 6, 1));
        period.setEndDate(LocalDate.of(2010, 12, 1));
        period.setDescription("Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" " +
                "(GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). " +
                "Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента " +
                "(Python/ Jython, Django, ExtJS)");
        period.setTitle("Ведущий специалист");
        company = new Company();
        company.setWebsite("https://www.yota.ru/");
        company.setName("Yota");
        company.addPeriod(period);
        experience.addCompany(company);

        period = new Period();
        period.setStartDate(LocalDate.of(2007, 3, 1));
        period.setEndDate(LocalDate.of(2008, 6, 1));
        period.setDescription("Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, " +
                "JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        period.setTitle("Разработчик ПО");
        company = new Company();
        company.setWebsite("https://www.enkata.com/");
        company.setName("Enkata");
        company.addPeriod(period);
        experience.addCompany(company);

        period = new Period();
        period.setStartDate(LocalDate.of(2005, 1, 1));
        period.setEndDate(LocalDate.of(2007, 2, 1));
        period.setDescription("Разработка информационной модели, проектирование интерфейсов, реализация и отладка " +
                "ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        period.setTitle("Разработчик ПО");
        company = new Company();
        company.setWebsite("https://www.siemens.com/");
        company.setName("Siemens AG");
        company.addPeriod(period);
        experience.addCompany(company);

        period = new Period();
        period.setStartDate(LocalDate.of(1997, 9, 1));
        period.setEndDate(LocalDate.of(2005, 1, 1));
        period.setDescription("Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 " +
                "(CHILL, ASM).");
        period.setTitle("Инженер по аппаратному и программному тестированию");
        company = new Company();
        company.setWebsite("https://www.alcatel.ru/");
        company.setName("Alcatel");
        company.addPeriod(period);
        experience.addCompany(company);

        CompanySection education = new CompanySection();

        period = new Period();
        period.setStartDate(LocalDate.of(2013, 3, 1));
        period.setEndDate(LocalDate.of(2013, 5, 1));
        period.setTitle("'Functional Programming Principles in Scala' by Martin Odersky");
        company = new Company();
        company.setWebsite("https://www.coursera.org/course/progfun");
        company.setName("Coursera");
        company.addPeriod(period);
        education.addCompany(company);

        period = new Period();
        period.setStartDate(LocalDate.of(2011, 3, 1));
        period.setEndDate(LocalDate.of(2011, 4, 1));
        period.setTitle("Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'");
        company = new Company();
        company.setWebsite("https://www.luxoft-training.ru/training/catalog/course.html?ID=22366");
        company.setName("Luxoft");
        company.addPeriod(period);
        education.addCompany(company);

        period = new Period();
        period.setStartDate(LocalDate.of(2005, 1, 1));
        period.setEndDate(LocalDate.of(2005, 4, 1));
        period.setTitle("3 месяца обучения мобильным IN сетям (Берлин)");
        company = new Company();
        company.setWebsite("https://www.siemens.com/");
        company.setName("Siemens AG");
        company.addPeriod(period);
        education.addCompany(company);

        period = new Period();
        period.setStartDate(LocalDate.of(1997, 9, 1));
        period.setEndDate(LocalDate.of(1998, 3, 1));
        period.setTitle("6 месяцев обучения цифровым телефонным сетям (Москва)");
        company = new Company();
        company.setWebsite("https://www.alcatel.ru/");
        company.setName("Alcatel");
        company.addPeriod(period);
        education.addCompany(company);

        period = new Period();
        period.setStartDate(LocalDate.of(1993, 9, 1));
        period.setEndDate(LocalDate.of(1996, 7, 1));
        period.setTitle("Аспирантура (программист С, С++)");
        company = new Company();
        company.setWebsite("https://itmo.ru/");
        company.setName("Санкт-Петербургский национальный исследовательский университет информационных " +
                "технологий, механики и оптики");
        company.addPeriod(period);
        period = new Period();
        period.setStartDate(LocalDate.of(1987, 9, 1));
        period.setEndDate(LocalDate.of(1993, 7, 1));
        period.setTitle("Инженер (программист Fortran, C)");
        company.addPeriod(period);
        education.addCompany(company);

        period = new Period();
        period.setStartDate(LocalDate.of(1984, 9, 1));
        period.setEndDate(LocalDate.of(1987, 6, 1));
        period.setTitle("Закончил с отличием");
        company = new Company();
        company.setWebsite("https://mipt.ru/");
        company.setName("Заочная физико-техническая школа при МФТИ");
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

        for (Map.Entry<ContactType, String> pair : resume.getContacts().entrySet()) {
            System.out.println(pair.getKey().getTitle() + ": " + pair.getValue());
        }

        System.out.println();

        for (Map.Entry<SectionType, AbstractSection> pair : resume.getSections().entrySet()) {
            System.out.println(pair.getKey().getTitle() + ":");
            if (pair.getValue() instanceof TextSection) {
                System.out.println("-" + pair.getValue());
            } else if (pair.getValue() instanceof ListSection) {
                for (String content : ((ListSection) pair.getValue()).getList()) {
                    System.out.println("-" + content);
                }
            } else if (pair.getValue() instanceof CompanySection) {
                for (Company comp : ((CompanySection) pair.getValue()).getCompanies()) {
                    System.out.println(comp.getName());
                    if (comp.getWebsite() != null) {
                        System.out.println(comp.getWebsite());
                    }
                    for (Period per : comp.getPeriods()) {
                        System.out.println(per);
                    }
                    System.out.println();
                }
            }
            System.out.println();
        }
    }
}

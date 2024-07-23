package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.Config;

import java.io.File;
import java.util.*;

public abstract class AbstractStorageTest {
    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();
    private static final Resume RESUME_1; /*= ResumeTestData.createResume(UUID_1, "Name1")*/
    private static final Resume RESUME_2; /*= ResumeTestData.createResume(UUID_2, "Name2")*/
    private static final Resume RESUME_3; /*= ResumeTestData.createResume(UUID_3, "Name3")*/
    private static final Resume RESUME_4; /*= ResumeTestData.createResume(UUID_4, "Name4")*/
    protected static final File FILE_STORAGE_DIR = Config.get().getStorageDir();
    protected static final String PATH_STORAGE_DIR = ".\\storage";
    protected final Storage storage;

    static {
        RESUME_1 = new Resume(UUID_1, "Name1");
        RESUME_2 = new Resume(UUID_2, "Name2");
        RESUME_3 = new Resume(UUID_3, "Name3");
        RESUME_4 = new Resume(UUID_4, "Name4");

        RESUME_1.getContacts().put(ContactType.EMAIL, "mail1@ya.ru");
        RESUME_1.getContacts().put(ContactType.PHONE_NUMBER, "11111");
        RESUME_2.getContacts().put(ContactType.SKYPE, "skype2");
        RESUME_2.getContacts().put(ContactType.PHONE_NUMBER, "22222");
        RESUME_2.getSections().put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, " +
                "сильная логика, " +
                "креативность, инициативность. " +
                "Пурист кода и архитектуры."));
        RESUME_3.getSections().put(SectionType.OBJECTIVE,
                new TextSection("Ведущий стажировок и корпоративного обучения по Java Web " +
                "и Enterprise технологиям"));


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
        RESUME_2.getSections().put(SectionType.ACHIEVEMENT, achievement);

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

        RESUME_3.getSections().put(SectionType.QUALIFICATIONS, qualifications);
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        assertSize(3);
        storage.delete(UUID_2);
        assertSize(2);
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "New Name");
        storage.update(newResume);
        Assert.assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test
    public void clear() {
        assertSize(3);
        storage.clear();
        assertSize(0);
        List<Resume> list = Collections.emptyList();
        assertListEquals(list);
    }

    @Test
    public void getAllSorted() {
        List<Resume> list = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        assertSize(3);
        assertListEquals(list);
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertListEquals(List<Resume> resumes) {
        List<Resume> actual = storage.getAllSorted();
        Assert.assertEquals(resumes, actual);
    }
}
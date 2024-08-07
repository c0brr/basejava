package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.util.Config;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
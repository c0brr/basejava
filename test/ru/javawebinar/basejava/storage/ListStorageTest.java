package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public class ListStorageTest {
    private final ListStorage storage = new ListStorage();
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);
    private static final int DUMMY = Integer.MIN_VALUE;

    @BeforeEach
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
    public void getSearchKey() {
        assertGetSearchKey(0, UUID_1);
        assertGetSearchKey(1, UUID_2);
        assertGetSearchKey(2, UUID_3);
        assertGetSearchKey(-1, UUID_4);
    }

    @Test
    public void isExist() {
        assertIsExist(0);
        assertIsExist(1);
        assertIsExist(2);
        Assertions.assertFalse(storage.isExist(-1));
    }

    @Test
    public void doSave() {
        storage.doSave(RESUME_4, DUMMY);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    public void doDelete() {
        storage.doDelete(1);
        assertSize(2);
        Assertions.assertThrows(NotExistStorageException.class, () -> assertGet(RESUME_2));
    }

    @Test
    public void doGet() {
        Assertions.assertEquals(RESUME_3, storage.doGet(2));
    }

    @Test
    public void doUpdate() {
        storage.doUpdate(0, RESUME_1);
        Assertions.assertSame(RESUME_1, storage.get(UUID_1));
    }

    @Test
    public void clear() {
        assertSize(3);
        storage.clear();
        assertSize(0);
        Resume[] expected = new Resume[0];
        assertArrayEquals(expected);
    }

    @Test
    public void getAll() {
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        assertArrayEquals(expected);
    }

    private void assertSize(int size) {
        Assertions.assertEquals(size, storage.size());
    }

    private void assertGetSearchKey(int index, String uuid) {
        Assertions.assertEquals(index, storage.getSearchKey(uuid));
    }

    private void assertIsExist(int index) {
        Assertions.assertTrue(storage.isExist(index));
    }

    private void assertGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertArrayEquals(Resume[] resumes) {
        Assertions.assertArrayEquals(resumes, storage.getAll());
    }
}
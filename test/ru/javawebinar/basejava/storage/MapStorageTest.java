package ru.javawebinar.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class MapStorageTest {
    private final MapStorage storage = new MapStorage();
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);

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
        assertGetSearchKey(UUID_1);
        assertGetSearchKey(UUID_2);
        assertGetSearchKey(UUID_3);
        assertGetSearchKey(null);
    }

    @Test
    public void isExist() {
        assertIsExist(UUID_1);
        assertIsExist(UUID_2);
        assertIsExist(UUID_3);
        Assertions.assertFalse(storage.isExist(null));
    }

    @Test
    public void doSave() {
        storage.doSave(RESUME_4, UUID_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    public void doDelete() {
        storage.doDelete(UUID_2);
        assertSize(2);
        Assertions.assertThrows(NotExistStorageException.class, () -> assertGet(RESUME_2));
    }

    @Test
    public void doGet() {
        Assertions.assertEquals(RESUME_3, storage.doGet(UUID_3));
    }

    @Test
    public void doUpdate() {
        storage.doUpdate(UUID_1, RESUME_1);
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

    private void assertGetSearchKey(String uuid) {
        Assertions.assertEquals(uuid, storage.getSearchKey(uuid));
    }

    private void assertIsExist(String uuid) {
        Assertions.assertTrue(storage.isExist(uuid));
    }

    private void assertGet(Resume resume) {
        Assertions.assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertArrayEquals(Resume[] resumes) {
        Resume[] actual = storage.getAll();
        Arrays.sort(actual);
        Assertions.assertArrayEquals(resumes, actual);
    }
}
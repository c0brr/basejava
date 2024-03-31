package ru.javawebinar.basejava.storage;

import org.junit.Assert;
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
    private static final int DUMMY = 0;

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
    public void getIndex() {
        assertGetIndex(0, UUID_1);
        assertGetIndex(1, UUID_2);
        assertGetIndex(2, UUID_3);
        assertGetIndex(-1, UUID_4);
    }

    @Test
    public void addResume() {
        storage.addResume(RESUME_4, DUMMY);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    public void deleteResume() {
        assertSize(3);
        assertGet(RESUME_3);
        storage.deleteResume(storage.getIndex(RESUME_3.getUuid()));
        assertSize(2);
        Assertions.assertThrows(NotExistStorageException.class, () -> assertGet(RESUME_3));
    }

    @Test
    public void getResume() {
        assertSame(RESUME_1);
        assertSame(RESUME_2);
        assertSame(RESUME_3);
    }

    @Test
    public void updateResume() {
        storage.updateResume(1, storage.getResume(1));
        assertSame(storage.getResume(1));
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

    private void assertGetIndex(int expectedIndex, String uuid) {
        Assertions.assertEquals(expectedIndex, storage.getIndex(uuid));
    }

    private void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertSame(Resume resume) {
        Assertions.assertSame(resume, storage.getResume(storage.getIndex(resume.getUuid())));
    }

    private void assertArrayEquals(Resume[] resumes) {
        Assertions.assertArrayEquals(resumes, storage.getAll());
    }
}
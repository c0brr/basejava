package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);
    private static final int STORAGE_LIMIT = 10_000;

    public AbstractArrayStorageTest(Storage storage) {
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
        assertSize(3);
        Assert.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_4));
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
        Assert.assertThrows(ExistStorageException.class, () -> storage.save(RESUME_4));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Storage overflow happened ahead of time");
        }
        storage.save(RESUME_1);
    }

    @Test
    public void delete() {
        assertSize(3);
        storage.delete(UUID_2);
        assertSize(2);
        Assert.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_2));
        Assert.assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_4));
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
        Assert.assertThrows(NotExistStorageException.class, () -> storage.get(UUID_4));
    }

    @Test
    public void update() {
        storage.update(RESUME_3);
        Assert.assertSame(RESUME_3, storage.get(UUID_3));
        Assert.assertThrows(NotExistStorageException.class, () -> storage.update(RESUME_4));
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
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertArrayEquals(Resume[] resumes) {
        Assert.assertArrayEquals(resumes, storage.getAll());
    }
}
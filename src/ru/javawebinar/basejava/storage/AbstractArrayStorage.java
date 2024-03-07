package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        if (size >= STORAGE_LIMIT) {
            System.out.println("Error: storage overflow");
        } else if (getIndex(uuid) != -1) {
            System.out.println("Error: resume " + uuid + " already exists");
        } else {
            storage[size++] = resume;
        }
    }

    public void delete(String uuid) {
        int resumeIndex = getIndex(uuid);
        if (resumeIndex == -1) {
            printNotFound(uuid);
            return;
        }
        storage[resumeIndex] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    public Resume get(String uuid) {
        int resumeIndex = getIndex(uuid);
        if (resumeIndex == -1) {
            printNotFound(uuid);
            return null;
        }
        return storage[resumeIndex];
    }

    public void update(Resume resume) {
        int resumeIndex = getIndex(resume.getUuid());
        if (resumeIndex == -1) {
            printNotFound(resume.getUuid());
            return;
        }
        storage[resumeIndex] = resume;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    private void printNotFound(String uuid) {
        System.out.println("Error: resume " + uuid + " not exists");
    }
}
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

    public final int size() {
        return size;
    }

    public final void save(Resume resume) {
        String uuid = resume.getUuid();
        if (size >= STORAGE_LIMIT) {
            System.out.println("Error: storage overflow");
        } else if (getIndex(uuid) >= 0) {
            System.out.println("Error: resume " + uuid + " already exists");
        } else {
            addResume(resume);
        }
    }

    public final void delete(String uuid) {
        if (getIndex(uuid) < 0) {
            printNotFound(uuid);
            return;
        }
        deleteResume(uuid);
    }

    public final Resume get(String uuid) {
        int resumeIndex = getIndex(uuid);
        if (resumeIndex < 0) {
            printNotFound(uuid);
            return null;
        }
        return storage[resumeIndex];
    }

    public final void update(Resume resume) {
        int resumeIndex = getIndex(resume.getUuid());
        if (resumeIndex < 0) {
            printNotFound(resume.getUuid());
            return;
        }
        storage[resumeIndex] = resume;
    }

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void addResume(Resume resume);

    protected abstract void deleteResume(String uuid);

    private void printNotFound(String uuid) {
        System.out.println("Error: resume " + uuid + " not exists");
    }
}
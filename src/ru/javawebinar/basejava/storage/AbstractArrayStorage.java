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

    public final void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Error: storage overflow");
            return;
        }
        String uuid = resume.getUuid();
        int foundIndex = getIndex(uuid);
        if (foundIndex >= 0) {
            System.out.println("Error: resume " + uuid + " already exists");
        } else {
            addResume(resume, foundIndex);
            size++;
        }
    }

    public final void delete(String uuid) {
        int foundIndex = getIndex(uuid);
        if (foundIndex < 0) {
            printNotFound(uuid);
            return;
        }
        deleteResume(foundIndex);
        size--;
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

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void addResume(Resume resume, int foundIndex);

    protected abstract void deleteResume(int foundIndex);

    private void printNotFound(String uuid) {
        System.out.println("Error: resume " + uuid + " not exists");
    }
}
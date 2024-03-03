package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int MAX_RESUMES = 10_000;
    private final Resume[] storage = new Resume[MAX_RESUMES];
    private int size;

    public int size() {
        return size;
    }

    public void save(Resume resume) {
        if (size < MAX_RESUMES) {
            if (getIndex(resume.getUuid()) >= 0) {
                System.out.println("Ошибка: резюме " + resume.getUuid() + " уже имеется в базе");
                return;
            }
            storage[size++] = resume;
            return;
        }
        System.out.println("Ошибка: хранилище заполнено");
    }

    public void update(Resume resume) {
        int resumeIndex = getIndex(resume.getUuid());
        if (resumeIndex >= 0) {
            storage[resumeIndex] = resume;
            return;
        }
        printNotFound(resume.getUuid());
    }

    public void delete(String uuid) {
        int resumeIndex = getIndex(uuid);
        if (resumeIndex >= 0) {
            storage[resumeIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            return;
        }
        printNotFound(uuid);
    }

    public Resume get(String uuid) {
        int resumeIndex = getIndex(uuid);
        if (resumeIndex >= 0) {
            return storage[resumeIndex];
        }
        printNotFound(uuid);
        return null;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private void printNotFound(String uuid) {
        System.out.println("Ошибка: резюме " + uuid + " не найдено");
    }
}
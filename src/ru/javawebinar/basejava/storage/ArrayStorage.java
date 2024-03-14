package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertElement(Resume resume, int foundIndex) {
        storage[size] = resume;
    }

    @Override
    protected void fillDeletedElement(int foundIndex) {
        storage[foundIndex] = storage[size - 1];
        storage[size - 1] = null;
    }
}
package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void checkOverflow(String uuid) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", uuid);
        }
    }

    @Override
    protected final void addResume(Resume resume, int foundIndex) {
        insertElement(resume, foundIndex);
        size++;
    }

    @Override
    protected final void deleteResume(int foundIndex) {
        fillDeletedElement(foundIndex);
        size--;
    }

    @Override
    protected Resume getResume(int resumeIndex) {
        return storage[resumeIndex];
    }

    @Override
    protected void updateResume(int resumeIndex, Resume resume) {
        storage[resumeIndex] = resume;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void insertElement(Resume resume, int foundIndex);

    protected abstract void fillDeletedElement(int foundIndex);
}
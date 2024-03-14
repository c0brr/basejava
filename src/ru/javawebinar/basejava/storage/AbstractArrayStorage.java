package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
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
        String uuid = resume.getUuid();
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", uuid);
        }
        int foundIndex = getIndex(uuid);
        if (foundIndex >= 0) {
            throw new ExistStorageException(uuid);
        } else {
            insertElement(resume, foundIndex);
            size++;
        }
    }

    public final void delete(String uuid) {
        int foundIndex = getIndex(uuid);
        if (foundIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
        fillDeletedElement(foundIndex);
        size--;
    }

    public final Resume get(String uuid) {
        int resumeIndex = getIndex(uuid);
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[resumeIndex];
    }

    public final void update(Resume resume) {
        int resumeIndex = getIndex(resume.getUuid());
        if (resumeIndex < 0) {
            throw new NotExistStorageException(resume.getUuid());
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

    protected abstract void insertElement(Resume resume, int foundIndex);

    protected abstract void fillDeletedElement(int foundIndex);
}
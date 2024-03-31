package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public final void save(Resume resume) {
        String uuid = resume.getUuid();
        checkOverflow(uuid);
        int foundIndex = getIndex(uuid);
        if (foundIndex >= 0) {
            throw new ExistStorageException(uuid);
        } else {
            addResume(resume, foundIndex);
        }
    }

    @Override
    public final void delete(String uuid) {
        int foundIndex = getIndex(uuid);
        if (foundIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteResume(foundIndex);
    }

    @Override
    public final Resume get(String uuid) {
        int resumeIndex = getIndex(uuid);
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(resumeIndex);
    }

    @Override
    public final void update(Resume resume) {
        int resumeIndex = getIndex(resume.getUuid());
        if (resumeIndex < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        updateResume(resumeIndex, resume);
    }

    protected abstract void checkOverflow(String uuid);

    protected abstract int getIndex(String uuid);

    protected abstract void addResume(Resume resume, int foundIndex);

    protected abstract void deleteResume(int foundIndex);

    protected abstract Resume getResume(int resumeIndex);

    protected abstract void updateResume(int resumeIndex, Resume resume);
}

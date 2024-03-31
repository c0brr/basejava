package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void checkOverflow(String uuid) {

    }

    @Override
    protected int getIndex(String uuid) {
        for (Resume resume : storage) {
            if (resume.getUuid().equals(uuid)) {
                return storage.indexOf(resume);
            }
        }
        return -1;
    }

    @Override
    protected void addResume(Resume resume, int foundIndex) {
        storage.add(resume);
    }

    @Override
    protected void deleteResume(int foundIndex) {
        storage.remove(foundIndex);
    }

    @Override
    protected Resume getResume(int resumeIndex) {
        return storage.get(resumeIndex);
    }

    @Override
    protected void updateResume(int resumeIndex, Resume resume) {
        storage.set(resumeIndex, resume);
    }


    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }
}

package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public interface Storage {
    int size();

    void save(Resume resume);

    void delete(String uuid);

    Resume get(String uuid);

    void update(Resume resume);

    void clear();

    Resume[] getAll();
}
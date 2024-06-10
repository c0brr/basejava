package ru.javawebinar.basejava.storage.strategypattern;

import ru.javawebinar.basejava.model.Resume;

import java.util.List;

public interface Storage {

    int size();

    void save(Resume resume);

    void delete(String uuid);

    Resume get(String uuid);

    void update(Resume resume);

    void clear();

    List<Resume> getAllSorted();
}
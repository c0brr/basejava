package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapEntryValueStorage extends AbstractMapStorage {

    @Override
    protected Object getSearchKey(String uuid) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getKey().equals(uuid)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    protected void doDelete(Object searchKey) {
        Iterator<Map.Entry<String, Resume>> itr = storage.entrySet().iterator();
        while (itr.hasNext()) {
            if (itr.next().getValue().equals(searchKey)) {
                itr.remove();
            }
        }
    }

    @Override
    protected Resume doGet(Object searchKey) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getValue().equals(searchKey)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        for (Map.Entry<String, Resume> entry : storage.entrySet()) {
            if (entry.getValue().equals(searchKey)) {
                storage.replace(entry.getKey(), resume);
            }
        }
    }
}

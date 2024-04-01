package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertElement(Resume resume, Object searchKey) {
        int insertionIndex = Math.abs(((int) searchKey) + 1);
        if (insertionIndex != size) {
            System.arraycopy(storage, insertionIndex, storage,
                    insertionIndex + 1, size - insertionIndex);
        }
        storage[insertionIndex] = resume;
    }

    @Override
    protected void fillDeletedElement(Object searchKey) {
        int lastIndex = size - 1;
        if ((int) searchKey != lastIndex) {
            System.arraycopy(storage, ((int) searchKey) + 1, storage,
                    (int) searchKey, lastIndex - ((int) searchKey));
        }
        storage[lastIndex] = null;
    }
}
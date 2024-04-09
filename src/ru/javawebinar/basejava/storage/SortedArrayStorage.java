package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertElement(Resume resume, int searchKey) {
        int insertionIndex = Math.abs((searchKey) + 1);
        if (insertionIndex != size) {
            System.arraycopy(storage, insertionIndex, storage,
                    insertionIndex + 1, size - insertionIndex);
        }
        storage[insertionIndex] = resume;
    }

    @Override
    protected void fillDeletedElement(int searchKey) {
        int lastIndex = size - 1;
        if (searchKey != lastIndex) {
            System.arraycopy(storage, (searchKey) + 1, storage, searchKey, lastIndex - (searchKey));
        }
        storage[lastIndex] = null;
    }
}
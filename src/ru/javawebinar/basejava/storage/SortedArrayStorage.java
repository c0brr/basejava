package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertElement(Resume resume, int foundIndex) {
        int insertionIndex = Math.abs(foundIndex + 1);
        if (insertionIndex != size) {
            System.arraycopy(storage, insertionIndex, storage,
                    insertionIndex + 1, size - insertionIndex);
        }
        storage[insertionIndex] = resume;
    }

    @Override
    protected void fillDeletedElement(int foundIndex) {
        int lastIndex = size - 1;
        if (foundIndex != lastIndex) {
            System.arraycopy(storage, foundIndex + 1, storage, foundIndex, lastIndex - foundIndex);
        }
        storage[lastIndex] = null;
    }
}

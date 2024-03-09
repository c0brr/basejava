package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void addResume(Resume resume) {
        int insertionIndex = Math.abs(getIndex(resume.getUuid()) + 1);
        if (insertionIndex != size) {
            System.arraycopy(storage, insertionIndex, storage,
                    insertionIndex + 1, size - insertionIndex);
        }
        storage[insertionIndex] = resume;
        size++;
    }

    @Override
    protected void deleteResume(String uuid) {
        int index = getIndex(uuid);
        size--;
        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
        }
        storage[size] = null;
    }
}

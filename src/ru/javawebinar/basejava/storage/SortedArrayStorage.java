package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getUuid);
    private static final String DUMMY = "";

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid, DUMMY);
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
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
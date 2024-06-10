package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategypattern.ObjectStreamStorage;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new ObjectStreamStorage(FILE_STORAGE_DIR).getStrategy());
    }
}
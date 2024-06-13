package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategypattern.FileStorage;
import ru.javawebinar.basejava.storage.strategypattern.ObjectStreamStorage;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(FILE_STORAGE_DIR, new ObjectStreamStorage()));
    }
}
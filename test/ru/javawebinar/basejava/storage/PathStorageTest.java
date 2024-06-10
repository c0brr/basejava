package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategypattern.ObjectStreamStorage;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new ObjectStreamStorage(PATH_STORAGE_DIR).getStorage());
    }
}

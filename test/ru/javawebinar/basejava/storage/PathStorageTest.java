package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategypattern.ObjectStreamStorage;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(PATH_STORAGE_DIR, new ObjectStreamStorage()));
    }
}

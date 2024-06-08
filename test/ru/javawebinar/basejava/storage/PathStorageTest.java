package ru.javawebinar.basejava.storage;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new ObjectStreamStorage(PATH_STORAGE_DIR).storage);
    }
}

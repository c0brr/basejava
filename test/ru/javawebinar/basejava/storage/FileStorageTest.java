package ru.javawebinar.basejava.storage;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new ObjectStreamStorage(FILE_STORAGE_DIR).storage);
    }
}
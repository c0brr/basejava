package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(PATH_STORAGE_DIR, new DataStreamSerializer()));
    }
}

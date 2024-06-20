package ru.javawebinar.basejava.storage.serializer;

import java.io.IOException;

public interface CustomSupplier <T> {
    T customGet() throws IOException;
}

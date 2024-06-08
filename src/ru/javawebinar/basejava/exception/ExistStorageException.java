package ru.javawebinar.basejava.exception;

import java.io.Serial;

public class ExistStorageException extends StorageException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " already exists", uuid);
    }
}

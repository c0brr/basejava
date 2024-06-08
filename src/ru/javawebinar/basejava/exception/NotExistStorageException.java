package ru.javawebinar.basejava.exception;

import java.io.Serial;

public class NotExistStorageException extends StorageException {
    @Serial
    private static final long serialVersionUID = 1L;

    public NotExistStorageException(String uuid) {
        super("Resume " + uuid + " not exists", uuid);
    }
}

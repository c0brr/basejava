package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class ObjectStreamStorage extends AbstractSerializationStorage {

    public ObjectStreamStorage(Object dir) {
        if (dir instanceof File) {
            storage = new AbstractFileStorage((File) dir) {
                @Override
                protected void doWrite(Resume resume, OutputStream os) throws IOException {
                    doDoWrite(resume, os);
                }

                @Override
                protected Resume doRead(InputStream is) throws IOException {
                    return doDoRead(is);
                }
            };
        } else if (dir instanceof String) {
            storage = new AbstractPathStorage((String) dir) {
                @Override
                protected void doWrite(Resume resume, OutputStream os) throws IOException {
                    doDoWrite(resume, os);
                }

                @Override
                protected Resume doRead(InputStream is) throws IOException {
                    return doDoRead(is);
                }
            };
        }
    }

    private void doDoWrite(Resume resume, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
        }
    }

    private Resume doDoRead(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}

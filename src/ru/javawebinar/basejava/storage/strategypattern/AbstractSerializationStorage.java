package ru.javawebinar.basejava.storage.strategypattern;

import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractSerializationStorage {
    protected Storage strategy;

    protected AbstractSerializationStorage(String dir) {
        strategy = new AbstractPathStorage(dir) {
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

    protected AbstractSerializationStorage(File dir) {
        strategy = new AbstractFileStorage(dir) {
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

    public Storage getStorage() {
        return strategy;
    }

    protected abstract void doDoWrite(Resume resume, OutputStream os) throws IOException;

    protected abstract Resume doDoRead(InputStream is) throws IOException;
}

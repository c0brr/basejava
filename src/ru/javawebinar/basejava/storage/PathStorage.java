package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.strategypattern.SerializationStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final SerializationStrategy serializationStrategy;

    public PathStorage(String dir, SerializationStrategy serializationStrategy) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory");
        }
        this.serializationStrategy = serializationStrategy;
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + path.toAbsolutePath(),
                    path.getFileName().toString(), e);
        }
        doUpdate(path, resume);
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("File not deleted " + path.toAbsolutePath(), path.getFileName().toString(), e);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return serializationStrategy.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("File read error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void doUpdate(Path path, Resume resume) {
        try {
            serializationStrategy.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("File write error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(getCheckedDirectoryList().map(this::doGet).toList());
    }

    @Override
    public int size() {
        return (int) getCheckedDirectoryList().count();
    }

    @Override
    public void clear() {
        getCheckedDirectoryList().forEach(this::doDelete);
    }

    private Stream<Path> getCheckedDirectoryList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Files.list error", null, e);
        }
    }
}
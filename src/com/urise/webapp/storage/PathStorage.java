package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.saver.Saver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;

    private Saver option;

    protected PathStorage(String dir, Saver option) {
        directory = Paths.get(dir);
        this.option = option;
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {  //coderoad.ru/50551920/
        return directory.resolve(uuid);
    }

    @Override
    public void clear() {
        getStreamPath().forEach(this::doDelete);
    }

    @Override
    public int size() {
        List<Resume> listFiles = getStreamPath().map(this::doGet).collect(Collectors.toList());
        return listFiles.size();
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            option.doWrite(r, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }


    @Override
    protected void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create path" + path, path.getFileName().toString(), e);
        }
        doUpdate(r, path);
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return option.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.toString(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", path.getFileName().toString());
        }
    }

    @Override
    protected List<Resume> copyAllResume() {
        return getStreamPath().map(this::doGet).collect(Collectors.toList());
    }

    private Stream<Path> getStreamPath() {
        try {
            Stream<Path> pathStream = Files.list(directory);
            return pathStream;
        } catch (IOException e) {
            throw new StorageException("Directory read error", null);
        }
    }
}
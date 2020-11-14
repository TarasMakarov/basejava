package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;

    ToKeepResume option;

    protected PathStorage(String dir, ToKeepResume option) {
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
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    //    @Override
//    public int size() {
//        String[] files = directory.toFile().list();
//        if (files == null) {
//            throw new StorageException("Directory read error", null);
//        }
//        return files.length;
//    }
    @Override
    public int size() {

        String[] files = directory.toFile().list();
        if (files == null) {
            throw new StorageException("Directory read error", null);
        }
        return files.length;
    }


    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            option.doWrite(r, new BufferedOutputStream(new FileOutputStream(path.toFile())));
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
            return option.doRead(new BufferedInputStream(new FileInputStream(path.toFile())));
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
    protected List<Resume> convertToList() {
        File[] paths = directory.toFile().listFiles();
        if (paths == null) {
            throw new StorageException("Directory read error", null);
        }
        List<Resume> listPath = new ArrayList<>(paths.length);
        for (File path : paths) {
            listPath.add(doGet(path.toPath()));
        }
        return listPath;
    }
}
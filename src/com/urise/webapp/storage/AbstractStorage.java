package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        Object searchKey = notExistStorageException(resume.getUuid());
        updateResume(resume, searchKey);
    }

    public void save(Resume resume) {
        Object searchKey = existStorageException(resume.getUuid());
        saveResume(resume, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = notExistStorageException(uuid);
        deleteResume(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = notExistStorageException(uuid);
        return getResume(searchKey);
    }

    private Object existStorageException(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (findResume(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object notExistStorageException(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!findResume(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean findResume(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void updateResume(Resume resume, Object searchKey);

    protected abstract void saveResume(Resume resume, Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract Resume getResume(Object searchKey);
}
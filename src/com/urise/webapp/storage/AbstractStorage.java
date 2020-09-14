package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;

public abstract class AbstractStorage implements Storage {

    Comparator<Resume> sortedResumeByFullName = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    public void update(Resume resume) {
        Object searchKey = getSearchKeyIfExist(resume.getUuid()); // analog getExistedSearchKey()
        doUpdate(resume, searchKey);
    }

    public void save(Resume resume) {
        Object searchKey = existStorageException(resume.getUuid()); // analog getNotExistedSearchKey
        doSave(resume, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getSearchKeyIfExist(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getSearchKeyIfExist(uuid);
        return doGet(searchKey);
    }

    private Object existStorageException(String uuid) { // analog getNotExistedSearchKey
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getSearchKeyIfExist(String uuid) {  // analog getExistedSearchKey
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}
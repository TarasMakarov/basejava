package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

//    Object searchKey(Object o) {
//        Object searchKey = o;
//        return searchKey;
//    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            Object o = index;
            updateResume(o, r);
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > -1) {
            throw new ExistStorageException(r.getUuid());
        } else {
            Object searchKey = index;
            saveResume(r, searchKey);
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            Object searchKey = index;
            deleteResume(searchKey);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        Object searchKey = index;
        return getResume(searchKey);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void updateResume(Object o, Resume r);

    protected abstract void saveResume(Resume r, Object o);

    protected abstract void deleteResume(Object o);

    protected abstract Resume getResume(Object o);
}
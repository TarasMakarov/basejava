package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected int index;

    public void update(Resume r) {
        index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateResume(r);
        }
    }

    public void save(Resume r) {
        index = getIndex(r.getUuid());
        if (index > -1) {
            throw new ExistStorageException(r.getUuid());
        } else {
            saveResume(r);
        }
    }

    public void delete(String uuid) {
        index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResume(uuid);
        }
    }

    public Resume get(String uuid) {
        index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(uuid);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void updateResume(Resume r);

    protected abstract void saveResume(Resume r);

    protected abstract void deleteResume(String uuid);

    protected abstract Resume getResume(String uuid);
}
package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        if (!NotExist(resume.getUuid())) {
            updateResume(resume);
        }
    }

    public void save(Resume resume) {
        if (!Exist(resume.getUuid())) {
            saveResume(resume);
        }
    }

    public void delete(String uuid) {
        if (!NotExist(uuid)) {
            deleteResume(uuid);
        }
    }

    public Resume get(String uuid) {
        if (!NotExist(uuid)) {
            return getResume(uuid);
        }
        return null;
    }

    protected abstract void updateResume(Resume resume);

    protected abstract void saveResume(Resume resume);

    protected abstract void deleteResume(String uuid);

    protected abstract Resume getResume(String uuid);

    protected abstract Object getSearchKey(String uuid);

    protected boolean NotExist(String uuid) {
        if ((int) getSearchKey(uuid) < 0) {
            throw new NotExistStorageException(uuid);
        }
        return false;
    }

    protected boolean Exist(String uuid) {
        if ((int) getSearchKey(uuid) > -1) {
            throw new ExistStorageException(uuid);
        }
        return false;
    }
}
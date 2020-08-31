package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume r) {
        if (!NotExist(r.getUuid())) {
            updateResume(r);
        }
    }

    public void save(Resume r) {
        if (!Exist(r.getUuid())) {
            saveResume(r);
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

    protected abstract void updateResume(Resume r);

    protected abstract void saveResume(Resume r);

    protected abstract void deleteResume(String uuid);

    protected abstract Resume getResume(String uuid);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean NotExist(String uuid);

    protected abstract boolean Exist(String uuid);
}
package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public int size() {
        return sizeStorage();
    }

    public void clear() {
        clearStorage();
    }

    public void update(Resume r) {
        updateResume(r);
    }


    public Resume[] getAll() {
        return getAllResumes();
    }

    final public void save(Resume r) {
        saveResume(r);
    }

    final public void delete(String uuid) {
        deleteResume(uuid);
    }

    final public Resume get(String uuid) {
        return getResume(uuid);
    }

    protected abstract void clearStorage();

    protected abstract int sizeStorage();

    protected abstract void saveResume(Resume r);

    protected abstract void updateResume(Resume r);

    protected abstract Resume[] getAllResumes();

    protected abstract void deleteResume(String uuid);

    protected abstract Resume getResume(String uuid);
}
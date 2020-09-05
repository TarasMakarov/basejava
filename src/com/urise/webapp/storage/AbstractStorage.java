package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        Object searchKey = getSearchKey(resume.getUuid());
        updateResume(resume, searchKey);
    }

    public void save(Resume resume) {
        Object searchKey = getSearchKey(resume.getUuid());
        saveResume(resume, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getSearchKey(uuid);
        deleteResume(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getSearchKey(uuid);
        return getResume(searchKey);
    }

    protected Object getSearchKey(String uuid) {
        Object searchKey = existResume(uuid);
        return searchKey;
    }

    protected abstract Object existResume(String uuid);

    protected abstract void updateResume(Resume resume, Object searchKey);

    protected abstract void saveResume(Resume resume, Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract Resume getResume(Object searchKey);
}
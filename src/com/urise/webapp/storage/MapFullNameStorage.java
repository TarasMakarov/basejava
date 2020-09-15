package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapFullNameStorage extends AbstractStorage {

    private final Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    public int size() {
        return mapStorage.size();
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        ArrayList<Resume> resumes = new ArrayList<>(mapStorage.values());
        resumes.sort(fullNameCompare);
        return resumes;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        mapStorage.put((String) searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        mapStorage.remove();
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return mapStorage.get(searchKey);
    }

    protected boolean isExist(Object searchKey) {
        return mapStorage.containsKey(searchKey);
    }

    protected Resume getSearchKey(String uuid) {
        Resume resume = new Resume(uuid);
        return resume;
    }
}
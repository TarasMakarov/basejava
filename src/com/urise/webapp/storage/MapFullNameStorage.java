package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapFullNameStorage extends AbstractStorage {

    private final Map<Resume, String> mapStorage = new HashMap<>();

    @Override
    public int size() {
        return mapStorage.size();
    }

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    final public List<Resume> convertToArraylist() {
        return new ArrayList<>(mapStorage.keySet());
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        mapStorage.put((Resume) searchKey, r.getFullName());
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        mapStorage.put((Resume) searchKey, r.getFullName());
    }

    @Override
    protected void doDelete(Object searchKey) {
        mapStorage.remove(searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    protected boolean isExist(Object searchKey) {
        return mapStorage.containsKey(searchKey);
    }

    protected Resume getSearchKey(String uuid) {
        return new Resume(uuid);
    }
}
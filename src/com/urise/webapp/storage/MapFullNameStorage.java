package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapFullNameStorage extends AbstractStorage {

    private final Map<Resume, String> storageMap = new HashMap<>();

    @Override
    public int size() {
        return storageMap.size();
    }

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    final public List<Resume> convertArrayToList() {
        return new ArrayList<>(storageMap.keySet());
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storageMap.put((Resume) searchKey, r.getFullName());
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storageMap.put((Resume) searchKey, r.getFullName());
    }

    @Override
    protected void doDelete(Object searchKey) {
        storageMap.remove(searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    protected boolean isExist(Object searchKey) {
        return storageMap.containsKey(searchKey);
    }

    protected Resume getSearchKey(String uuid) {
        return new Resume(uuid);
    }
}
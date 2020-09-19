package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapFullNameStorage extends AbstractStorage {

    private final Map<String, Resume> storageMap = new HashMap<>();

    @Override
    public int size() {
        return storageMap.size();
    }

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    final public List<Resume> convertToList() {
        return new ArrayList<>(storageMap.values());
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storageMap.put(r.getUuid(), r);
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
        return searchKey != null;
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storageMap.get(uuid);
    }
}

package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {

    private final Map<String, Resume> storageMap = new HashMap<>();

    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Resume r, String searchKey) {
        storageMap.replace(searchKey, r);
        //        map.put(uuid, r);
    }

    protected boolean isExist(String searchKey) {
        return storageMap.containsKey(searchKey);
    }

    @Override
    protected void doSave(Resume r, String searchKey) {
        storageMap.put(searchKey, r);
    }

    @Override
    protected Resume doGet(String searchKey) {
        return storageMap.get(searchKey);
    }

    @Override
    protected void doDelete(String searchKey) {
        storageMap.remove(searchKey);
    }

    @Override
    public void clear() {
        storageMap.clear();
    }

    @Override
    public int size() {
        return storageMap.size();
    }

    @Override
    public List<Resume> convertToList() {
        return new ArrayList<>(storageMap.values());
    }
}
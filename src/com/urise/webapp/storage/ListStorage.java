package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storageList = new ArrayList<>();

    @Override
    public int size() {
        return storageList.size();
    }

    @Override
    public void clear() {
        storageList.clear();
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storageList.set((Integer) searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storageList.add(r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storageList.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storageList.get((Integer) searchKey);
    }

    @Override
    final public List<Resume> convertToList() {
        ArrayList<Resume> copyStorageList = new ArrayList<>(storageList);
        return new ArrayList<>(copyStorageList);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storageList.size(); i++) {
            if (storageList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
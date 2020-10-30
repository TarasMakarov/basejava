package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage<Integer> {

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
    protected void doUpdate(Resume r, Integer searchKey) {
        storageList.set((Integer) searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Integer searchKey) {
        storageList.add(r);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        storageList.remove(searchKey.intValue());
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return storageList.get((Integer) searchKey);
    }

    @Override
    final public List<Resume> convertToList() {
        return new ArrayList<>(storageList);
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

    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }
}
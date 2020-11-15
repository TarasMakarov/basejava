package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private final Map<String, Resume> storageMap = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return storageMap.get(uuid);
    }

    @Override
    protected void doUpdate(Resume r, Resume searchKey) {
        storageMap.replace(r.getUuid(), r);
        //        map.put(r.getUuid(), r);
    }

    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doSave(Resume r, Resume searchKey) {
        storageMap.put(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Resume searchKey) {
        return searchKey;
    }

    @Override
    protected void doDelete(Resume searchKey) {
        storageMap.remove(searchKey.getUuid());
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
    final public List<Resume> copyAllResume() {
        return new ArrayList<>(storageMap.values());
    }
}
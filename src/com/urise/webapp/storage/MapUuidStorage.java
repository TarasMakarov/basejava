
package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {

    private final Map<String, Resume> mapUuidStorage = new HashMap<>();

    @Override
    public int size() {
        return mapUuidStorage.size();
    }

    @Override
    public void clear() {
        mapUuidStorage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        ArrayList<Resume> resumes = new ArrayList<>(mapUuidStorage.values());
        Collections.sort(resumes);
        return resumes;
    }

//    @Override
//    public Resume[] getAll() {
//        ArrayList<Resume> resumes = new ArrayList<>(mapStorage.values());
//        Resume[] allResumes = resumes.toArray(new Resume[mapStorage.size()]);
//        Arrays.sort(allResumes);
//        return allResumes;
//    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        mapUuidStorage.put((String) searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        mapUuidStorage.put((String) searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        mapUuidStorage.remove(searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return mapUuidStorage.get(searchKey);
    }

    protected boolean isExist(Object searchKey) {
        return mapUuidStorage.containsKey(searchKey);
    }

    protected String getSearchKey(String uuid) {
        return uuid;
    }
}
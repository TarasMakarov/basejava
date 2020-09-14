package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapFullNameStorage extends AbstractStorage {

    private final Map<String, Resume> mapFullNameStorage = new HashMap<>();

    @Override
    public int size() {
        return mapFullNameStorage.size();
    }

    @Override
    public void clear() {
        mapFullNameStorage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        ArrayList<Resume> resumes = new ArrayList<>(mapFullNameStorage.values());
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
        mapFullNameStorage.put((String) searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        mapFullNameStorage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        mapFullNameStorage.remove();
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return mapFullNameStorage.get(searchKey);
    }

    protected boolean isExist(Object searchKey) {
        return mapFullNameStorage.containsKey(searchKey);
    }

    protected Resume getSearchKey(String uuid) {
        Resume resume = new Resume(uuid);
        return resume;
    }
}
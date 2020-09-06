
package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

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
    public Resume[] getAll() {
        ArrayList<Resume> resumes = new ArrayList<>(mapStorage.values());
        Resume[] allResumes = resumes.toArray(new Resume[mapStorage.size()]);
        Arrays.sort(allResumes);
        return allResumes;
    }

    @Override
    protected void updateResume(Resume r, Object searchKey) {
        mapStorage.put((String) searchKey, r);
    }

    @Override
    protected void saveResume(Resume r, Object searchKey) {
        mapStorage.put((String) searchKey, r);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        mapStorage.remove(searchKey);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return mapStorage.get(searchKey);
    }

    protected  boolean findResume(Object searchKey) {
        if(searchKey != null) {
            return true;
        }
        return false;
    }

    protected String getSearchKey(String uuid) {
        if(mapStorage.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }
}
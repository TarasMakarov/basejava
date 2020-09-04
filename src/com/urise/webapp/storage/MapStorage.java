
package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
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
    protected void updateResume(Resume r) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    protected void saveResume(Resume r) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    protected void deleteResume(String uuid) {
        mapStorage.remove(uuid);
    }

    @Override
    protected Resume getResume(String uuid) {
        return mapStorage.get(uuid);
    }

    protected boolean exist(String uuid) {
        if(mapStorage.containsKey(uuid)) {
            throw new ExistStorageException(uuid);
        }
            return false;
    }

    protected boolean notExist(String uuid) {
        if(!mapStorage.containsKey(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return false;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        if(mapStorage.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }
}
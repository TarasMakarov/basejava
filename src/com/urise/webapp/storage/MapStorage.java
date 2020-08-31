
package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    Map<String, Resume> mapResume = new HashMap<>();

    @Override
    public int size() {
        return mapResume.size();
    }

    @Override
    public void clear() {
        mapResume.clear();
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    protected void updateResume(Resume r) {
        mapResume.put(r.getUuid(), r);
    }

    @Override
    protected void saveResume(Resume r) {
        mapResume.put(r.getUuid(), r);
    }

    @Override
    protected void deleteResume(String uuid) {
        mapResume.remove(uuid);
    }

    @Override
    protected Resume getResume(String uuid) {
        return mapResume.get(uuid);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        if(mapResume.containsKey(uuid)) {
            return 1;
        }
        return -1;
    }

    protected boolean NotExist(String uuid) {
        if ((int) getSearchKey(uuid) < 0) {
            throw new NotExistStorageException(uuid);
        }
        return true;
    }

    protected boolean Exist(String uuid) {
        if ((int) getSearchKey(uuid) > 0) {
            throw new ExistStorageException(uuid);
        }
        return true;
    }
}
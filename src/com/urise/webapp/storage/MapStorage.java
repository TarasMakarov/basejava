package com.urise.webapp.storage;

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

//    @Override
//    public Resume[] getAll() {
//        return ;
//    }

    @Override
    protected int getIndex(String uuid) {
        if (mapResume.containsKey(uuid)) {
            return 0;
        }
        return -1;
    }

    @Override
    protected void updateResume(Object o, Resume r) {
        o  = null;
        mapResume.put(r.getUuid(), r);
    }

    @Override
    protected void saveResume(Resume r, Object o) {
        mapResume.put((String) o, r);
    }

//    @Override
//    protected void deleteResume(Object o) {
//        mapResume.remove(o);
//    }

//    @Override
//    protected Resume getResume(Object o) {
//        return mapResume.get(o);
//    }
}
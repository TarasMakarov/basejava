package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;

public class MapStorage extends AbstractStorage {

    HashMap<String, String> mapResume = new HashMap<>();

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
    protected int getIndex(String uuid) {
        return 0;
    }

    @Override
    protected void updateResume(Resume r) {
//        mapResume.containsKey(r);
    }

    @Override
    protected void saveResume(Resume r) {

    }

    @Override
    protected void deleteResume(String uuid) {

    }

    @Override
    protected Resume getResume(String uuid) {
//        Resume findResume = new Resume(uuid);
        return null;
    }
}
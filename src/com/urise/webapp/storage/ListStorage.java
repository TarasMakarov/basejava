package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> listResume = new ArrayList<>();

    @Override
    public int size() {
        return listResume.size();
    }

    @Override
    public void clear() {
        listResume.clear();
    }

    @Override
    protected void updateResume(Object o, Resume r) {
        listResume.set((int) o, r);
    }

    @Override
    public Resume[] getAll() {
        Resume[] allResumes = new Resume[listResume.size()];
        return listResume.toArray(allResumes);
    }

    @Override
    protected void saveResume(Resume r, Object o) {
        o = null;
        listResume.add(r);
    }

    @Override
    protected void deleteResume(Object o) {
        listResume.remove((int)o);
    }

    @Override
    protected Resume getResume(Object o) {
        return listResume.get((int) o);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume[] allResumes = new Resume[listResume.size()];
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(allResumes, 0, listResume.size(), searchKey);
    }
}
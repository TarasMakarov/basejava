package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private List<Resume> listResume = new LinkedList<>();

    @Override
    public int size() {
        return listResume.size();
    }

    @Override
    public void clear() {
        listResume.clear();
    }

    @Override
    protected void updateResume(Resume r) {
        listResume.set(index, r);
    }

    @Override
    public Resume[] getAll() {
        Resume[] allResumes = new Resume[listResume.size()];
        return listResume.toArray(allResumes);
    }

    @Override
    protected void saveResume(Resume r) {
        listResume.add(r);
    }

    @Override
    protected void deleteResume(String uuid) {
        Resume eraseResume = new Resume(uuid);
        listResume.remove(eraseResume);
    }

    @Override
    protected Resume getResume(String uuid) {
        return listResume.get(index);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume[] allResumes = new Resume[listResume.size()];
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(allResumes, 0, listResume.size(), searchKey);
    }
}
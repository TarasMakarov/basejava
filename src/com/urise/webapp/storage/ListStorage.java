package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> listResume = new ArrayList<>();

    @Override
    public int size() {
        return listResume.size();
    }

    @Override
    public void clear() {
        listResume.clear();
    }

    @Override
    protected void updateResume(Resume r, Object searchKey) {
        listResume.set((int)searchKey, r);
    }

    @Override
    public Resume[] getAll() {
        Resume[] allResumes = new Resume[listResume.size()];
        return listResume.toArray(allResumes);
    }

    @Override
    protected void saveResume(Resume r, Object searchKey) {
        if((int)searchKey > -1) {
            throw new ExistStorageException(r.getUuid());
        }
        listResume.add(r);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        listResume.remove((int)searchKey);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return listResume.get((int)searchKey);
    }

    @Override
    protected Object existResume(String uuid) {
        int searchKey = findResume(uuid);
        return searchKey;
    }

    private Integer findResume(String uuid) {
        Resume[] allResumes = listResume.toArray(new Resume[listResume.size()]);
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(allResumes, 0, listResume.size(), searchKey);
    }
}
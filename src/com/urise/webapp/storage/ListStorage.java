package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
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
    protected void updateResume(Resume r) {
        listResume.set((int)getSearchKey(r.getUuid()), r);
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
        listResume.remove((int)getSearchKey(uuid));
    }

    @Override
    protected Resume getResume(String uuid) {
        return listResume.get((Integer) getSearchKey(uuid));
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Resume[] allResumes = new Resume[listResume.size()];
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(allResumes, 0, listResume.size(), searchKey);
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
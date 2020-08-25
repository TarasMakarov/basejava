package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class ListStorage extends AbstractStorage {

    LinkedList <Resume>listResume = new LinkedList<>();

    @Override
    protected int sizeStorage() {
        return listResume.size();
    }

    @Override
    protected void clearStorage() {
        listResume.clear();
    }

    @Override
    public void updateResume(Resume r) {
        if (listResume.contains(r)) {
            int index = listResume.indexOf(r);
            listResume.set(index, r);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    @Override
    public Resume[] getAllResumes() {
        Resume[] allResumes = new Resume[listResume.size()];
        return listResume.toArray(allResumes);
    }


    @Override
    public void saveResume(Resume r) {
        int index = getIndex(r.getUuid());
        if (listResume.contains(r)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            insertElement(r, index);
        }
    }

    @Override
    public void deleteResume(String uuid) {
        Resume eraseResume = new Resume(uuid);
        if (listResume.contains(eraseResume)) {
            listResume.remove(eraseResume);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public Resume getResume(String uuid) {
        Resume requestResume = new Resume(uuid);
        if (listResume.contains(requestResume)) {
            return requestResume;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private int getIndex(String uuid) {
        Resume[] allResumes = new Resume[listResume.size()];
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(allResumes, 0, listResume.size(), searchKey);
    }

    private void insertElement(Resume r, int index) {
//      http://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array#answer-36239
        Resume[] allResumes = new Resume[listResume.size()];
        int insertIdx = -index - 1;
        System.arraycopy(allResumes, insertIdx, allResumes, insertIdx + 1, listResume.size() - insertIdx);
        allResumes[insertIdx] = r;
        listResume.clear();
        Collections.addAll(listResume, allResumes);
    }
}
package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class ListStorage extends AbstractStorage {

    LinkedList<Resume> listResume = new LinkedList<>();

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
        int index = getIndex(r.getUuid());
        listResume.set(index, r);
    }

    @Override
    public Resume[] getAll() {
        Resume[] allResumes = new Resume[listResume.size()];
        return listResume.toArray(allResumes);
    }

    @Override
    protected void saveResume(Resume r) {
        int index = getIndex(r.getUuid());
        insertElement(r, index);
    }

    @Override
    protected void deleteResume(String uuid) {
        Resume eraseResume = new Resume(uuid);
        listResume.remove(eraseResume);
    }

    @Override
    protected Resume getResume(String uuid) {
        int index = getIndex(uuid);
        return listResume.get(index);
    }

    @Override
    protected int getIndex(String uuid) {
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
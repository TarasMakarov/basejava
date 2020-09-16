package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

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
    protected void doUpdate(Resume r, Object searchKey) {
        listResume.set((Integer) searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        listResume.add(r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        listResume.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return listResume.get((Integer) searchKey);
    }

    protected Integer getSearchKey(String uuid) {
        Resume[] allResumes = listResume.toArray(new Resume[listResume.size()]);
        Arrays.sort(allResumes);
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(allResumes, 0, listResume.size(), searchKey);
    }

    @Override
    final public List <Resume> convertToArraylist() {
        return listResume;
    }

//    @Override
//    protected Integer getSearchKey(String uuid) {
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getUuid().equals(uuid)) {
//                return i;
//            }
//        }
//        return null;
//    }

    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
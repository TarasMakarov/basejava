package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    static final Comparator<Resume> fullNameCompare = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

//    static class SortedByFullName implements Comparator<Resume> {
//
//        @Override
//        public int compare(Resume r1, Resume r2) {
//
//            if(r1.getFullName().compareTo(r2.getFullName()) == 0) {
//                return r1.getUuid().compareTo(r2.getUuid());
//            }
//            return r1.getFullName().compareTo(r2.getFullName());
//        }
//    }
//
//    SortedByFullName fullNameCompare = new SortedByFullName();
//
//    static class SortedByUuid implements Comparator<Resume> {
//
//        @Override
//        public int compare(Resume r1, Resume r2) {
//                return r1.getUuid().compareTo(r2.getUuid());
//            }
//    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract List<Resume> convertToArraylist();

    public List<Resume> getAllSorted() {
        List <Resume> list = convertToArraylist();
        list.sort(fullNameCompare);
        return list;
    }

    public void update(Resume resume) {
        Object searchKey = getSearchKeyIfExist(resume.getUuid()); // analog getExistedSearchKey()
        doUpdate(resume, searchKey);
    }

    public void save(Resume resume) {
        Object searchKey = existStorageException(resume.getUuid()); // analog getNotExistedSearchKey
        doSave(resume, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = getSearchKeyIfExist(uuid);
        doDelete(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = getSearchKeyIfExist(uuid);
        return doGet(searchKey);
    }

    private Object existStorageException(String uuid) { // analog getNotExistedSearchKey
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getSearchKeyIfExist(String uuid) {  // analog getExistedSearchKey
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}
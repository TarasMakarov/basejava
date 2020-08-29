package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

//    private Object searchKey(Object o) {
//        Object searchKey = o;
//        return searchKey;
//    }

    @Override
    final public int size() {
        return size;
    }

    @Override
    final public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    final public void updateResume(Object o, Resume r) {
        storage[(int)o] = r;
    }

    /**
     * @return
     */
    @Override
    final public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    final public void saveResume(Resume r, Object o) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, (int)o);
            size++;
        }
    }

    @Override
    final public void deleteResume(Object o) {
        fillDeletedElement((int)o);
        storage[size - 1] = null;
        size--;
    }

    @Override
    final public Resume getResume(Object o) {
        return storage[(int)o];
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);
}
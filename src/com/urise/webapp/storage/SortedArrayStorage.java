package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void entryResume(Resume r) {
        storage[Math.abs(getIndex(r.getUuid())) - 1] = r;
    }

    @Override
    protected void eraseResume(String uuid) {
        for (int i = getIndex(uuid); i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
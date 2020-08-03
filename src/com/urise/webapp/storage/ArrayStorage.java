package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int sizeStorage = 0;
    private int indexResume;

    public void clear() {
        Arrays.fill(storage, 0, sizeStorage, null);
        sizeStorage = 0;
    }

    public void update(Resume resume) {
        if (findResume(resume.getUuid())) {
            storage[indexResume] = resume;
        } else {
            System.out.println("Resume " + resume.getUuid() + " is not in the database.");
        }
    }

    public void save(Resume r) {
        if (findResume(r.getUuid())) {
            System.out.println("Resume " + r.getUuid() + " is already in the database.");
        } else if (sizeStorage < storage.length) {
            storage[sizeStorage] = r;
            sizeStorage++;
        } else {
            System.out.println("Database is full.");
        }
    }

    public Resume get(String uuid) {
        if (findResume(uuid)) {
            return storage[indexResume];
        }
        System.out.println("Resume " + uuid + " is not in the database.");
        return null;
    }

    public void delete(String uuid) {
        if (findResume(uuid)) {
            storage[indexResume] = storage[sizeStorage - 1];
            storage[sizeStorage - 1] = null;
            sizeStorage--;
        } else {
            System.out.println("Resume " + uuid + " is not in the database.");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, sizeStorage);
    }

    public int size() {
        return sizeStorage;
    }

    private boolean findResume(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                indexResume = i;
                return true;
            }
        }
        return false;
    }
}

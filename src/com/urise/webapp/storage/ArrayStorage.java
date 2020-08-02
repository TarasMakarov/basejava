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
        Arrays.fill(storage, 0, sizeStorage - 1, null);
        sizeStorage = 0;
    }

    public void update(Resume resume) {
        if (resumeFind(resume.getUuid())) {
            storage[indexResume] = resume;
        } else {
            System.out.println("Resume is not in the database.");
        }
    }

    public void save(Resume r) {
        if (resumeFind(r.getUuid())) {
            System.out.println("Resume is already in the database.");
        } else {
            if (sizeStorage < storage.length) {
                storage[sizeStorage] = r;
                sizeStorage++;
            } else {
                System.out.println("Database is full.");
            }
        }
    }

    public Resume get(String uuid) {
        if (resumeFind(uuid)) {
            return storage[indexResume];
        } else {
            System.out.println("Resume is not in the database.");
        }
        return null;
    }

    public void delete(String uuid) {
        if (resumeFind(uuid)) {
            storage[indexResume] = storage[sizeStorage - 1];
            storage[sizeStorage - 1] = null;
            sizeStorage--;
        } else {
            System.out.println("Resume is not in the database.");
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

    private boolean resumeFind(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                indexResume = i;
                return true;
            }
        }
        return false;
    }
}

package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[3];
    private int sizeStorage = 0;

    public void clear() {
        for (int i = 0; i < sizeStorage; i++) {
            storage[i] = null;
        }
        sizeStorage = 0;
    }

    public void update() {

    }

    public void save(Resume r) {
        if (sizeStorage < storage.length) {
            storage[sizeStorage] = r;
            sizeStorage++;
        } else {
            System.out.println("Нельзя добавить. База резюме заполнена.");
        }
    }

    public Resume get(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                int indexDel = i;
                storage[indexDel] = storage[sizeStorage - 1];
                storage[sizeStorage - 1] = null;
                sizeStorage--;
                break;
            }
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
}

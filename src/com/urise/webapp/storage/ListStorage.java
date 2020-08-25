package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {

    ArrayList<Resume> listResume = new ArrayList<>();

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
        if (listResume.contains(r)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            listResume.add(r);
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
}
package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {

    protected Storage storage;

    protected static final String UUID_1 = "uuid1";
    protected static final String UUID_2 = "uuid2";
    protected static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(storage.get(UUID_1));
        Resume[] arrayResume = storage.getAll();
        Assert.assertEquals(3, storage.size());
        Assert.assertEquals(arrayResume[0], storage.get(UUID_1));
        Assert.assertEquals(arrayResume[1], storage.get(UUID_2));
        Assert.assertEquals(arrayResume[2], storage.get(UUID_3));
    }

    @Test
    public void getAll() {
        Resume[] actualResumes = storage.getAll();
        Resume[] expectedResumes = new Resume[]{storage.get(UUID_1), storage.get(UUID_2), storage.get(UUID_3)};
        Assert.assertEquals(3, storage.size());
        Assert.assertArrayEquals(expectedResumes, actualResumes);
    }

    @Test
    public void save() {
        Assert.assertEquals(3, storage.size());
        Resume[] arrayResume = storage.getAll();
        Assert.assertEquals(arrayResume[0], storage.get(UUID_1));
        Assert.assertEquals(arrayResume[1], storage.get(UUID_2));
        Assert.assertEquals(arrayResume[2], storage.get(UUID_3));
    }

    @Test (expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test
    public void get() {
        Resume[] arrayResume = storage.getAll();
        Assert.assertEquals(arrayResume[0], storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = ExistStorageException.class)
    public void getExist() throws Exception {
        storage.save(storage.get(UUID_1));
    }

//    @Test(expected = RuntimeException.class)
    @Test
    public void getStorageException() throws RuntimeException {
        Resume[] arrayResume = new Resume[storage.size() - 1];
        Resume[] arrayResume2 = storage.getAll();
        try {
            for (int i = 0; i < storage.size(); i++) {
                arrayResume[i] = arrayResume2[i];
            }
            Assert.fail("Storage overflow");
        } catch (RuntimeException thrown) {
            Assert.assertNotEquals("", thrown.getMessage());
        }
    }
}
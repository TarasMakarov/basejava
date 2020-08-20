package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
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
        Resume resumeNew = new Resume(UUID_1);
        storage.update(resumeNew);
        Assert.assertEquals(3, storage.size());
        Assert.assertEquals(storage.get(UUID_1), resumeNew);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNothing() {
        storage.delete(UUID_1);
        storage.update(storage.get(UUID_1));
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
        storage.save(new Resume("uuid4"));
        Resume[] arrayResume = storage.getAll();
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(arrayResume[3], storage.get("uuid4"));
    }

    @Test(expected = ExistStorageException.class)
    public void duplicateResume() {
        storage.save(storage.get(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void checkArrayOverflow() {
        try {
            for (int i = 3; i < 10000; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException exception) {
            Assert.fail("Storage overflow");
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNothing() {
        storage.delete("dummy");
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
}
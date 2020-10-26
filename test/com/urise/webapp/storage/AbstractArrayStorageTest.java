package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("fullName"));
//                storage.save(new Resume("Name" + i));
            }
        } catch (StorageException exception) {
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        storage.save(new Resume("Overflow"));
    }
}
package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Assert;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    protected void compareCellsResume() {
        Resume[] arrayResume = storage.getAll();
        Assert.assertEquals(storage.get(UUID_2), arrayResume[1]);
        Assert.assertEquals(storage.get(UUID_3), arrayResume[0]);
    }
}
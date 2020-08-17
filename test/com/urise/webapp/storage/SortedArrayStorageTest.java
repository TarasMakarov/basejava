package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Assert;

import static org.junit.Assert.*;

public class SortedArrayStorageTest extends AbstractArrayStorageTest{

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    protected void compareCellsResume() {
        Resume[] arrayResume = storage.getAll();
        Assert.assertEquals(new Resume(UUID_2), arrayResume[0]);
        Assert.assertEquals(new Resume(UUID_3), arrayResume[1]);
    }
}
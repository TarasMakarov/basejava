package com.urise.webapp.storage;

import com.urise.webapp.storage.saver.ObjectSaver;

public class ObjectStreamPathStorageTest extends AbstractStorageTest{

    public ObjectStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectSaver()));
    }
}
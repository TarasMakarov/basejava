package com.urise.webapp.storage;

public class ObjectStreamStorageTest extends AbstractStorageTest{

    public ObjectStreamStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectToKeep()));
    }
}
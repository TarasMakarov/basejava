package com.urise.webapp.storage;

public class ObjectStreamPathStorageTest extends AbstractStorageTest{

    public ObjectStreamPathStorageTest() {
        super(new AbstractPathStorage(STORAGE_PATH,  new ObjectToKeep()));
    }
}
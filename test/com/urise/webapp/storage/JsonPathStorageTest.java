package com.urise.webapp.storage;

import com.urise.webapp.storage.saver.JsonStreamSaver;

public class JsonPathStorageTest extends AbstractStorageTest{

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSaver()));
    }
}
package com.urise.webapp.storage;

import com.urise.webapp.storage.saver.DataStreamSaver;

public class DataFileStorageTest extends AbstractStorageTest {
    public DataFileStorageTest() {
        super(new FileStorage(STORAGE_DIR, new DataStreamSaver()));
    }
}

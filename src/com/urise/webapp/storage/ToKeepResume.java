package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ToKeepResume {

    void doWrite(Resume r, OutputStream os) throws IOException;
    
    void doRead(InputStream is) throws IOException;
}
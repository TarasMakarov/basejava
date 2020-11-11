package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.io.OutputStream;

public class StrategyClient {

    ToKeepResume option;

    public void setOption(ToKeepResume option) {
        this.option = option;
    }

    public void chooseOption (Resume r, OutputStream os) {
        option.doWrite(r, os);
    }
}
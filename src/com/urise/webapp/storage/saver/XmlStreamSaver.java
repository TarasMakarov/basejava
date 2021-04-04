package com.urise.webapp.storage.saver;

import com.urise.webapp.model.*;
import com.urise.webapp.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlStreamSaver implements Saver {
    private XmlParser xmlParser;

    public XmlStreamSaver() {
        xmlParser = new XmlParser(
                Resume.class, Organization.class, Link.class,
                OrganizationSection.class, SimpleTextSection.class, BulletListSection.class, Organization.Experience.class);
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(r, w);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r);
        }
    }
}
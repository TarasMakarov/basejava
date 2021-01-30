package com.urise.webapp.util;

import com.urise.webapp.model.AbstractSection;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SimpleTextSection;
import org.junit.Assert;
import org.junit.Test;

import static com.urise.webapp.TestData.*;

public class JsonParserTest {

    @Test
    public void testResume() throws Exception {
        String json = JsonParser.write(RESUME_2);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        Assert.assertEquals(RESUME_2, resume);
    }

    @Test
    public void read() {
    }

    @Test
    public void write() {
        AbstractSection section1 = new SimpleTextSection("Simple clever");
        String json = JsonParser.write(section1, AbstractSection.class);
        System.out.println(json);
        AbstractSection section2 = JsonParser.read(json, AbstractSection.class);
        Assert.assertEquals(section1, section2);
    }
}
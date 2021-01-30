package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.util.UUID;

import static com.urise.webapp.ResumeTestData.fillResume;

public class TestData {

    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final Resume RESUME_1 = new Resume(UUID_1, "Name1");

    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final Resume RESUME_2 = fillResume(UUID_2, "Name2");

    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final Resume RESUME_3 = fillResume(UUID_3, "Name3");

    public static final String UUID_4 = UUID.randomUUID().toString();
    public static final Resume RESUME_4 = fillResume(UUID_4, "Name4");
}
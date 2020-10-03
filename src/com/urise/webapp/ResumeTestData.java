package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;

public class ResumeTestData {
    public static void main(String[] args) {

        Resume gKislin = new Resume("Григорий Кислин");

        System.out.println(gKislin);

        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle());
        }

    }
}

//public class TestSingleton {
//    private static TestSingleton instance;
//
//    public static TestSingleton getInstance() {
//        if (instance == null) {
//            instance = new TestSingleton();
//        }
//        return instance;
//    }
//
//    private TestSingleton() {
//    }
//
//    public static void main(String[] args) {
//        TestSingleton.getInstance().toString();
//        Singleton instance = Singleton.valueOf("INSTANCE");
//        System.out.println(instance.ordinal());
//
//        for (SectionType type : SectionType.values()) {
//            System.out.println(type.getTitle());
//        }
//    }
//
//    public enum Singleton {
//        INSTANCE
//    }
//}
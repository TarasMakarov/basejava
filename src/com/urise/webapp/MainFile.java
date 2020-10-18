package com.urise.webapp;

import java.io.File;
import java.io.IOException;

public class MainFile {

    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }


        File dir = new File("./src/com/urise/webapp");
//        System.out.println(dir.isDirectory());
//        System.out.println(dir.getParentFile());
//        File[] allDir = new File[0];
//        System.out.println(allDir);
        String[] list = dir.list();
        if (list != null) {
//            for (int i = 0; i < list.length; i++) {
//                File filesDir = new File("./src/com/urise/webapp", list[i]);
//                if(filesDir.isDirectory()) {
//                System.out.println(list[i]);
//                System.out.println(filesDir.isDirectory());
//                System.out.println(filesDir.getName());
                }
            for (String name : list) {
                File filesDir = new File("./src/com/urise/webapp", name);
                System.out.println(name);
                if(filesDir.isDirectory()) {
                    String[] listDir = filesDir.list();
                    for (String nameDir : listDir) {
                        System.out.println(nameDir);
                    }
            }
        }
//        for (File files : allDir) {
//            if (files.isDirectory()) {
//                String[] list = dir.list();
//                if (list != null) {
//                    for (String name : list) {
//                        System.out.println(name);
//                    }
//                }
//            }
//        }


//        try (FileInputStream fis = new FileInputStream(filePath)) {
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
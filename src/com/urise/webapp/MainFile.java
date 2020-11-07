package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainFile {

    public static void main(String[] args) {
        String filePath = ".gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }


        String dirPath = "./src/com/urise/webapp";
        File fileOrDir = new File(dirPath);

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        workWithFile(fileOrDir, "");
    }

    static String space = "";

    static void workWithFile(File fileOrDir, String gap) {

        File[] files = fileOrDir.listFiles();
        if (files != null) {
            Arrays.sort(files);
            List<File> onlyFiles = new ArrayList<>();
            List<File> onlyDir = new ArrayList<>();
            List<File> filesAndDir = new ArrayList<>();

            for (File file : files) {
                if (file.isDirectory()) {
                    onlyDir.add(file);
                } else if (file.isFile()) {
                    onlyFiles.add(file);
                }
            }

            filesAndDir.addAll(onlyDir);
            filesAndDir.addAll(onlyFiles);

            space = space + gap;

            for (File file : filesAndDir) {
                if (onlyDir.isEmpty()) {
                    System.out.println(space + space + gap + file.getName());
                } else {
                    if (file.isFile()) {
                        System.out.println(gap + file.getName());
                    } else if (file.isDirectory()) {
                        System.out.println(gap + file.getName());
                        space = gap;
                        workWithFile(file, " ");
                    }
                }
            }
        }
    }
}
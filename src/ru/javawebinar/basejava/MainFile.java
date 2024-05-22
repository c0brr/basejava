package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) throws IOException {
//        String filePath = ".\\.gitignore";
//        File file = new File(filePath);
//        try {
//            System.out.println(file.getCanonicalFile());
//        } catch (IOException e) {
//            throw new RuntimeException("Error", e);
//        }
//        File dir = new File(".\\src\\ru\\javawebinar\\basejava");
//        System.out.println(dir.isDirectory());
//        String[] list = dir.list();
//        if (list != null) {
//            for (String name : list) {
//                System.out.println(name);
//            }
//        }
//
//        try (FileInputStream fis = new FileInputStream(filePath)) {
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        recursion(new File(".").getCanonicalFile());
    }

    public static void recursion(File file) throws IOException {
        if (file.isDirectory()) {
            File parentFile = file.getParentFile();
            System.out.println((parentFile != null ? "(" + parentFile.getName() + ")" : "") + file.getName() + ": ");
            String[] fileList = file.list();
            if (fileList != null) {
                for (String string : fileList) {
                    recursion(new File(file.getCanonicalPath(), string));
                }
            }
        } else {
            System.out.println("(file) " + file.getName());
        }
    }
}
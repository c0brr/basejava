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
        printDeepDirectory(new File(".").getCanonicalFile(),
                true, true, new StringBuilder());
    }

    public static void printDeepDirectory(File file, boolean isRootDirectory, boolean isLastIndex, StringBuilder sb)
            throws IOException {
        System.out.println(sb + (!isRootDirectory ? (isLastIndex ? "└───" : "├───") : "") + file.getName());
        if (file.isDirectory()) {
            String[] fileList = file.list();
            if (fileList != null) {
                for (int i = 0; i < fileList.length; i++) {
                    if (!isRootDirectory) {
                        sb.append(isLastIndex ? "    " : "│   ");
                    }
                    printDeepDirectory(new File(file.getCanonicalPath(), fileList[i]), false,
                            i == fileList.length - 1, sb);
                    if (sb.length() >= 4) {
                        sb.delete(sb.length() - 4, sb.length());
                    }
                }
            }
        }
    }
}
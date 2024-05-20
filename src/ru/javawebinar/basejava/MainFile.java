package ru.javawebinar.basejava;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
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

        recursion(new File("C:\\My Files\\Java\\BaseJava\\basejava"));
    }

    public static void recursion(File file) {
        if (file.isDirectory()) {
            System.out.println("(" + file.getParentFile().getName() + ")" + file.getName() + ": ");
            for (String string : file.list()) {
                recursion(new File(file.getAbsolutePath() + "\\" + string));
            }
        } else {
            System.out.println("--" + file.getName());
        }
    }
}
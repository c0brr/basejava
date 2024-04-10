package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ListStorage;
import ru.javawebinar.basejava.storage.Storage;

/**
 * Test ru.javawebinar.basejava.storage.ArrayStorage
 */
public class MainTestArrayStorage {
    private static final Storage ARRAY_STORAGE = new ListStorage();
    private static final String SOME_FULL_NAME = "Some fullName";

    public static void main(String[] args) {
        final Resume resume1 = new Resume("uuid1", SOME_FULL_NAME);
        final Resume resume2 = new Resume("uuid2", SOME_FULL_NAME);
        final Resume resume3 = new Resume("uuid3", SOME_FULL_NAME);
        final Resume resume4 = new Resume("uuid3", SOME_FULL_NAME);

        ARRAY_STORAGE.save(resume1);
        ARRAY_STORAGE.save(resume2);
        ARRAY_STORAGE.save(resume3);

        ARRAY_STORAGE.update(resume4);

        System.out.println("Get resume1: " + ARRAY_STORAGE.get(resume1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(resume1.toString());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    private static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}

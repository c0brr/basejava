package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int MAX_RESUMES = 10_000;
    private final Resume[] storage = new Resume[MAX_RESUMES];
    private int size;

    public int size() {
        return size;
    }

    public void save(Resume resume) {
        if (size < MAX_RESUMES) {
            for (int i = 0; i < size; i++) {
                if (resume.toString().equals(storage[i].toString())) {
                    System.out.println("Ошибка: резюме " + resume + " уже имеется в базе");
                    return;
                }
            }
            storage[size++] = resume;
            return;
        }
        System.out.println("Ошибка: хранилище заполнено");
    }

    public void update(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (resume.toString().equals(storage[i].toString())) {
                System.out.println("Введите обновленный uuid: ");
                Scanner scanner = new Scanner(System.in);
                String newUuid = null;
                boolean isUuidUnique = false;
                while (!isUuidUnique) {
                    newUuid = scanner.nextLine();
                    for (int j = 0; j < size; j++) {
                        if (storage[j].toString().equals(newUuid)) {
                            System.out.println("Резюме с uuid " + newUuid +
                                    " уже имеется в базе, введите другой uuid: ");
                            break;
                        }
                        if (j == size - 1) {
                            isUuidUnique = true;
                        }
                    }
                }
                storage[i].setUuid(newUuid);
                scanner.close();
                return;
            }
        }
        System.out.println("Ошибка: резюме " + resume + " не найдено");
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                size--;
                storage[i] = storage[size];
                storage[size] = null;
                return;
            }
        }
        printNotFound(uuid);
    }

    private void printNotFound(String uuid) {
        System.out.println("Ошибка: резюме " + uuid + " не найдено");
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        printNotFound(uuid);
        return null;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
}

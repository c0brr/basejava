import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int MAX_RESUMES = 10_000;
    private final Resume[] storage = new Resume[MAX_RESUMES];
    private int amountResumes;

    void clear() {
        Arrays.fill(storage, 0, amountResumes, null);
        amountResumes = 0;
    }

    void save(Resume resume) {
        storage[amountResumes++] = resume;
    }

    Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < amountResumes; i++) {
            if (storage[i].toString().equals(uuid)) {
                resume = storage[i];
            }
        }
        return resume;
    }

    void delete(String uuid) {
        for (int i = 0; i < amountResumes; i++) {
            if (storage[i].toString().equals(uuid)) {
                amountResumes--;
                if (i != amountResumes) {
                    System.arraycopy(storage, i + 1, storage, i, amountResumes - i);
                }
                storage[amountResumes] = null;
                return;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, amountResumes);
    }

    int size() {
        return amountResumes;
    }
}

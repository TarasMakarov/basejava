import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int sizeStorage = 0;

    void clear() {
        for (int i = 0; i < sizeStorage; i++) {
            storage[i] = null;
        }
        sizeStorage = 0;
    }

    void save(Resume r) {
        for (int i = 0; i < sizeStorage + 1; i++) {
            storage[sizeStorage] = r;
            sizeStorage++;
            break;
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int resumeDelete = 0;
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                sizeStorage--;
                break;
            }
            resumeDelete++;
        }
        int y = resumeDelete;
        for (int i = resumeDelete + 1; i < sizeStorage + 1; i++) {
                storage[y] = storage[i];
                y++;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] tempStorage = new Resume[sizeStorage];
        tempStorage = Arrays.copyOf(storage, sizeStorage);
        return tempStorage;
    }

    int size() {
        return sizeStorage;
    }
}
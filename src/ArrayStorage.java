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
        if (sizeStorage < storage.length) {
            storage[sizeStorage] = r;
            sizeStorage++;
        } else {
            System.out.println("Нельзя добавить. База резюме заполнена.");
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
        for (int i = 0; i < sizeStorage; i++) {
            if (storage[i].uuid.equals(uuid)) {
                int indexDel = i;
                sizeStorage--;
                storage[indexDel] = storage[sizeStorage];
                break;
            }
            if (i + 1 == sizeStorage) {
                System.out.println("Резюме: " + uuid + " в базе не найдено.");
            }
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, sizeStorage);
    }

    int size() {
        return sizeStorage;
    }
}
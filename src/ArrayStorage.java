import sun.security.util.ArrayUtil;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) { // очистить весь массив  Arrays.fill(storage, null);
            if (storage[i] != null) {
                storage[i] = null;
            }
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < storage.length; i++)
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            } else {
                Resume dummy = new Resume();
                dummy.uuid = uuid;
                return dummy;
            }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] tempStorage = new Resume[storage.length];
        int counterNull = 0; //переменная считает количество ссылок == null
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                counterNull += 1;
            } else {
                tempStorage[i - counterNull] = storage[i]; //копируем во временный массив все резюме подряд, все null идут после ссылок на резюме
            }
        }
        int counterNoNull = 0;
        for (int i = 0; i < tempStorage.length; i++) {
            if (tempStorage[i] != null) {
                counterNoNull++; //считаем количество ссылок != null
            }
        }
        tempStorage = Arrays.copyOfRange(tempStorage, 0, counterNoNull); //новый массив только с резюме (без null)
        return tempStorage;
    }

    int size() {
        int sizeStorage = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                sizeStorage += 1;
            }
        }
        return sizeStorage;
    }
}
package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lesson12 {
    public static void main(String[] args) {

        int[] intArr = new int[]{1, 2, 5, 5, 1, 8};
        minValue(intArr);
        List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(5);
        integerList.add(6);
        integerList.add(4);
        integerList.add(3);
        integerList.add(9);
        System.out.println(oddOrEven(integerList));
    }

    private static void minValue(int[] values) {
        Arrays.stream(values).sorted().distinct().forEach(System.out::print);
    }

    //    Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число, составленное из этих уникальных цифр.
//    Не использовать преобразование в строку и обратно. Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89
//    1. Set
//    2. sort
//    3. int[] -> int
//


    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        ArrayList<Integer> list = new ArrayList<>();
        if (sum % 2 != 0) {
            for (int i = 0; i < integers.size(); i++) {
                if (integers.get(i) % 2 != 0) {
                    list.add(integers.get(i));
                }
            }
        } else {
            for (int i = 0; i < integers.size(); i++) {
                if (integers.get(i) % 2 == 0) {
                    list.add(integers.get(i));
                }
            }
        }
        return list;
    }
}
//        реализовать метод List<Integer> oddOrEven(List<Integer> integers) если сумма всех чисел нечетная - удалить все нечетные,
//        если четная - удалить все четные. Сложность алгоритма должна быть O(N). Optional - решение в один стрим.
//        1. to stream
//        2. sum
//        3. filter
//        4. delete
//        5. to List
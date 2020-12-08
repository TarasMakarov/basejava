package com.urise.webapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lesson12 {
    public static void main(String[] args) {

        int[] intArr = new int[]{1, 2, 5, 5, 1, 8};
//        minValue(intArr);
        List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(1);
        integerList.add(6);
        integerList.add(6);
        integerList.add(4);
        integerList.add(3);
        integerList.add(9);
        System.out.println(oddOrEven(integerList));
    }

//    private static int minValue(int[] values) {
//        int i = 0;
//        i =
//        i = Arrays.stream(values).distinct().sorted().
//        Arrays.stream(values).sorted().distinct().forEach(System.out::print);
//        return i;
//    }

    //    Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число, составленное из этих уникальных цифр.
//    Не использовать преобразование в строку и обратно. Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89
//    1. Set
//    2. sort
//    3. int[] -> int
//


    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        ArrayList<Integer> list;
        if (sum % 2 != 0) {
            list = IntStream.range(0, integers.size()).filter(i -> integers.get(i) % 2 != 0)
                    .mapToObj(integers::get).collect(Collectors.toCollection(ArrayList::new));
        } else {
            list = IntStream.range(0, integers.size()).filter(i -> integers.get(i) % 2 != 0)
                    .mapToObj(integers::get).collect(Collectors.toCollection(ArrayList::new));
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
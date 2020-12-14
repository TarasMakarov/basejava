package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int[] intArr = new int[]{1, 2, 5, 5, 1, 8};
        System.out.println(minValue(intArr));
        List<Integer> integerList = Arrays.asList(2, 4, 5, 4, 2, 9);
        System.out.println(oddOrEven(integerList));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .sorted()
                .distinct()
                .reduce(0, (x, y) -> x * 10 + y);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        return integers.stream()
                .filter(x -> x % 2 != sum % 2)
                .collect(Collectors.toList());
//        return integers.stream()
//                .filter(integers.stream().mapToInt(Integer::intValue)
//                        .sum() % 2 == 0 ? x -> x % 2 != 0 : x -> x % 2 == 0)
//                .collect(Collectors.toList());
    }
}
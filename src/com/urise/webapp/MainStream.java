package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainStream {
    public static void main(String[] args) {
        int[] intArr = new int[]{1, 2, 5, 5, 1, 8};
        System.out.println(minValue(intArr));
        List<Integer> integerList = Arrays.asList(1, 6, 5, 4, 3, 9);
        System.out.println(oddOrEven(integerList));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).sorted().distinct().reduce(0, (x, y) -> x * 10 + y);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = IntStream.range(0, integers.size()).sum();
        ArrayList<Integer> list;
        if (sum % 2 != 0) {
            list = (ArrayList<Integer>) integers.stream()
                    .filter(i -> i % 2 != 0)
                    .collect(Collectors.toList());
        } else {
            list = (ArrayList<Integer>) integers.stream()
                    .filter(i -> i % 2 == 0)
                    .collect(Collectors.toList());
        }
        return list;
    }
}
//        1. to stream
//        2. sum
//        3. filter
//        4. delete
//        5. to List
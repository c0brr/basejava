package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStreams {
    public static void main(String[] args) {
        int[] array = {1, 5, 5, 6, 7, 5, 8, 1, 3, 7};
        List<Integer> list = Arrays.stream(array)
                .boxed()
                .toList();
        System.out.println(new MainStreams().minValue(array));
        System.out.println(new MainStreams().oddOrEven(list));
    }

    private int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce((subtotal, num) -> subtotal * 10 + num)
                .getAsInt();
    }

    private List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream()
                .collect(Collectors.collectingAndThen(Collectors.partitioningBy(num -> num % 2 == 0),
                        map -> map.get(false).size() % 2 == 0 ? map.get(false) : map.get(true)));
    }
}
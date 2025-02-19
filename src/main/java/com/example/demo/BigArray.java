package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BigArray {
    public static List<Integer> processInputIntegers(final int[] input) {
        final List<Integer> resultList = new ArrayList<>();
        IntStream.of(input).forEach(v -> {
            if (v < 0) {
                resultList.add(v);
            } else {
                if (v > 0 && v <= resultList.size()) {
                    resultList.remove(v - 1);
                }
            }
        });
        return resultList;
    }

    public static void main(final String[] args) {
        final int[] inputArray = {-1, -2, -3, 2};
        final List<Integer> result = processInputIntegers(inputArray);
        System.out.println(result);
    }
}

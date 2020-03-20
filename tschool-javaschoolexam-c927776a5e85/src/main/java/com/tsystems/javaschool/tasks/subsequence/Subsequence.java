package com.tsystems.javaschool.tasks.subsequence;

import java.util.Arrays;
import java.util.List;

public class Subsequence {
    public static void main(String[] args) {
        System.out.println(find(Arrays.asList("A", "B", "C", "D"),
                Arrays.asList("BD", "A", "ABC", "B", "M", "D", "M", "C", "DC", "D")));
    }

    @SuppressWarnings("rawtypes")
    static public boolean find(List x, List y) {
        if (x == null || y == null) {
            throw new IllegalArgumentException();
        }
        if (x.size() > y.size()) {
            return false;
        }
        if (x.isEmpty()) {
            return true;
        }
        int n = 0;
        for (Object elm : y) {
            if (x.get(n) == elm) {
                n++;
                if (n == x.size()) {
                    return true;
                }
            }
        }
        return false;
    }
}

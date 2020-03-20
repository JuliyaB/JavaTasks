package com.tsystems.javaschool.tasks.pyramid;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PyramidBuilder {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 4, 3, 5, 6, 1);
        System.out.println(Arrays.deepToString((new PyramidBuilder()).buildPyramid(list)).replace("], ", "]\n"));
    }
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        int numBase = 0;

        double number = ((-1 + Math.sqrt(1 + 8 * inputNumbers.size())) / 2);
        if (number == (int) number) {
            numBase = (int) number;
        } else {
            throw new CannotBuildPyramidException();
        }
        if(inputNumbers.contains(null)){
            throw new CannotBuildPyramidException();
        }
        int[][] arr = new int[numBase][numBase * 2 - 1];
        Collections.sort(inputNumbers);

        int center = numBase;
        int count = 1;
        int arrIdx = 0;

        for (int i = 0, offset = 0; i < numBase; i++, offset++, count++) {
            int start = center - offset - 1;
            for (int j = 0; j < count * 2; j += 2, arrIdx++) {
                arr[i][start + j] = inputNumbers.get(arrIdx);
            }
        }
        return arr;
    }


}

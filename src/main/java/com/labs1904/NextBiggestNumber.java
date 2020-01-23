package com.labs1904;

import java.util.Arrays;
import java.util.Collections;

public class NextBiggestNumber {

    public static void main(String[] args) {
        Integer input = Integer.parseInt(args[0]);
        Integer nextBiggestNumber = getNextBiggestNumber(input);
        System.out.println("Input: " + input);
        System.out.println("Next biggest number: " + nextBiggestNumber);
    }

    public static int getNextBiggestNumber(Integer input) {
        int[] inputArray = Integer.toString(input).chars().map(i -> i - '0').toArray();

        //Sort tempArray in descending order
        int[] tempArray = Arrays.stream(inputArray).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();

        //If array is in descending return -1
        if (Arrays.equals(tempArray, inputArray)) {
            return -1;
        } else {
            int index1;
            tempArray = inputArray.clone();

            //Find element that has a greater value than the element value at index - 1
            for (index1 = tempArray.length - 1; index1 >= 0; index1--) {
                if (tempArray[index1] > tempArray[index1 - 1]) { break; }
            }

            int minValue = index1;

            //Find the element with the lowest value of the elements to the right of index - 1
            for (int index2 = index1 + 1; index2 < tempArray.length; index2++) {
                if (tempArray[index2] > tempArray[index1 - 1]) { minValue = index2; }
            }

            int temp = tempArray[index1 - 1];
            tempArray[index1 - 1] = tempArray[minValue];
            tempArray[minValue] = temp;

            //Sort specified sub-section of array in ascending order
            Arrays.sort(tempArray, index1, tempArray.length);

            StringBuilder stringBuilder = new StringBuilder();
            Arrays.stream(tempArray).forEach(value -> stringBuilder.append(value));

            return Integer.parseInt(stringBuilder.toString());
        }
    }
}
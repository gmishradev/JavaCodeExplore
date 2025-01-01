package com.warehouse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonContinuousRangeAcrossDays {
    public static List<int[]> findCommonContinuousRange(List<List<String>> warehouseSizesPerDay) {
        // Calculate the occurrence of each size in each day
        int[][] occurrence = new int[warehouseSizesPerDay.size()][7];
        for (int i = 0; i < warehouseSizesPerDay.size(); i++) {
            for (int j = 0; j < 7; j++) {
                occurrence[i][j] = countOccurrences(warehouseSizesPerDay.get(i), Integer.toString(j + 1));
            }
        }

        // Find the common continuous range
        int[] commonRange = new int[2];
        boolean foundCommonRange = false;
        for (int i = 0; i < 7; i++) {
            int start = -1;
            for (int j = 0; j < warehouseSizesPerDay.size(); j++) {
                if (occurrence[j][i] > 0) {
                    if (start == -1) {
                        start = j * 7 + i;
                    }
                } else {
                    if (start != -1) {
                        commonRange[0] = start;
                        commonRange[1] = j * 7 + i - 1;
                        foundCommonRange = true;
                        break;
                    }
                }
            }
            if (foundCommonRange) {
                break;
            }
        }

        // Convert the common range index to day and index within the day
        int startDay = commonRange[0] / 7;
        int endDay = commonRange[1] / 7;
        int startIndex = commonRange[0] % 7;
        int endIndex = commonRange[1] % 7;

        List<int[]> result = new ArrayList<>();
        result.add(new int[]{startDay, startIndex, endDay, endIndex});

        return result;
    }

    // Helper method to count occurrences of a size in a day
    private static int countOccurrences(List<String> sizes, String target) {
        int count = 0;
        for (String size : sizes) {
            if (size.equals(target)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        List<List<String>> sizesPerDay = new ArrayList<>();

        sizesPerDay.add(Arrays.asList("M", "S", "S", "S", "XS", "M", "M"));
        sizesPerDay.add(Arrays.asList("M", "S", "M", "M", "XS", "S", "S"));
        sizesPerDay.add(Arrays.asList("XS", "M", "M", "M", "S", "S", "S"));
        sizesPerDay.add(Arrays.asList("XS", "S", "S", "S", "M", "M", "M"));
        sizesPerDay.add(Arrays.asList("XS", "XS", "XS", "XS", "M", "M", "M"));
        sizesPerDay.add(Arrays.asList("M", "M", "M", "M", "S", "S", "S"));
        sizesPerDay.add(Arrays.asList("S", "S", "S", "S", "M", "M", "M"));

        List<int[]> commonRange = findCommonContinuousRange(sizesPerDay);

        for (int[] range : commonRange) {
            int startDay = range[0];
            int startIndex = range[1];
            int endDay = range[2];
            int endIndex = range[3];
            System.out.println("Common continuous range from day " + startDay + " index " + startIndex +
                    " to day " + endDay + " index " + endIndex);
        }
    }
}

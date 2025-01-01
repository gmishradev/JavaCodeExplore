package com.warehouse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContinuousRanges {
    public static List<int[]> findContinuousInterval(List<String> warehouseSizes) {
        List<int[]> continuousInterval = new ArrayList<>();
        int start = 0;

        for (int i = 1; i < warehouseSizes.size(); i++) {
            if (!warehouseSizes.get(i).equals(warehouseSizes.get(i - 1))) {
                continuousInterval.add(new int[]{start, i});
                start = i;
            }
        }
        continuousInterval.add(new int[]{start, warehouseSizes.size() - 1});

        return continuousInterval;
    }

    public static void main(String[] args) {
        List<String> sizes = Arrays.asList(
                "M", "S", "S", "S", "S", "M", "M", "M", "M", "XS", "XS", "XS", "XS", "S",
                "S", "XS", "XS", "M", "M", "XS", "M", "M", "M", "M"
        );

        List<int[]> continuousInterval = findContinuousInterval(sizes);

        for (int[] range : continuousInterval) {
            System.out.println("Create Task to update the warehouse size  " + sizes.get(range[0]) + " for interval start " + range[0] + " end " + range[1]);
        }
    }
}

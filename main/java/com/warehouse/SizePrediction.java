package com.warehouse;

import java.util.HashMap;
import java.util.Map;

public class SizePrediction {

    public static String predictSize(String[] hourData) {
        Map<String, Integer> sizeCount = new HashMap<>();

        // Assign weights to each hour's data
        int weight = 4;
        for (String size : hourData) {
            sizeCount.put(size, sizeCount.getOrDefault(size, 0) + weight);
            weight--;
        }

        // Predict the size for the 5th hour
        String predictedSize = null;
        int maxCount = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : sizeCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                predictedSize = entry.getKey();
            }
        }

        return predictedSize;
    }

    public static void main(String[] args) {
        String[] hourData = {"Medium", "Large", "Large", "Medium"};
        String predictedSize = predictSize(hourData);
        System.out.println("Predicted size for the 5th hour: " + predictedSize);
    }
}

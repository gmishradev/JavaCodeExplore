package com.warehouse;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Your original map
        Map<String, String> cronSizeMap = new HashMap<>();
        cronSizeMap.put("USING CRON  0 12 * * 2 UTC", "Large");
        // Add more entries if needed

        // Custom comparator to sort based on hour and day
        removeSameSizeSchedule(cronSizeMap);

        TreeMap<String, String> sortedCronSizeMap = getSortedCronSizeMap(cronSizeMap);

        // Print the sorted map
        for (Map.Entry<String, String> entry : sortedCronSizeMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    private static TreeMap<String, String> getSortedCronSizeMap(Map<String, String> cronSizeMap) {
        Comparator<String> cronComparator = (cron1, cron2) -> {
            int hour1 = extractHour(cron1);
            int hour2 = extractHour(cron2);
            int day1 = extractDay(cron1);
            int day2 = extractDay(cron2);

            // First compare hours, if hours are same, then compare days
            if (hour1 != hour2) {
                return Integer.compare(hour1, hour2);
            } else {
                return Integer.compare(day1, day2);
            }
        };

        // Create a TreeMap with custom comparator
        TreeMap<String, String> sortedCronSizeMap = new TreeMap<>(cronComparator);
        sortedCronSizeMap.putAll(cronSizeMap);
        return sortedCronSizeMap;
    }

    // Extracts hour from the cron pattern
    private static int extractHour(String cron) {
        String[] parts = cron.split("\\s+");
        return Integer.parseInt(parts[2]); // Assuming hour is always at index 2
    }

    // Extracts day from the cron pattern
    private static int extractDay(String cron) {
        String[] parts = cron.split("\\s+");
        return Integer.parseInt(parts[4]); // Assuming day is always at index 4
    }


    public static Map<String, String> removeSameSizeSchedule(Map<String, String> cronJobSizeMap) {
        // Sort the map by cronJob size
        TreeMap<String, String> sortedCronJobSizeMap = new TreeMap<>(cronJobSizeMap);
        Map<String, String> newCronJobSizeMap = new HashMap<>();

        // Remove same size cron jobs
        String previousSize = null;
        for (Map.Entry<String, String> entry : sortedCronJobSizeMap.entrySet()) {
            String size = entry.getValue();
            if (!size.equalsIgnoreCase(previousSize)) {
                newCronJobSizeMap.put(entry.getKey(), size);
                previousSize = size;
            }
        }
        return newCronJobSizeMap;
    }
}

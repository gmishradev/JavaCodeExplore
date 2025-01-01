package com.govi;

import java.util.*;

public class CalendarConverter {

    public static void main(String[] args) {
        int[][] calendar = {
            {4, 4, 4, 4, 4, 4, 4},
            {4, 4, 4, 4, 4, 4, 4},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 4, 4, 2, 4, 4, 4},
            {1, 4, 4, 2, 4, 4, 4},
            {1, 4, 4, 2, 4, 4, 4},
            {1, 4, 4, 2, 4, 4, 4},
            {1, 4, 4, 2, 4, 4, 4},
            {1, 4, 4, 2, 4, 4, 4},
            {1, 4, 4, 2, 4, 4, 4},
            {1, 4, 4, 2, 4, 4, 4},
            {1, 4, 4, 2, 4, 4, 4},
            {1, 4, 4, 2, 4, 4, 4},
            {4, 4, 4, 2, 4, 4, 4},
            {4, 4, 4, 2, 4, 4, 4},
            {4, 4, 4, 2, 4, 4, 4},
            {4, 4, 4, 2, 4, 4, 4}
        };

        Map<Integer, Map<Integer, List<Integer>>> crons = processCalendar(calendar);

        // Print the crons map
        for (Map.Entry<Integer, Map<Integer, List<Integer>>> entry1 : crons.entrySet()) {
            int key1 = entry1.getKey();
            for (Map.Entry<Integer, List<Integer>> entry2 : entry1.getValue().entrySet()) {
                int key2 = entry2.getKey();
                List<Integer> value = entry2.getValue();
                System.out.println("key1: " + key1 + ", key2: " + key2 + ", value: " + value);
            }
        }
    }

    public static Map<Integer, Map<Integer, List<Integer>>> processCalendar(int[][] calendar) {
        Map<Integer, Map<Integer, List<Integer>>> crons = new HashMap<>();

        // Process each row in the calendar
        for (int hour = 0; hour < calendar.length; hour++) {
            int[] row = calendar[hour];
            int previousSize = -1;
            int currentSize = -1;
            int previousHour = -1;
            int currentHour = -1;

            for (int minute = 0; minute < row.length; minute++) {
                currentSize = row[minute];

                if (minute == 0) {
                    previousSize = currentSize;
                    previousHour = hour;
                }

                if (currentSize != previousSize) {
                    // Store in the crons map
                    if (!crons.containsKey(previousHour)) {
                        crons.put(previousHour, new HashMap<>());
                    }
                    if (!crons.get(previousHour).containsKey(previousSize)) {
                        crons.get(previousHour).put(previousSize, new ArrayList<>());
                    }
                    crons.get(previousHour).get(previousSize).add(hour);

                    // Update for the next segment
                    previousSize = currentSize;
                    previousHour = hour;
                }
            }

            // Edge case: last segment of the row
            if (!crons.containsKey(previousHour)) {
                crons.put(previousHour, new HashMap<>());
            }
            if (!crons.get(previousHour).containsKey(previousSize)) {
                crons.get(previousHour).put(previousSize, new ArrayList<>());
            }
            crons.get(previousHour).get(previousSize).add(hour);
        }

        return crons;
    }
}

package com.govi;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CronGeneration {
    static Map<Integer, String> sizeValues = new HashMap<Integer, String>();


    /**
     * <p>
     * Use the data type as below to store the final output MAP<Integer key1, MAP<String key2,LIST<Integer> value>>
     * <p>
     * Every hour there will be map<String,list<Integer>>
     * <p>
     * Key1- > Minute of the day
     * Key2 -> Unique Size
     * Value -> List of day of the week
     * <p>
     * For the calendar provided below will be the value
     * <p>
     * key1       Key2   value
     * <p>
     * 60           1     0,1,2,3,4,5,6
     * 600          2      1,2,4,5,6
     * 600          4      3
     * 1200         2    0
     * <p>
     * Pseudocode like
     * <p>
     * Crons = []
     * <p>
     * row_process() { //for hour 0,1,2.....23
     * <p>
     * process_cell() {
     * if(get_previous_hour_value and get_current_hour _value is different)
     * {
     * put to the map with the hour as key1, size as key2 and add to value list
     * }
     * }
     * create_crons_for_row()
     * }
     *
     * @return
     */


    public static void main1(String[] args) {


        int[][] calendar = input();


        int size[] = {1, 2, 4};  // XS,S, M,L


        sizeValues.put(1, "XS");
        sizeValues.put(2, "S");
        sizeValues.put(4, "M");
        sizeValues.put(8, "L");

        Map<Integer, Map<String, List<Integer>>> cronMap = processCalendar(calendar);
        //  System.out.println(cronMap);
        System.out.println("Cron Results");

        for (int hour : cronMap.keySet()) {
            Map<String, List<Integer>> sameSizeDayMap = cronMap.get(hour);
            //System.out.println(sameSizeDayMap);
            for (String sizeVal : sameSizeDayMap.keySet()) {
                List<Integer> days = sameSizeDayMap.get(sizeVal);
                System.out.println("changes Wh size to " + (sizeVal) + ", at hour " + hour + " and days " + days);

            }
        }
    }

    private static Map<Integer, Map<String, List<Integer>>> processCalendar(int[][] calendar) {
        Map<Integer, Map<String, List<Integer>>> cronMap = new HashMap<>();

        for (int i = 1; i < calendar.length; i++) {
            Map<String, List<Integer>> everyHourSameSizeDay = cronMap.get(i);
            if (everyHourSameSizeDay == null) {
                everyHourSameSizeDay = new HashMap<>();
            }
            for (int j = 0; j < calendar[i].length; j++) {
                int prevHourSize = calendar[i - 1][j];
                int curHourSize = calendar[i][j];
                if (prevHourSize != curHourSize) {
                    List<Integer> days = everyHourSameSizeDay.get(sizeValues.get(calendar[i][j]));
                    if (days == null) {
                        days = new ArrayList<>();
                    }
                    days.add(j);
                    everyHourSameSizeDay.put(sizeValues.get(calendar[i][j]), days);
                }
            }
            cronMap.put(i - 1, everyHourSameSizeDay);
        }
        return cronMap;
    }


    private static int[][] input() {
        int schedule[][] = new int[25][7];
        int size[] = {1, 2, 4};  // XS,S, M,L

        for (int i = 0; i < schedule.length; i++) {
            for (int j = 0; j < schedule[i].length; j++) {
                if (i < 2) {
                    schedule[i][j] = 4;
                }
                if (i >= 2 && i < 11) {
                    schedule[i][j] = 1;
                }
                if (j == 0 && i >= 2 && i < 21) {
                    schedule[i][j] = 1;
                }

                if (i > 10 && j > 0 && j != 3) {
                    schedule[i][j] = 4;
                }
                if (i > 10 && j == 3) {
                    schedule[i][j] = 8;
                }

                if (i > 20 && j != 3) {
                    schedule[i][j] = 4;
                }
            }
        }

        int count = -1;
        for (int[] ints : schedule) {
            System.out.println("row -> " + count++ + Arrays.toString(ints));
        }
        return schedule;
    }


    public static void main(String[] args) {

        testListSet();
     //  main1(args);
       /* Map<Integer, Map<String, List<Integer>>> cronMap = processCalendar(input1());

        for (int hour : cronMap.keySet()) {
            Map<String, List<Integer>> sameSizeDayMap = cronMap.get(hour);
            //System.out.println(sameSizeDayMap);
            for (String sizeVal : sameSizeDayMap.keySet()) {
                List<Integer> days = sameSizeDayMap.get(sizeVal);
                System.out.println("changes Wh size to " + (sizeVal) + ", at hour " + hour + " and days " + days);

            }
        }*/
    }

    private static Map<Integer, Map<String, List<Integer>>> processCalendar(Map<Integer, List<String>> hourSizeListForWeekMap) {
        Map<Integer, Map<String, List<Integer>>> cronMap = new HashMap<>();

        for (int h = 0; h < 24; h++) {
            Map<String, List<Integer>> everyHourSameSizeDay = cronMap.get(h);
            if (everyHourSameSizeDay == null) {
                everyHourSameSizeDay = new HashMap<>();
            }
            for (int day = 0; day < 7; day++) {
                String prevHourSize = hourSizeListForWeekMap.get(h - 1).get(day);
                String curHourSize = hourSizeListForWeekMap.get(h).get(day);
                if (!prevHourSize.equalsIgnoreCase(curHourSize)) {
                    List<Integer> days = everyHourSameSizeDay.get(curHourSize);
                    if (days == null) {
                        days = new ArrayList<>();
                    }
                    days.add(day);
                    everyHourSameSizeDay.put(curHourSize, days);
                }
            }
            cronMap.put(h, everyHourSameSizeDay);
        }
        return cronMap;
    }

    private static Map<Integer, List<String>> input1() {
        Map<Integer, List<String>> input = new HashMap<>();
        for (int hour = -1; hour < 24; hour++) {
            for (int day = 0; day < 7; day++) {
                if (hour < 1) {
                    input.putIfAbsent(hour, new ArrayList<>());
                    input.get(hour).add("M");
                }
                if (hour >= 1 && hour < 10) {
                    input.putIfAbsent(hour, new ArrayList<>());
                    input.get(hour).add("XS");
                }
                if (day == 0 && hour >= 1 && hour < 20) {

                    input.putIfAbsent(hour, new ArrayList<>());
                    input.get(hour).add("XS");
                }

                if (hour > 9 && day == 3) {

                    input.putIfAbsent(hour, new ArrayList<>());
                    input.get(hour).add("L");
                }
                if (hour > 9 && day > 0 && day != 3) {

                    input.putIfAbsent(hour, new ArrayList<>());
                    input.get(hour).add("M");
                } else if (hour >= 20 && day == 0) {

                    input.putIfAbsent(hour, new ArrayList<>());
                    input.get(hour).add("M");
                }
            }
        }

        for (int hour : input.keySet()) {
            System.out.println("row -> " + hour + input.get(hour));
        }
        return input;
    }


    private static void testListSet()
    {
        List<String> list = new ArrayList<>(7);
        for (int i = 0;i<7;i++) {
            list.add(i, "gk"+i);
        }

        list.forEach(System.out::println);
    }


   /* public void analyzeStoragePolicy1() {
        List<JSONObject> policyList = policy.getPolicy();

        for (JSONObject jsonObject : policyList) {
            // apply policy for dbName,, allowedTimeTravelDay;
            if (checkTimeTravelPolicy(jsonObject)) {
                generateTimeTravelPolicyBasedInsight(jsonObject);
            } else if (checkFailSafePolicy(jsonObject)) {
                generateFailSafePolicyBasedInsight(jsonObject);
            } else if (checkColdTransientTablePolicy(jsonObject)) {
                generateColdTransientTableInsight(jsonObject);
            } else if (checkTableClonePolicy(jsonObject)) {
                generateTableClonePolicyBasedInsight(jsonObject);
            }
        }
    }*/

}

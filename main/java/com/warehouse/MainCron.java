package com.warehouse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainCron{
    public static void main(String[] args) {
       /* Map<String, String> cronIntervalSizeMap = new HashMap<>();
        cronIntervalSizeMap.put("USING CRON  0 * * * * UTC", "L");
        cronIntervalSizeMap.put("USING CRON  5 * * * * UTC", "M");
        cronIntervalSizeMap.put("USING CRON  19 * * * * UTC", "L");
        cronIntervalSizeMap.put("USING CRON  20 * * * * UTC", "L");

        cronMapToJsonString(cronIntervalSizeMap);*/
        System.out.println( "USING CRON  5 * * * * UTC".equals(createCronJob(1, "Monday")));
    }

    private static void cronMapToJsonString(Map<String, String> cronIntervalSizeMap) {
        List<Map<String, String>> jsonList = new ArrayList<>();
        for (Map.Entry<String, String> entry : cronIntervalSizeMap.entrySet()) {
            Map<String, String> jsonMap = new HashMap<>();
            jsonMap.put("cron", entry.getKey());
            jsonMap.put("size", entry.getValue());
            jsonList.add(jsonMap);
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonStr = mapper.writeValueAsString(jsonList);
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public String createDatabaseAndSchema() {
        String sb = "CREATE DATABASE IF NOT EXISTS unravel;"
                + "CREATE SCHEMA IF NOT EXISTS unravel.unravel_schema;"
                + "USE unravel.unravel_schema";
        return sb;
    }

    //"USING CRON  5 * * * * UTC", "Medium"
    private static String createCronJob(Integer hour, String day) {
        StringBuilder cornJob = new StringBuilder();
        cornJob.append("USING CRON  ");
        cornJob.append("5 "); //Minute
        cornJob.append("*"+ " "); //Hour
        cornJob.append("* "); // Day of the month
        cornJob.append("* "); //Month
        cornJob.append("*" + " ");// Day of the week
        cornJob.append("UTC");
        return cornJob.toString();
    }
}

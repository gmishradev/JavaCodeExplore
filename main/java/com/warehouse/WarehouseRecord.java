package com.warehouse;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WarehouseRecord {
    private final String day;
    private final LocalTime timestamp;
    private final String size;

    public WarehouseRecord(String day, String timestamp, String size) {
        this.day = day;
        this.timestamp = LocalTime.parse(timestamp, DateTimeFormatter.ofPattern("HH:mm"));
        this.size = size;
    }

    @Override
    public String toString() {
        return "Day: " + day + ", Timestamp: " + timestamp.format(DateTimeFormatter.ofPattern("HH:mm")) + ", Size: " + size;
    }

    public static void main(String[] args) {
        List<WarehouseRecord> records = generateRecords();
        records.forEach(System.out::println);

    }

    public static List<WarehouseRecord> generateRecords() {
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] sizes = {"XS", "S", "M", "L", "XL", "2XL", "4XL", "6XL", "8XL", "10XL", "12XL", "14XL", "16XL"};
        List<WarehouseRecord> records = new ArrayList<WarehouseRecord>(169);
        Random random = new Random();
        for (String day : days) {
            for (int hour = 0; hour < 24; hour++) {
                String timestamp = String.format("%02d:00", hour);
                String size = sizes[random.nextInt(sizes.length)];
                ; // Example logic for assigning size, change as needed
                WarehouseRecord record = new WarehouseRecord(day, timestamp, size);
                records.add(record);
            }
        }
       List<String>size = new ArrayList<String>();
        for(int i=0;i<24;i++){
            size.add(sizes[random.nextInt(3)]);
        }
        System.out.println(size);


        return records;
    }
}
// write algorithm to arrange hourly daily data so that we can
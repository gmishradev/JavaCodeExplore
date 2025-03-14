/*
package com.warehouse;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ComputeCal {
    public static final LocalSimpleDateFormat SDF_ISO_UTC =
            new LocalSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "UTC");
    static  String  DATASTORE_TIMEZONE_FORMATTER = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String TIMEZONE_UTC = "UTC";
    public static final LocalSimpleDateFormat SDF_ISO_SHORT_UTC =
            new LocalSimpleDateFormat("yyyyMMdd", "UTC");
    public static final ThreadLocal<SimpleDateFormat> dataStoreTimeStampFormat = ThreadLocal.withInitial(() -> {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATASTORE_TIMEZONE_FORMATTER);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE_UTC));
        return simpleDateFormat;
    });

    public static String getDayAndHour(String timeStamp) {
        Instant instant = Instant.parse(timeStamp);
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));
        DayOfWeek dayOfWeek = zonedDateTime.getDayOfWeek();
        int hour = zonedDateTime.getHour();
        String result = "Day of the week: " + dayOfWeek + ", Hour of the day: " + hour;
        return result;
    }
    private static String getDate(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime().toString();
    }

    public static void main(String[] args){
       */
/* String time = SDF_ISO_UTC.instance().format(new Date(Duration.ofHours(1).toMillis()));
        System.out.println(time);
        System.out.println(formatHour(1));*//*


        getDayAndHour("2024-06-13T01:00:00.000Z");
        // Get the current date and time in UTC
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneOffset.UTC);

        // Subtract one day to get yesterday's date and set time to midnight
        LocalDateTime yesterdayMidnight = currentDateTime.minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);

        // Convert LocalDateTime to timestamp in milliseconds
        long timestamp = yesterdayMidnight.toEpochSecond(ZoneOffset.UTC) * 1000;

        System.out.println("Timestamp for yesterday midnight (UTC): " + timestamp);
        //System.out.println( convertLongDateToOsUTC(System.currentTimeMillis()));
    }
    public static String formatHour(int hour) {
        LocalTime time = LocalTime.of(hour, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
    }

    public static String convertLongDateToOsUTC(long startDate) {
        return dataStoreTimeStampFormat.get().format(new Date(startDate));
    }


}
*/

package com.govi;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import javax.xml.bind.SchemaOutputResolver;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TestDate {

    public static void main(String[] args) {
   /*     Date date = new Date();
        System.out.println(date);
        System.out.println(date.getDay());

        LocalDate currentDate
                = LocalDate.parse("2024-03-15");
        System.out.println(currentDate.getDayOfMonth());

        Calendar cal = Calendar.getInstance();

        System.out.println("Current Date:" + cal.get(Calendar.DATE));

        System.out.println("Current Month:" + (cal.get(Calendar.MONTH) + 1));

        System.out.println("Current Year:" + cal.get(Calendar.YEAR));*/

   /*     String inputString = "rtb81672.us-east-1";

        // Split the string based on the dot
        String[] parts = inputString.split("\\.");
        System.out.println(parts[0]);

        // Print the parts
        for (String part : parts) {
            System.out.println(part);
        }*/
       /* String name = "spark_catalog.usecase.data_quality_transaction";
        List<String> tables = new ArrayList<String>();
        tables.add(name);
        tables.add("usecase1.data_quality_transaction1");
        tables.add("spark_catalog2.usecase2.data_quality_transaction2");
        tables.addAll(tables.stream().filter(t -> t.split("\\.").length > 2).map(t -> t.substring(t.indexOf(".") + 1)).collect(Collectors.toList()));
      tables.forEach(System.out::println);*/
        List<Long> list = new LinkedList<>();
      for(int i = 0; i <8; i++) {
          list.add(0l);
      }
        isTableHasSizeAnomaly(list, 10l);
        //System.out.println( percentageChange(list, 9218059162071741130L));
        //System.out.println(percentageChange5(list, 92898L));


    }


    public static double percentageChange(Collection<Long> sizeList, long todaySize) {
        // Calculate mean
        double sum = 0;
        for (long value : sizeList) {
            sum += value;
        }
        double mean = sum / sizeList.size();

        // Calculate percentage change
        double percentageChange = ((todaySize - mean) / mean) * 100.0;

        // Format the result to two decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(percentageChange));
    }


    public static double percentageChange5(Collection<Long> sizeList, long todaySize) {
        // Calculate mean
        BigDecimal sum = BigDecimal.ZERO;
        for (Long value : sizeList) {
            sum = sum.add(BigDecimal.valueOf(value));
        }
        BigDecimal mean = sum.divide(BigDecimal.valueOf(sizeList.size()));

        // Calculate percentage change
        BigDecimal todaySizeBigDecimal = BigDecimal.valueOf(todaySize);
        BigDecimal percentageChange = todaySizeBigDecimal.subtract(mean)
                .divide(mean)
                .multiply(BigDecimal.valueOf(100));

        return percentageChange.doubleValue();
    }

    public static boolean isTableHasSizeAnomaly(Collection<Long> sizeList, long todaySize) {
        if (sizeList == null || sizeList.size() < 7) {
            return false;
        }
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (double value : sizeList) {
            stats.addValue(value);
        }
        // Calculate mean and standard deviation
        double mean = stats.getMean();
        double stdDev = stats.getStandardDeviation();

        if (stdDev > 0) {
            // Calculate z-score for each value
            double zScore = (todaySize - mean) / stdDev;
            return Math.abs(zScore) > 3;
        }
        return false;
    }


}

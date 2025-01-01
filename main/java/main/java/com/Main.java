package main.java.com;


import com.google.gson.Gson;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {


        //Object queryStepsObject = queryStage.get(QUERY_STEPS);
        //            // [{"name":"READ","substeps":["$1","FROM __stage00_output"]}, {"name":"LIMIT","substeps":["10000013"]}, {"name":"WRITE","substeps":["$1","TO __stage01_output"]}]
        //            // List<String> rawQuerySteps = Stream.of(queryStepsObject).map(Object::toString).collect(Collectors.toList());
        //            List<String> rawQuerySteps = JsonObject.;
        //            for (String rawQueryStep : rawQuerySteps) {
        //                LOG.info(" ---- rawQueryStep = " + rawQueryStep);
        //                //  ---- rawQueryStep = [{"name":"READ","substeps":["$1:sr_num","FROM unravel-dataproc.bq_workload.partitioned_accounts_0","WHERE equal($1, 1)"]},
        //                //  {"name":"LIMIT","substeps":["10000021"]}, {"name":"WRITE","substeps":["$1","TO __stage00_output"]}]
        //            }
        //            List<QueryStep> querySteps = generateQuerySteps(rawQuerySteps, generatedStageId);

        // List<Long>input2 = Stream.of(object).map(Long::valueOf).collect(Collectors.toList());

        Object queryStepsObject = "[{\"name\":\"READ\",\"substeps\":[\"$1\",\"FROM __stage00_output\"]}, {\"name\":\"LIMIT\",\"substeps\":[\"10000013\"]}, {\"name\":\"WRITE\",\"substeps\":[\"$1\",\"TO __stage01_output\"]}]";//new Object();//

        String queryStepsString = queryStepsObject.toString();
        /*JSONArray jsonArray = new JSONArray(queryStepsString);
        List<Object> test = jsonArray.toList();
        System.out.println(test);
        for (Object o : test) {
            System.out.println(o);
        }*/

        Runnable r = new Runnable() {
            @Override
            public void run() {

            }
        };

       /* Gson gson = new Gson();
        String[] list = gson.fromJson(queryStepsString, String[].class);
        List<String> list1 = Arrays.asList(list);
        System.out.println(list1);*/

        List<Long>t = new ArrayList<>();
        t.add(2l);
        t.add(null);
        for(Long i: t)
        {
            i.longValue();
            System.out.println(i);
        }
    }


    public void fun () throws InterruptedException {
        for(int i=0;i<100;i++)
        {
            Thread.sleep(100);
            System.out.println("----------"+ i);

           /* if(i==900)
            {
                throw new InterruptedException("interrupted");
            }
            else
            {
                throw new RuntimeException(" runtime ");
            }*/
           // System.out.println("----------"+ i);
        }
    }

    private static void benchMark() {
        Set<String> employeeSet1 = new HashSet<>();

        List<String> employeeList1 = new ArrayList<>();
        Set<String> employeeSet2 = new HashSet<>();
        List<String> employeeList2 = new ArrayList<>();
        long set1Size = 1000;
        for (int i = 0; i < set1Size; i++) {
            employeeSet1.add("slv;slsfnsfldsfnsdfvsfvsfvs" + i);
            // employeeSet2.add("slv;slsfnsfldsfnsdfvsfvsfvs"+i);
        }
        for (int i = 0; i < set1Size - 100; i++) {
            employeeSet2.add("slv;slsfnsfldsfnsdfvsfvsfvs" + i);
            employeeList1.add("slv;slsfnsfldsfnsdfvsfvsfvs" + i);
            employeeList2.add("slv;slsfnsfldsfnsdfvsfvsfvs" + i);
        }


        long start = System.currentTimeMillis();
        // employeeList1.removeAll(employeeList2);
        //System.out.println( "List time  "+ (System.currentTimeMillis()-start) );
        start = System.currentTimeMillis();
        employeeSet1.removeAll(employeeSet2);
        System.out.println("Set time  " + (System.currentTimeMillis() - start));
    }

    private static int InversionScore(String s) {
        int len = s.length();
        int result = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == 'a') {
                result = result + countNumberOfZ(s, i);
            }
        }
        return result;
    }

    private static int countNumberOfZ(String s, int i) {
        int count = 0;
        for (int t = 0; t < i; t++) {
            if (s.charAt(t) == 'z') {
                count++;
            }
        }
        return count;
    }

    public static int maximumSum(int N, int A[]) {
        int max = 0;
        int sum;
        for (int i = 0; i < N - 2; i++) {
            sum = findSum(A[i], A[i + 1], A[i + 2]);
            if (max < sum) {
                max = sum;
            }
        }
        return max;
    }

    private static int findSum(int i, int i1, int i2) {

        int largest = i > i1 ? (Math.max(i, i2)) : (Math.max(i1, i2));
        return Math.abs(largest - i) + Math.abs(largest - i1) + Math.abs(largest - i2);
    }

    private static void fun(boolean[] b3) {
        b3[0] = false;

    }

    /*public static void testInitialDelay() {
        long initialDelay = initialDelay();
        DateTime currentDateTime = DateTime.now(DateTimeZone.UTC);
        long calculate = calculateInitialDelay(currentDateTime);
    }

    public static long initialDelay() {
        DateTime currentDateTime = DateTime.now(DateTimeZone.UTC);
        long initialDelay = calculateInitialDelay(currentDateTime) + 5;
        return initialDelay;
    }

    public static long calculateInitialDelay(DateTime currentDateTime){
        long currentDateInMillis = currentDateTime.toInstant().getMillis();
        long nextRunTimeInMillis = currentDateTime.withTimeAtStartOfDay().plusDays(1).toInstant().getMillis();
        return (nextRunTimeInMillis - currentDateInMillis)/1000;
    }*/
}

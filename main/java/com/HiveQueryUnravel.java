package com;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HiveQueryUnravel {


    public static void main(String[] args) {
        fun2();

        String tableMetadata = "SELECT t.\"TBL_ID\", " +
                "       d.\"NAME\" as dbname, " +
                "       t.\"TBL_NAME\", " +
                "       t.\"OWNER\", " +
                "       t.\"CREATE_TIME\", " +
                "       t.\"LAST_ACCESS_TIME\", " +
                "       t.\"TBL_TYPE\", " +
                "       t.\"RETENTION\", " +
                "       t.\"VIEW_ORIGINAL_TEXT\", " +
                "       t.\"VIEW_EXPANDED_TEXT\", " +
                "       p.\"PKEY_NAME\" " +
                "FROM   \"TBLS\" t " +
                "JOIN   \"DBS\" d " +
                "ON     t.\"DB_ID\" = d.\"DB_ID\" " +
                "LEFT JOIN \"PARTITION_KEYS\" p " +
                "ON     t.\"TBL_ID\" = p.\"TBL_ID\" ";

        System.out.println("tableMetadata");
        tableMetadata = tableMetadata.replaceAll("\\\"", "").replaceAll("     "," ");
        System.out.println(tableMetadata);

        String tableDetails = "SELECT t.\"TBL_ID\", " +
                "       s.\"LOCATION\", " +
                "       s.\"NUM_BUCKETS\", " +
                "       s.\"INPUT_FORMAT\", " +
                "       s.\"OUTPUT_FORMAT\", " +
                "       s.\"IS_COMPRESSED\", " +
                "       sd.\"SLIB\" as serde, " +
                "       bc.\"BUCKET_COL_NAME\", " +
                "       sc.\"COLUMN_NAME\" " +
                "FROM   \"TBLS\" t " +
                "JOIN   \"SDS\" s " +
                "ON     t.\"SD_ID\" = s.\"SD_ID\" " +
                "JOIN   \"SERDES\" sd " +
                "ON     s.\"SERDE_ID\" = sd.\"SERDE_ID\" " +
                "LEFT JOIN   \"BUCKETING_COLS\" bc " +
                "ON     s.\"SD_ID\" = bc.\"SD_ID\" " +
                "LEFT JOIN   \"SORT_COLS\" sc " +
                "ON     s.\"SD_ID\" = sc.\"SD_ID\" ";

        System.out.println("tableDetails");
        tableDetails = tableDetails.replaceAll("\\\"", "").replaceAll("     "," ");
        System.out.println(tableDetails);


        String sqlText = "SELECT t.\"TBL_ID\", " +
                "       s.\"LOCATION\", " +
                "       s.\"NUM_BUCKETS\", " +
                "       s.\"INPUT_FORMAT\", " +
                "       s.\"OUTPUT_FORMAT\", " +
                "       s.\"IS_COMPRESSED\" " +
                "FROM   \"TBLS\" t " +
                "JOIN   \"SDS\" s " +
                "ON     t.\"SD_ID\" = s.\"SD_ID\" ";


        System.out.println("sqlText");
        sqlText = sqlText.replaceAll("\\\"", "").replaceAll("     "," ");
        System.out.println(sqlText);


        String tableState = "SELECT t.\"TBL_ID\", " +
                "       cs.\"LAST_ANALYZED\" " +
                "FROM \"TBLS\" t " +
                "LEFT JOIN   \"TAB_COL_STATS\" cs " +
                "ON     t.\"TBL_ID\" = cs.\"TBL_ID\" ";


        System.out.println("tableState");
        tableState = tableState.replaceAll("\\\"", "").replaceAll("     "," ");
        System.out.println(tableState);


        String partitionDetailsForTable = "SELECT T.\"TBL_ID\", " +
                "        P.\"PART_ID\", " +
                "        S.\"SD_ID\", " +
                "        D.\"NAME\", " +
                "        T.\"TBL_NAME\", " +
                "        P.\"CREATE_TIME\", " +
                "        S.\"LOCATION\", " +
                "        S.\"NUM_BUCKETS\" " +
                "FROM    \"PARTITIONS\" P " +
                "INNER JOIN \"TBLS\" T " +
                "ON P.\"TBL_ID\" = T.\"TBL_ID\" " +
                "INNER JOIN \"DBS\" D " +
                "ON T.\"DB_ID\" = D.\"DB_ID\" " +
                "LEFT OUTER JOIN \"SDS\" S " +
                "ON P.\"SD_ID\" = S.\"SD_ID\"";

        System.out.println("---------: partitionDetailsForTable");

        System.out.println(partitionDetailsForTable);
        partitionDetailsForTable = partitionDetailsForTable.replaceAll("\\\"", "");
        System.out.println(partitionDetailsForTable);


        String hiveCombineQuery=  "SELECT t.\"TBL_ID\", " +
                "       d.\"NAME\" as dbname, " +
                "       t.\"TBL_NAME\", " +
                "       t.\"OWNER\", " +
                "       t.\"CREATE_TIME\", " +
                "       t.\"TBL_TYPE\", " +
                "       t.\"RETENTION\", " +
                "       p.\"PKEY_NAME\", " +
                "       s.\"LOCATION\", " +
                "       s.\"NUM_BUCKETS\", " +
                "       s.\"INPUT_FORMAT\", " +
                "       s.\"OUTPUT_FORMAT\", " +
                "       s.\"IS_COMPRESSED\" , " +
                "       cs.\"LAST_ANALYZED\" " +
                "FROM   \"TBLS\" t " +
                "JOIN   \"DBS\" d " +
                "ON     t.\"DB_ID\" = d.\"DB_ID\" " +
                "LEFT JOIN  \"SDS\" s " +
                "ON     t.\"SD_ID\" = s.\"SD_ID\" " +
                "LEFT JOIN   \"TAB_COL_STATS\" cs " +
                "ON     t.\"TBL_ID\" = cs.\"TBL_ID\" " +
                "LEFT JOIN \"PARTITION_KEYS\" p " +
                "ON     t.\"TBL_ID\" = p.\"TBL_ID\" " ;//+
               // "WHERE  t.\"DB_ID\"  IN ( " + dbIds + " )";


        System.out.println("hiveCombineQuery");
        hiveCombineQuery = hiveCombineQuery.replaceAll("\\\"", "").replaceAll("     "," ");
        System.out.println(hiveCombineQuery);


        String sqlTextGroupBy = "SELECT count(1) as table_cnt, d.\"DB_ID\" " +
                "FROM   \"TBLS\" t " +
                "JOIN   \"DBS\" d " +
                "ON     t.\"DB_ID\" = d.\"DB_ID\"  " +
                "GROUP BY d.\"DB_ID\" " +
                "ORDER BY table_cnt desc ";


        System.out.println("sqlTextGroupBy");
        System.out.println(sqlTextGroupBy);
        sqlTextGroupBy = sqlTextGroupBy.replaceAll("\\\"", "").replaceAll("     "," ");
        System.out.println(sqlTextGroupBy);




        fun1();

       /* for(int i=0;i<10;i++)
        {
            try {
                fun();
            }
            catch (RuntimeException ignored)
            {
                System.out.println("fun catched");
            }
        }
        System.out.println("ended ");*/
    }

    private static void fun() {

        System.out.println("fun started");

        if(true)
        {
            System.out.println("fun ended");
            throw new RuntimeException();
        }
    }


public  static void fun1() {

 /*   Get table partition count for top 100 tables (which has max number of partitions).

        Select count(1) partition_count, t.TBL_ID from
    PARTITIONS p INNER JOIN TBLS t ON p.TBL_ID = t.TBL_ID  GROUP BY t.TBL_ID  ORDER BY partition_count desc limit 100;

    Get total partiton count for each DB.

    Select count(1) partition_count from
    PARTITIONS p INNER JOIN TBLS t ON p.TBL_ID = t.TBL_ID INNER JOIN DBS d ON d.DB_ID = t.DB_ID GROUP BY d.DB_ID  ORDER BY*/

    String partitioncount = "SELECT count(1) partition_count "+
            "FROM    \"PARTITIONS\" P " +
            "INNER JOIN \"TBLS\" T " +
            "ON P.\"TBL_ID\" = T.\"TBL_ID\" " +
            "GROUP BY P.\"PART_ID\" ";
    System.out.println(partitioncount);
    partitioncount = partitioncount.replaceAll("\\\"", "").replaceAll("     "," ");
    System.out.println(partitioncount);



    String tableCount = "SELECT count(1) as table_cnt " +
            "FROM   \"TBLS\" t " +
            "JOIN   \"DBS\" d " +
            "ON     t.\"DB_ID\" = d.\"DB_ID\"  " +
            "GROUP BY t.\"TBL_ID\" " ;

    System.out.println("tableCount");
    System.out.println(tableCount);
    tableCount = tableCount.replaceAll("\\\"", "").replaceAll("     "," ");
    System.out.println(tableCount);

    String totalPartition = "SELECT count(1) partition_count, " +
            "D.\"DB_ID\" "+
            "FROM    \"PARTITIONS\" P " +
            "INNER JOIN \"TBLS\" T " +
            "ON P.\"TBL_ID\" = T.\"TBL_ID\" " +
            "INNER JOIN \"DBS\" D " +
            "ON T.\"DB_ID\" = D.\"DB_ID\" " +
            "GROUP BY D.\"DB_ID\" "+
            "ORDER BY partition_count desc";
    System.out.println(totalPartition);
    totalPartition = totalPartition.replaceAll("\\\"", "").replaceAll("     "," ");
    System.out.println(totalPartition);


}

    public  static void fun2() {

        String tableMetadataQuery = "SELECT t.\"TBL_ID\", " +
                "       d.\"NAME\" as dbname, " +
                "       t.\"TBL_NAME\", " +
                "       t.\"OWNER\", " +
                "       t.\"CREATE_TIME\", " +
                "       t.\"TBL_TYPE\", " +
                "       t.\"RETENTION\", " +
                "       p.\"PKEY_NAME\", " +
                "       s.\"LOCATION\", " +
                "       s.\"NUM_BUCKETS\", " +
                "       s.\"INPUT_FORMAT\", " +
                "       s.\"OUTPUT_FORMAT\", " +
                "       s.\"IS_COMPRESSED\" , " +
                "       cs.\"LAST_ANALYZED\" " +
                "FROM   \"TBLS\" t " +
                "JOIN   \"DBS\" d " +
                "ON     t.\"DB_ID\" = d.\"DB_ID\" " +
                "LEFT JOIN  \"SDS\" s " +
                "ON     t.\"SD_ID\" = s.\"SD_ID\" " +
                "LEFT JOIN   \"TAB_COL_STATS\" cs " +
                "ON     t.\"TBL_ID\" = cs.\"TBL_ID\" " +
                "LEFT JOIN \"PARTITION_KEYS\" p " +
                "ON     t.\"TBL_ID\" = p.\"TBL_ID\" " +
                "ORDER BY  t.\"TBL_ID\"  " +
                "LIMIT " + 1000 + " OFFSET " + 0;
        System.out.println(tableMetadataQuery);
        tableMetadataQuery = tableMetadataQuery.replaceAll("\\\"", "").replaceAll("     "," ");
        System.out.println(tableMetadataQuery);




        String partitionMetadataQuery = "SELECT T.\"TBL_ID\", " +
                "        P.\"PART_ID\", " +
                "        S.\"SD_ID\", " +
                "        D.\"NAME\", " +
                "        T.\"TBL_NAME\", " +
                "        P.\"CREATE_TIME\", " +
                "        S.\"LOCATION\", " +
                "        S.\"NUM_BUCKETS\" " +
                "FROM    \"PARTITIONS\" P " +
                "INNER JOIN \"TBLS\" T " +
                "ON P.\"TBL_ID\" = T.\"TBL_ID\" " +
                "INNER JOIN \"DBS\" D " +
                "ON T.\"DB_ID\" = D.\"DB_ID\" " +
                "LEFT OUTER JOIN \"SDS\" S " +
                "ON P.\"SD_ID\" = S.\"SD_ID\" " +
                "ORDER BY  P.\"PART_ID\"  " +
                "LIMIT " + 1000 + " OFFSET " + 0;

        System.out.println(partitionMetadataQuery);
        partitionMetadataQuery = partitionMetadataQuery.replaceAll("\\\"", "").replaceAll("     "," ");
        System.out.println(partitionMetadataQuery);

        String partitionIdAndTablePathQuery= "SELECT T.\"TBL_ID\", " +
                "        P.\"PART_ID\", " +
                "        s.\"LOCATION\" " +
                "FROM   \"PARTITIONS\" P " +
                "INNER JOIN \"TBLS\" T " +
                "ON P.\"TBL_ID\" = T.\"TBL_ID\" " +
                "LEFT JOIN  \"SDS\" s " +
                "ON  t.\"SD_ID\" = s.\"SD_ID\" " +
                "ORDER BY  P.\"PART_ID\"  " +
                "LIMIT " + 1000 + " OFFSET " + 0;
        System.out.println(partitionIdAndTablePathQuery);
        partitionIdAndTablePathQuery = partitionIdAndTablePathQuery.replaceAll("\\\"", "").replaceAll("     "," ");
        System.out.println(partitionIdAndTablePathQuery);



        String tableIdPartitionCountQuery = "SELECT count(1) partition_count, T.\"TBL_ID\"  " +
                "FROM   \"PARTITIONS\" P " +
                "INNER JOIN \"TBLS\" T " +
                "ON P.\"TBL_ID\" = T.\"TBL_ID\" " +
                "GROUP BY T.\"TBL_ID\" " +
                "order by  t.\"TBL_ID\"  " +
                " LIMIT " + 1000 + " OFFSET " + 0;

        System.out.println(tableIdPartitionCountQuery);
        tableIdPartitionCountQuery = tableIdPartitionCountQuery.replaceAll("\\\"", "").replaceAll("     "," ");
        System.out.println(tableIdPartitionCountQuery);




        /*
        SELECT t."TBL_ID",        d."NAME" as dbname,        t."TBL_NAME",        t."OWNER",        t."CREATE_TIME",        t."TBL_TYPE",        t."RETENTION",        p."PKEY_NAME",        s."LOCATION",        s."NUM_BUCKETS",        s."INPUT_FORMAT",        s."OUTPUT_FORMAT",        s."IS_COMPRESSED" ,        cs."LAST_ANALYZED" FROM   "TBLS" t JOIN   "DBS" d ON     t."DB_ID" = d."DB_ID" LEFT JOIN  "SDS" s ON     t."SD_ID" = s."SD_ID" LEFT JOIN   "TAB_COL_STATS" cs ON     t."TBL_ID" = cs."TBL_ID" LEFT JOIN "PARTITION_KEYS" p ON     t."TBL_ID" = p."TBL_ID" ORDER BY  t."TBL_ID"  LIMIT 1000 OFFSET 0
        SELECT t.TBL_ID,    d.NAME as dbname,    t.TBL_NAME,    t.OWNER,    t.CREATE_TIME,    t.TBL_TYPE,    t.RETENTION,    p.PKEY_NAME,    s.LOCATION,    s.NUM_BUCKETS,    s.INPUT_FORMAT,    s.OUTPUT_FORMAT,    s.IS_COMPRESSED ,    cs.LAST_ANALYZED FROM   TBLS t JOIN   DBS d ON t.DB_ID = d.DB_ID LEFT JOIN  SDS s ON t.SD_ID = s.SD_ID LEFT JOIN   TAB_COL_STATS cs ON t.TBL_ID = cs.TBL_ID LEFT JOIN PARTITION_KEYS p ON t.TBL_ID = p.TBL_ID ORDER BY  t.TBL_ID  LIMIT 1000 OFFSET 0
        SELECT T."TBL_ID",         P."PART_ID",         S."SD_ID",         D."NAME",         T."TBL_NAME",         P."CREATE_TIME",         S."LOCATION",         S."NUM_BUCKETS" FROM    "PARTITIONS" P INNER JOIN "TBLS" T ON P."TBL_ID" = T."TBL_ID" INNER JOIN "DBS" D ON T."DB_ID" = D."DB_ID" LEFT OUTER JOIN "SDS" S ON P."SD_ID" = S."SD_ID" ORDER BY  P."PART_ID"  LIMIT 1000 OFFSET 0
        SELECT T.TBL_ID,     P.PART_ID,     S.SD_ID,     D.NAME,     T.TBL_NAME,     P.CREATE_TIME,     S.LOCATION,     S.NUM_BUCKETS FROM    PARTITIONS P INNER JOIN TBLS T ON P.TBL_ID = T.TBL_ID INNER JOIN DBS D ON T.DB_ID = D.DB_ID LEFT OUTER JOIN SDS S ON P.SD_ID = S.SD_ID ORDER BY  P.PART_ID  LIMIT 1000 OFFSET 0
        SELECT T."TBL_ID",         P."PART_ID",         s."LOCATION" FROM   "PARTITIONS" P INNER JOIN "TBLS" T ON P."TBL_ID" = T."TBL_ID" LEFT JOIN  "SDS" s ON  t."SD_ID" = s."SD_ID" ORDER BY  P."PART_ID"  LIMIT 1000 OFFSET 0
        SELECT T.TBL_ID,     P.PART_ID,     s.LOCATION FROM   PARTITIONS P INNER JOIN TBLS T ON P.TBL_ID = T.TBL_ID LEFT JOIN  SDS s ON  t.SD_ID = s.SD_ID ORDER BY  P.PART_ID  LIMIT 1000 OFFSET 0
        SELECT count(1) partition_count, T."TBL_ID"  FROM   "PARTITIONS" P INNER JOIN "TBLS" T ON P."TBL_ID" = T."TBL_ID" GROUP BY T."TBL_ID" order by  t."TBL_ID"   LIMIT 1000 OFFSET 0
        SELECT count(1) partition_count, T.TBL_ID  FROM   PARTITIONS P INNER JOIN TBLS T ON P.TBL_ID = T.TBL_ID GROUP BY T.TBL_ID order by  t.TBL_ID   LIMIT 1000 OFFSET 0
        */


      /*

   SELECT  count(1)  FROM   "TBLS" t JOIN   "DBS" d ON     t."DB_ID" = d."DB_ID" LEFT JOIN  "SDS" s ON  t."SD_ID" = s."SD_ID" LEFT JOIN   "TAB_COL_STATS" cs ON     t."TBL_ID" = cs."TBL_ID" LEFT JOIN "PARTITION_KEYS" p ON
   t."TBL_ID" = p."TBL_ID" ORDER BY  t."TBL_ID"  LIMIT 1000 OFFSET 0





           #tableMetadataQuery
           SELECT t.TBL_ID,    d.NAME as dbname,    t.TBL_NAME,    t.OWNER,    t.CREATE_TIME,    t.TBL_TYPE,    t.RETENTION,    p.PKEY_NAME,    s.LOCATION,    s.NUM_BUCKETS,    s.INPUT_FORMAT,    s.OUTPUT_FORMAT,    s.IS_COMPRESSED ,    cs.LAST_ANALYZED FROM   TBLS t JOIN   DBS d ON t.DB_ID = d.DB_ID LEFT JOIN  SDS s ON t.SD_ID = s.SD_ID LEFT JOIN   TAB_COL_STATS cs ON t.TBL_ID = cs.TBL_ID LEFT JOIN PARTITION_KEYS p ON t.TBL_ID = p.TBL_ID ORDER BY  t.TBL_ID  LIMIT #limit OFFSET #offset

          #partitionMetadataQuery
          SELECT T.TBL_ID,     P.PART_ID,     S.SD_ID,     D.NAME,     T.TBL_NAME,     P.CREATE_TIME,     S.LOCATION,     S.NUM_BUCKETS FROM    PARTITIONS P INNER JOIN TBLS T ON P.TBL_ID = T.TBL_ID INNER JOIN DBS D ON T.DB_ID = D.DB_ID LEFT OUTER JOIN SDS S ON P.SD_ID = S.SD_ID ORDER BY  P.PART_ID  LIMIT #limit OFFSET #offset

          #partitionIdAndTablePathQuery
          SELECT  P.PART_ID,     s.LOCATION FROM   PARTITIONS P INNER JOIN TBLS T ON P.TBL_ID = T.TBL_ID LEFT JOIN  SDS s ON  t.SD_ID = s.SD_ID ORDER BY  P.PART_ID  LIMIT #limit OFFSET #offset

          #tableIdPartitionCountQuery
          SELECT count(1) partition_count, T.TBL_ID  FROM   PARTITIONS P INNER JOIN TBLS T ON P.TBL_ID = T.TBL_ID GROUP BY T.TBL_ID order by  t.TBL_ID   LIMIT #limit OFFSET #offset

*/
    }


    /*// TODO - Scroll API  is not working properly
    public static Map<String, List<Integer>> getTableIdAndEventIdsMapWithScroll(OpenSearchClientProvider clientProvider,
                                                                                Set<Integer> stalePolicyIds) {
        String index = DocType.event.getAlias();
        BoolQuery boolQuery = BoolQuery.of(b -> {
            if (!CommonSharedUtils.isNullOrEmpty(stalePolicyIds)) {
                b.must(m -> {
                    m.bool(b1 -> {
                                for (int policyId : stalePolicyIds) {
                                    b1.should(s -> s.term(
                                            st -> st.field(POLICY_ID).value(v -> v.longValue(policyId))));
                                }
                                return b1;
                            }
                    );
                    return m;
                });
            }
            return b;
        });

        Map<String, Aggregation> aggregationMap = new HashMap<>();

        Aggregation aggregation = Aggregation.of(a -> a
                .terms(t -> t
                        .field(ENTITY_ID)
                        .size(MAX_SIZE)
                ).aggregations(EVENT_NUMBERS, Aggregation.of(b -> b.terms(t -> t.field(EVENT_NUMBER).size(AGG_FIELD_SIZE))))
        );
        aggregationMap.put(TABLE_IDS_EVENTS, aggregation);

        Query query = boolQuery._toQuery();
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index(index)
                .size(SCROLL_SIZE)
                .query(query).aggregations(aggregationMap)
        );
        Map<String, List<Integer>> tableIdAndEventIdsMap = new HashMap<>();

        org.opensearch.client.opensearch.core.SearchResponse<ObjectNode> scrollResp;
        try {
            scrollResp = clientProvider.get().search(searchRequest, ObjectNode.class);
        } catch (Throwable exception) {
            LOG.error("Unexpected exception {} ", exception.getMessage(), exception);
            return tableIdAndEventIdsMap;
        }

        while (scrollResp != null && scrollResp.hits().hits().size() > 0) {

            List<StringTermsBucket> tableIdEvents = scrollResp.aggregations().get(TABLE_IDS_EVENTS).sterms().buckets().array();
            LOG.info("Got {} hits for tableIdEvents", tableIdEvents.size());
            for (StringTermsBucket tableIdEvent : tableIdEvents) {
                String tableId = tableIdEvent.key();
                List<Integer> eventIds = new ArrayList<>();
                List<LongTermsBucket> events = tableIdEvent.aggregations().get(EVENT_NUMBERS).lterms().buckets().array();
                for (LongTermsBucket event : events) {
                    int eventId = Integer.parseInt(event.key());
                    eventIds.add(eventId);
                }
                tableIdAndEventIdsMap.put(tableId, eventIds);
            }
            try {
                ScrollRequest scrollRequest =
                        new ScrollRequest.Builder().scrollId(scrollResp.scrollId()).scroll(t -> t.time(SCROLL_TIME_OUT)).build();
                scrollResp = clientProvider.get().scroll(scrollRequest, ObjectNode.class);
            } catch (IOException exception) {
                LOG.error("Error during getting TableIdAndEventIdsMap from event index ", exception);
                break;
            }
        }
        return tableIdAndEventIdsMap;
    }*




  public static Map<String, List<Integer>> getTableIdAndEventIdsMap(OpenSearchClientProvider clientProvider, int policyId) {
        String index = DocType.event.getAlias();

        BoolQuery boolQuery = BoolQuery.of(b -> {
            b.must(m -> {
                m.bool(b1 -> {
                            b1.must(s -> s.term(
                                    st -> st.field(POLICY_ID).value(v -> v.longValue(policyId))));
                            return b1;
                        }
                );
                return m;
            });
            return b;
        });

        Map<String, Aggregation> aggregationMap = new HashMap<>();
        Aggregation aggregation = Aggregation.of(a -> a
                .terms(t -> t
                        .field(ENTITY_ID)
                        .size(MAX_SIZE)
                ).aggregations(EVENT_NUMBERS, Aggregation.of(b -> b.terms(t -> t.field(EVENT_NUMBER).size(AGG_FIELD_SIZE))))
        );
        aggregationMap.put(TABLE_IDS_EVENTS, aggregation);

        Query query = boolQuery._toQuery();
        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index(index)
                .size(0)
                .query(query).aggregations(aggregationMap)
        );
        Map<String, List<Integer>> tableIdAndEventIdsMap = new HashMap<>();

        try {
            org.opensearch.client.opensearch.core.SearchResponse<ObjectNode> response =
                    clientProvider.get().search(searchRequest, ObjectNode.class);

            List<StringTermsBucket> tableIdEvents = response.aggregations().get(TABLE_IDS_EVENTS).sterms().buckets().array();
            for (StringTermsBucket tableIdEvent : tableIdEvents) {
                String tableId = tableIdEvent.key();
                List<Integer> eventIds = new ArrayList<>();
                List<LongTermsBucket> events = tableIdEvent.aggregations().get(EVENT_NUMBERS).lterms().buckets().array();
                for (LongTermsBucket event : events) {
                    int eventId = Integer.parseInt(event.key());
                    eventIds.add(eventId);
                }
                tableIdAndEventIdsMap.put(tableId, eventIds);
            }
        } catch (IOException exception) {
            LOG.error("Error during getting TableIdAndEventIdsMap from event index ", exception);

        } catch (Throwable exception) {
            LOG.error("Unexpected exception {} ", exception.getMessage(), exception);
        }
        return tableIdAndEventIdsMap;
    }

    /
     */
}

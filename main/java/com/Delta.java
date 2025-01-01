package com;

public class Delta {

    public static void main(String[] args) {

        String line = "\n" +
                "{\"tableType\":\"EXTERNAL\",\"format\":\"delta\",\"name\":\"spark_catalog.workload_mt_delta.people10m_999\",\"numFiles\":10,\"clusteringColumns\":[],\"location\":\"dbfs:/tmp/delta/people-10m\",\"statistics\":{\"numRowsDeletedByDeletionVectors\":0,\"numDeletionVectors\":0},\"lastModified\":1713597294000,\"description\":null,\"partitionCount\":\"2\",\"partitionIds\":\"gender=F/,gender=M/,\",\"tableFeatures\":[\"deletionVectors\"],\"id\":\"19f55960-309a-4611-9474-e36d2916cadd\",\"partitionColumns\":[\"gender\"],\"minReaderVersion\":3,\"properties\":{\"delta.enableDeletionVectors\":\"true\"},\"sizeInBytes\":237353976,\"createdAt\":1713597252800,\"databaseName\":\"workload_mt_delta\",\"minWriterVersion\":7}";
    }

}

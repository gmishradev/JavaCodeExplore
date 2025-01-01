DBS             |
| PARTITIONS      |
| PARTITION_KEYS  |
| SDS             |
| TAB_COL_STATS   |
| TBLS

CREATE TABLE TBLS (
  TBL_ID INT NOT NULL,

  PRIMARY KEY (TBL_ID)
) ENGINE=InnoDB; -- specify the storage engine

CREATE INDEX TBLS_pkey ON TBLS (TBL_ID) USING BTREE;


ALTER TABLE TBLS ADD PRIMARY KEY (TBL_ID);

ALTER TABLE TBLS ADD CONSTRAINT TBLS_DB_ID_fkey
FOREIGN KEY (DB_ID) REFERENCES DBS(DB_ID);

ALTER TABLE TBLS ADD CONSTRAINT TBLS_SD_ID_fkey
FOREIGN KEY (SD_ID) REFERENCES SDS(SD_ID);

CREATE INDEX TBLS_pkey ON TBLS (TBL_ID) USING BTREE;
CREATE INDEX TBLS_N49 ON TBLS (DB_ID) USING BTREE;
CREATE INDEX TBLS_N50 ON TBLS (SD_ID) USING BTREE;


\d "TBLS"
                              Table "public.TBLS"
       Column       |          Type          |            Modifiers
--------------------+------------------------+---------------------------------
 TBL_ID             | bigint                 | not null
 CREATE_TIME        | bigint                 | not null
 DB_ID              | bigint                 |
 LAST_ACCESS_TIME   | bigint                 | not null
 OWNER              | character varying(767) | default NULL::character varying
 OWNER_TYPE         | character varying(10)  | default NULL::character varying
 RETENTION          | bigint                 | not null
 SD_ID              | bigint                 |
 TBL_NAME           | character varying(256) | default NULL::character varying
 TBL_TYPE           | character varying(128) | default NULL::character varying
 VIEW_EXPANDED_TEXT | text                   |
 VIEW_ORIGINAL_TEXT | text                   |
 IS_REWRITE_ENABLED | boolean                | not null default false
 WRITE_ID           | bigint                 | default 0
Indexes:
    "TBLS_pkey" PRIMARY KEY, btree ("TBL_ID")
    "UNIQUETABLE" UNIQUE CONSTRAINT, btree ("TBL_NAME", "DB_ID")
    "TBLS_N49" btree ("DB_ID")
    "TBLS_N50" btree ("SD_ID")
Foreign-key constraints:
    "TBLS_DB_ID_fkey" FOREIGN KEY ("DB_ID") REFERENCES "DBS"("DB_ID") DEFERRABLE
    "TBLS_SD_ID_fkey" FOREIGN KEY ("SD_ID") REFERENCES "SDS"("SD_ID") DEFERRABLE
Referenced by:
    TABLE ""IDXS"" CONSTRAINT "IDXS_INDEX_TBL_ID_fkey" FOREIGN KEY ("INDEX_TBL_ID") REFERENCES "TBLS"("TBL_ID") DEFERRABLE
    TABLE ""IDXS"" CONSTRAINT "IDXS_ORIG_TBL_ID_fkey" FOREIGN KEY ("ORIG_TBL_ID") REFERENCES "TBLS"("TBL_ID") DEFERRABLE
    TABLE ""MV_TABLES_USED"" CONSTRAINT "MV_TABLES_USED_FK2" FOREIGN KEY ("TBL_ID") REFERENCES "TBLS"("TBL_ID") DEFERRABLE
    TABLE ""PARTITIONS"" CONSTRAINT "PARTITIONS_TBL_ID_fkey" FOREIGN KEY ("TBL_ID") REFERENCES "TBLS"("TBL_ID") DEFERRABLE
    TABLE ""PARTITION_KEYS"" CONSTRAINT "PARTITION_KEYS_TBL_ID_fkey" FOREIGN KEY ("TBL_ID") REFERENCES "TBLS"("TBL_ID") DEFERRABLE
    TABLE ""TABLE_PARAMS"" CONSTRAINT "TABLE_PARAMS_TBL_ID_fkey" FOREIGN KEY ("TBL_ID") REFERENCES "TBLS"("TBL_ID") DEFERRABLE
    TABLE ""TAB_COL_STATS"" CONSTRAINT "TAB_COL_STATS_fkey" FOREIGN KEY ("TBL_ID") REFERENCES "TBLS"("TBL_ID") DEFERRABLE
    TABLE ""TBL_COL_PRIVS"" CONSTRAINT "TBL_COL_PRIVS_TBL_ID_fkey" FOREIGN KEY ("TBL_ID") REFERENCES "TBLS"("TBL_ID") DEFERRABLE
    TABLE ""TBL_PRIVS"" CONSTRAINT "TBL_PRIVS_TBL_ID_fkey" FOREIGN KEY ("TBL_ID") REFERENCES "TBLS"("TBL_ID") DEFERRABLE


hive1=> \d "DBS"
                                 Table "public.DBS"
         Column          |          Type           |            Modifiers

-------------------------+-------------------------+----------------------------
-----
 DB_ID                   | bigint                  | not null
 DESC                    | character varying(4000) | default NULL::character var
ying
 DB_LOCATION_URI         | character varying(4000) | not null
 NAME                    | character varying(128)  | default NULL::character var
ying
 OWNER_NAME              | character varying(128)  | default NULL::character var
ying
 OWNER_TYPE              | character varying(10)   | default NULL::character var
ying
 CTLG_NAME               | character varying(256)  |
 CREATE_TIME             | bigint                  |
 DB_MANAGED_LOCATION_URI | character varying(4000) |
Indexes:
    "DBS_pkey" PRIMARY KEY, btree ("DB_ID")
    "UNIQUE_DATABASE" UNIQUE CONSTRAINT, btree ("NAME", "CTLG_NAME")
Foreign-key constraints:
    "DBS_FK1" FOREIGN KEY ("CTLG_NAME") REFERENCES "CTLGS"("NAME")
Referenced by:
    TABLE ""DATABASE_PARAMS"" CONSTRAINT "DATABASE_PARAMS_DB_ID_fkey" FOREIGN KEY ("DB_ID") REFERENCES "DBS"("DB_ID") DEFERRABLE
    TABLE ""DB_PRIVS"" CONSTRAINT "DB_PRIVS_DB_ID_fkey" FOREIGN KEY ("DB_ID") REFERENCES "DBS"("DB_ID") DEFERRABLE
    TABLE ""FUNCS"" CONSTRAINT "FUNCS_FK1" FOREIGN KEY ("DB_ID") REFERENCES "DBS"("DB_ID") DEFERRABLE
    TABLE ""I_SCHEMA"" CONSTRAINT "I_SCHEMA_DB_ID_fkey" FOREIGN KEY ("DB_ID") REFERENCES "DBS"("DB_ID")
    TABLE ""TBLS"" CONSTRAINT "TBLS_DB_ID_fkey" FOREIGN KEY ("DB_ID") REFERENCES "DBS"("DB_ID") DEFERRABLE

ALTER TABLE DBS ADD PRIMARY KEY (DB_ID);
CREATE INDEX DBS_pkey ON DBS (DB_ID) USING BTREE;


\d  "PARTITIONS"
                          Table "public.PARTITIONS"
      Column      |          Type          |            Modifiers
------------------+------------------------+---------------------------------
 PART_ID          | bigint                 | not null
 CREATE_TIME      | bigint                 | not null
 LAST_ACCESS_TIME | bigint                 | not null
 PART_NAME        | character varying(767) | default NULL::character varying
 SD_ID            | bigint                 |
 TBL_ID           | bigint                 |
 WRITE_ID         | bigint                 | default 0
Indexes:
    "PARTITIONS_pkey" PRIMARY KEY, btree ("PART_ID")
    "UNIQUEPARTITION" UNIQUE CONSTRAINT, btree ("PART_NAME", "TBL_ID")
    "PARTITIONS_N49" btree ("TBL_ID")
    "PARTITIONS_N50" btree ("SD_ID")
Foreign-key constraints:
    "PARTITIONS_SD_ID_fkey" FOREIGN KEY ("SD_ID") REFERENCES "SDS"("SD_ID") DEFE
RRABLE
    "PARTITIONS_TBL_ID_fkey" FOREIGN KEY ("TBL_ID") REFERENCES "TBLS"("TBL_ID")
DEFERRABLE
Referenced by:
    TABLE ""PARTITION_KEY_VALS"" CONSTRAINT "PARTITION_KEY_VALS_PART_ID_fkey" FO
REIGN KEY ("PART_ID") REFERENCES "PARTITIONS"("PART_ID") DEFERRABLE
    TABLE ""PARTITION_PARAMS"" CONSTRAINT "PARTITION_PARAMS_PART_ID_fkey" FOREIG
N KEY ("PART_ID") REFERENCES "PARTITIONS"("PART_ID") DEFERRABLE
    TABLE ""PART_COL_PRIVS"" CONSTRAINT "PART_COL_PRIVS_PART_ID_fkey" FOREIGN KEY ("PART_ID") REFERENCES "PARTITIONS"("PART_ID") DEFERRABLE
    TABLE ""PART_COL_STATS"" CONSTRAINT "PART_COL_STATS_fkey" FOREIGN KEY ("PART_ID") REFERENCES "PARTITIONS"("PART_ID") DEFERRABLE
    TABLE ""PART_PRIVS"" CONSTRAINT "PART_PRIVS_PART_ID_fkey" FOREIGN KEY ("PART_ID") REFERENCES "PARTITIONS"("PART_ID") DEFERRABLE


ALTER TABLE PARTITIONS ADD PRIMARY KEY (PART_ID);

ALTER TABLE PARTITIONS ADD CONSTRAINT PARTITIONS_SD_ID_fkey
FOREIGN KEY (SD_ID) REFERENCES SDS(SD_ID);

ALTER TABLE PARTITIONS ADD CONSTRAINT PARTITIONS_TBL_ID_fkey
FOREIGN KEY (TBL_ID) REFERENCES TBLS(TBL_ID);

CREATE INDEX PARTITIONS_pkey ON PARTITIONS (PART_ID) USING BTREE;
CREATE INDEX PARTITIONS_N49 ON PARTITIONS (DB_ID) USING BTREE;
CREATE INDEX PARTITIONS_N50 ON PARTITIONS (SD_ID) USING BTREE;



 \d "PARTITION_KEYS"
                      Table "public.PARTITION_KEYS"
    Column    |          Type           |            Modifiers
--------------+-------------------------+---------------------------------
 TBL_ID       | bigint                  | not null
 PKEY_COMMENT | character varying(4000) | default NULL::character varying
 PKEY_NAME    | character varying(128)  | not null
 PKEY_TYPE    | character varying(767)  | not null
 INTEGER_IDX  | bigint                  | not null
Indexes:
    "PARTITION_KEYS_pkey" PRIMARY KEY, btree ("TBL_ID", "PKEY_NAME")
    "PARTITION_KEYS_N49" btree ("TBL_ID")
Foreign-key constraints:
    "PARTITION_KEYS_TBL_ID_fkey" FOREIGN KEY ("TBL_ID") REFERENCES "TBLS"("TBL_ID") DEFERRABLE

ALTER TABLE PARTITION_KEYS_pkey ADD PRIMARY KEY (TBL_ID, PKEY_NAME);

ALTER TABLE PARTITION_KEYS ADD CONSTRAINT PARTITION_KEYS_TBL_ID_fkey
FOREIGN KEY (TBL_ID) REFERENCES TBLS(TBL_ID);

CREATE INDEX PARTITION_KEYS_pkey ON PARTITION_KEYS (TBL_ID,PKEY_NAME) USING BTREE;
CREATE INDEX PARTITION_KEYS_N49 ON PARTITION_KEYS (TBL_ID) USING BTREE;




 \d  "SDS"
                                  Table "public.SDS"
          Column           |          Type           |            Modifiers

---------------------------+-------------------------+--------------------------
-------
 SD_ID                     | bigint                  | not null
 INPUT_FORMAT              | character varying(4000) | default NULL::character v
arying
 IS_COMPRESSED             | boolean                 | not null
 LOCATION                  | character varying(4000) | default NULL::character v
arying
 NUM_BUCKETS               | bigint                  | not null
 OUTPUT_FORMAT             | character varying(4000) | default NULL::character v
arying
 SERDE_ID                  | bigint                  |
 CD_ID                     | bigint                  |
 IS_STOREDASSUBDIRECTORIES | boolean                 | not null
Indexes:
    "SDS_pkey" PRIMARY KEY, btree ("SD_ID")
    "SDS_N49" btree ("SERDE_ID")
Foreign-key constraints:
    "SDS_CD_ID_fkey" FOREIGN KEY ("CD_ID") REFERENCES "CDS"("CD_ID") DEFERRABLE
    "SDS_SERDE_ID_fkey" FOREIGN KEY ("SERDE_ID") REFERENCES "SERDES"("SERDE_ID")
 DEFERRABLE
Referenced by:
    TABLE ""BUCKETING_COLS"" CONSTRAINT "BUCKETING_COLS_SD_ID_fkey" FOREIGN KEY
("SD_ID") REFERENCES "SDS"("SD_ID") DEFERRABLE
    TABLE ""IDXS"" CONSTRAINT "IDXS_SD_ID_fkey" FOREIGN KEY ("SD_ID") REFERENCES
 "SDS"("SD_ID") DEFERRABLE
    TABLE ""PARTITIONS"" CONSTRAINT "PARTITIONS_SD_ID_fkey" FOREIGN KEY ("SD_ID"
) REFERENCES "SDS"("SD_ID") DEFERRABLE
    TABLE ""SD_PARAMS"" CONSTRAINT "SD_PARAMS_SD_ID_fkey" FOREIGN KEY ("SD_ID")
REFERENCES "SDS"("SD_ID") DEFERRABLE
    TABLE ""SKEWED_COL_NAMES"" CONSTRAINT "SKEWED_COL_NAMES_fkey" FOREIGN KEY ("
SD_ID") REFERENCES "SDS"("SD_ID") DEFERRABLE
    TABLE ""SKEWED_COL_VALUE_LOC_MAP"" CONSTRAINT "SKEWED_COL_VALUE_LOC_MAP_fkey
1" FOREIGN KEY ("SD_ID") REFERENCES "SDS"("SD_ID") DEFERRABLE
    TABLE ""SKEWED_VALUES"" CONSTRAINT "SKEWED_VALUES_fkey2" FOREIGN KEY ("SD_ID
_OID") REFERENCES "SDS"("SD_ID") DEFERRABLE
    TABLE ""SORT_COLS"" CONSTRAINT "SORT_COLS_SD_ID_fkey" FOREIGN KEY ("SD_ID")
REFERENCES "SDS"("SD_ID") DEFERRABLE
    TABLE ""TBLS"" CONSTRAINT "TBLS_SD_ID_fkey" FOREIGN KEY ("SD_ID") REFERENCES "SDS"("SD_ID") DEFERRABLE


ALTER TABLE SDS ADD PRIMARY KEY (SD_ID);

ALTER TABLE SDS ADD CONSTRAINT SDS_CD_ID_fkey
FOREIGN KEY (CD_ID) REFERENCES CDS(CD_ID);

CREATE INDEX SDS_pkey ON SDS (SD_ID) USING BTREE;
CREATE INDEX SDS_N49 ON SDS (SERDE_ID) USING BTREE;



\d  "TAB_COL_STATS"
                            Table "public.TAB_COL_STATS"
         Column         |          Type           |            Modifiers

------------------------+-------------------------+-----------------------------
----
 CS_ID                  | bigint                  | not null
 CAT_NAME               | character varying(256)  | default NULL::character vary
ing
 DB_NAME                | character varying(128)  | default NULL::character vary
ing
 TABLE_NAME             | character varying(256)  | default NULL::character vary
ing
 COLUMN_NAME            | character varying(767)  | default NULL::character vary
ing
 COLUMN_TYPE            | character varying(128)  | default NULL::character vary
ing
 TBL_ID                 | bigint                  | not null
 LONG_LOW_VALUE         | bigint                  |
 LONG_HIGH_VALUE        | bigint                  |
 DOUBLE_LOW_VALUE       | double precision        |
 DOUBLE_HIGH_VALUE      | double precision        |
 BIG_DECIMAL_LOW_VALUE  | character varying(4000) | default NULL::character vary
ing
 BIG_DECIMAL_HIGH_VALUE | character varying(4000) | default NULL::character varying
 NUM_NULLS              | bigint                  | not null
 NUM_DISTINCTS          | bigint                  |
 BIT_VECTOR             | bytea                   |
 AVG_COL_LEN            | double precision        |
 MAX_COL_LEN            | bigint                  |
 NUM_TRUES              | bigint                  |
 NUM_FALSES             | bigint                  |
 LAST_ANALYZED          | bigint                  | not null
 ENGINE                 | character varying(128)  | not null
Indexes:
    "TAB_COL_STATS_pkey" PRIMARY KEY, btree ("CS_ID")
    "TAB_COL_STATS_IDX" btree ("CAT_NAME", "DB_NAME", "TABLE_NAME", "COLUMN_NAME")
    "TAB_COL_STATS_N49" btree ("TBL_ID")
Foreign-key constraints:
    "TAB_COL_STATS_fkey" FOREIGN KEY ("TBL_ID") REFERENCES "TBLS"("TBL_ID") DEFERRABLE



ALTER TABLE TAB_COL_STATS ADD PRIMARY KEY (CS_ID);

ALTER TABLE TAB_COL_STATS ADD CONSTRAINT TAB_COL_STATS_fkey
FOREIGN KEY (TBL_ID) REFERENCES TBLS(TBL_ID);

CREATE INDEX TAB_COL_STATS_pkey ON TAB_COL_STATS (CS_ID) USING BTREE;
CREATE INDEX TAB_COL_STATS_N49 ON TAB_COL_STATS (TBL_ID) USING BTREE;







ALTER TABLE TBLS ADD PRIMARY KEY (TBL_ID);

ALTER TABLE TBLS ADD CONSTRAINT TBLS_DB_ID_fkey
FOREIGN KEY (DB_ID) REFERENCES DBS(DB_ID);

ALTER TABLE TBLS ADD CONSTRAINT TBLS_SD_ID_fkey
FOREIGN KEY (SD_ID) REFERENCES SDS(SD_ID);

CREATE INDEX TBLS_pkey ON TBLS (TBL_ID) USING BTREE;
CREATE INDEX TBLS_N49 ON TBLS (DB_ID) USING BTREE;
CREATE INDEX TBLS_N50 ON TBLS (SD_ID) USING BTREE;

ALTER TABLE DBS ADD PRIMARY KEY (DB_ID);
CREATE INDEX DBS_pkey ON DBS (DB_ID) USING BTREE;


ALTER TABLE PARTITIONS ADD PRIMARY KEY (PART_ID);

ALTER TABLE PARTITIONS ADD CONSTRAINT PARTITIONS_SD_ID_fkey
FOREIGN KEY (SD_ID) REFERENCES SDS(SD_ID);

ALTER TABLE PARTITIONS ADD CONSTRAINT PARTITIONS_TBL_ID_fkey
FOREIGN KEY (TBL_ID) REFERENCES TBLS(TBL_ID);

CREATE INDEX PARTITIONS_pkey ON PARTITIONS (PART_ID) USING BTREE;
CREATE INDEX PARTITIONS_N49 ON PARTITIONS (DB_ID) USING BTREE;
CREATE INDEX PARTITIONS_N50 ON PARTITIONS (SD_ID) USING BTREE;


ALTER TABLE PARTITION_KEYS_pkey ADD PRIMARY KEY (TBL_ID, PKEY_NAME);

ALTER TABLE PARTITION_KEYS ADD CONSTRAINT PARTITION_KEYS_TBL_ID_fkey
FOREIGN KEY (TBL_ID) REFERENCES TBLS(TBL_ID);

CREATE INDEX PARTITION_KEYS_pkey ON PARTITION_KEYS (TBL_ID,PKEY_NAME) USING BTREE;
CREATE INDEX PARTITION_KEYS_N49 ON PARTITION_KEYS (TBL_ID) USING BTREE;


ALTER TABLE SDS ADD PRIMARY KEY (SD_ID);

ALTER TABLE SDS ADD CONSTRAINT SDS_CD_ID_fkey
FOREIGN KEY (CD_ID) REFERENCES CDS(CD_ID);

CREATE INDEX SDS_pkey ON SDS (SD_ID) USING BTREE;
CREATE INDEX SDS_N49 ON SDS (SERDE_ID) USING BTREE;


ALTER TABLE TAB_COL_STATS ADD PRIMARY KEY (CS_ID);

ALTER TABLE TAB_COL_STATS ADD CONSTRAINT TAB_COL_STATS_fkey
FOREIGN KEY (TBL_ID) REFERENCES TBLS(TBL_ID);

CREATE INDEX TAB_COL_STATS_pkey ON TAB_COL_STATS (CS_ID) USING BTREE;
CREATE INDEX TAB_COL_STATS_N49 ON TAB_COL_STATS (TBL_ID) USING BTREE;




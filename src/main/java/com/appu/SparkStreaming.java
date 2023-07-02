package com.appu;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;

import java.util.concurrent.TimeoutException;

public class SparkStreaming {
    public static void main(String[] args) throws TimeoutException, StreamingQueryException {

      SparkSession spark = SparkSession.builder().master("local").appName("Temperature Analysis").getOrCreate();

        Dataset<Row> df = spark
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "192.168.1.42:9092")
                .option("subscribe", "input_topic")
                .load();
        df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)");
        //df.show();

        StreamingQuery ds = df
                .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
                .writeStream()
                .format("kafka")
                .option("checkpointLocation", "/tmp/checkpoint")
                .option("kafka.bootstrap.servers", "192.168.1.42:9092")
                .option("topic", "output_topic")
                .start();

        ds.awaitTermination();

    }



}
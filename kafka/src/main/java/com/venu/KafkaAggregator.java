package com.venu;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KGroupedStream;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;

public class KafkaAggregator {

    public static void main (String[] args) {

        Properties props = new Properties();

        //Assign localhost id
        props.put("bootstrap.servers", "localhost:9092");

        //Set acknowledgements for producer requests.
        props.put("acks", "all");

        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);

        //Specify buffer size in config
        props.put("batch.size", 16384);

        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);

        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);

        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        props.put("application.id", "aggregator");

        props.put("group.id", "test");


        StreamsBuilder streamBuilder = new StreamsBuilder();

StoreBuilder<KeyValueStore<String, Long>> countStoreSupplier =
                Stores.keyValueStoreBuilder(
                        Stores.persistentKeyValueStore("persistent-counts"),
                        Serdes.String(),
                        Serdes.Long());
// register store
        streamBuilder.addStateStore(countStoreSupplier);


        KStream<String, String> ks0 = streamBuilder.stream("test-topic");


        KGroupedStream<String, String> kGS1 = ks0.groupByKey();




        KTable<String, Long> kt1 = kGS1.count();

        kt1.toStream().print(Printed.<String,Long>toSysOut().withLabel("KT1"));



        KafkaStreams streams = new KafkaStreams(streamBuilder.build(), props);
        streams.start();

      //  Runtime.getRuntime().addShutdownHook(new Thread(()-> {streams.close();)});

    }


}

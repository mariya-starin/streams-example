package org.example;

import datagen.example.transactions;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import ksql.users;
import newschema.joined;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.example.utils.Globals;
import org.example.utils.Utils;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SpecificAvroJoinedExample {
    public static void main(final String[] args) {
        String mode = "default";
        if(args.length!=0){
            mode=args[0];
        }

        Properties props = Utils.loadProps("src/main/resources/kafka/application-%s.properties".formatted(mode));
        System.out.println("Using default %s".formatted(mode));

        final KafkaStreams streams = buildSampleStream(props);
        try{
            streams.start();
            System.out.println("streams started");
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

    static KafkaStreams buildSampleStream(Properties streamsConfiguration) {

        streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);

        StreamsBuilder builder = new StreamsBuilder();

        Serde<transactions> transactionsSerde = new SpecificAvroSerde<>();
        Serde<users> usersSerde = new SpecificAvroSerde<>();
        Serde<joined> joinedSerde = new SpecificAvroSerde<>();

        Map<String, String> schemaConfig = new HashMap<>();
        streamsConfiguration.forEach((key,value) -> schemaConfig.put(key.toString(),value.toString()));

        transactionsSerde.configure(schemaConfig, false);
        usersSerde.configure(schemaConfig,false);
        joinedSerde.configure(schemaConfig,false);

        GlobalKTable<String, users> users = builder.globalTable(streamsConfiguration.get(
                Globals.SOURCE_ONE_TOPIC).toString(),
                Materialized.<String, users, KeyValueStore<Bytes, byte[]>>as("users-global-table").
                        withKeySerde(Serdes.String()).
                        withValueSerde(usersSerde));

        KStream<String, transactions> transactions = builder.stream(streamsConfiguration.get(
                Globals.SOURCE_TWO_TOPIC).toString(),
                Consumed.with(Serdes.String(), transactionsSerde));

        ValueJoiner<transactions, users, joined> valueJoiner =
                (transaction, user) -> joined.newBuilder()
                        .setCardId(transaction.getCardId())
                        .setGender(user.getGender())
                        .setPurchaseId(transaction.getPurchaseId())
                        .setRegionid(user.getRegionid())
                        .setRegistertime(user.getRegistertime())
                        .setStoreId(transaction.getStoreId())
                        .setTransactionId(transaction.getTransactionId())
                        .setUserId(user.getUserid())
                        .build();

        KeyValueMapper<String, transactions, String> keyValueMapper = (key, transaction) -> transaction.getUserId();

        transactions.join(users, keyValueMapper, valueJoiner)
                .peek((k,v) -> System.out.println("Peeked " + k + " with value " + v))
        .to(streamsConfiguration.get(Globals.JOINED_TOPIC).toString(), Produced.with(Serdes.String(),joinedSerde));

        return new KafkaStreams(builder.build(), streamsConfiguration);
    }
}
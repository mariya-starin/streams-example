package org.example;

import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import ksql.users;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.example.utils.Globals;
import org.example.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SpecificAvroExample {
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

        Serde<users> usersSerde = new SpecificAvroSerde<>();
        Map<String, String> schemaConfig = new HashMap<>();
        streamsConfiguration.forEach((key,value) -> schemaConfig.put(key.toString(),value.toString()));
        usersSerde.configure(schemaConfig, false);

        KStream<String, users> users = builder.stream(
                streamsConfiguration.get(Globals.SOURCE_TOPIC).toString(), Consumed.with(Serdes.String(), usersSerde));
        users.print(Printed.toSysOut());

        users.to(streamsConfiguration.get(Globals.SINK_TOPIC).toString(), Produced.with(Serdes.String(),usersSerde));

        return new KafkaStreams(builder.build(), streamsConfiguration);
    }

}
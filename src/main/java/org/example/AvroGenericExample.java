package org.example;

import io.confluent.kafka.streams.serdes.avro.GenericAvroSerde;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.example.utils.*;
import java.util.Properties;

public class AvroGenericExample {

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

        final StreamsBuilder builder = new StreamsBuilder();
        streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, GenericAvroSerde.class);

        Serde userSerde = new GenericAvroSerde();
        userSerde.configure(streamsConfiguration, false);

        final KStream<String, GenericRecord> avroStream = builder.stream(
                streamsConfiguration.get(Globals.SOURCE_TOPIC).toString(),
                Consumed.with(Serdes.String(), userSerde));
        avroStream.print(Printed.toSysOut());
        avroStream.map((key,value) -> {
            System.out.println("key: "+key);
            String val = value.toString();
            System.out.println("schema: "+value.getSchema().toString());
            System.out.println("val: "+val);
            return new KeyValue<>(key,value);
        }).to(streamsConfiguration.get(Globals.SINK_TOPIC).toString(), Produced.with(Serdes.String(), userSerde));

        return new KafkaStreams(builder.build(), streamsConfiguration);
    }

}
package org.example;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.example.utils.*;
import java.util.Properties;

public class Example {
    public static void main(final String[] args) {

        String mode = "default";
        if(args.length!=0){
            mode=args[0];
        }

        Properties props = Utils.loadProps("src/main/resources/kafka/application-%s.properties".formatted(mode));
        System.out.println("Using default %".formatted(mode));

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

        final KStream<String, String> stream = builder.stream(streamsConfiguration.get(Globals.SOURCE_TOPIC).toString(),
                Consumed.with(Serdes.String(), Serdes.String()));

        stream.print(Printed.toSysOut());
        stream.to(streamsConfiguration.get(Globals.SINK_TOPIC).toString());

        return new KafkaStreams(builder.build(), streamsConfiguration);
    }
}
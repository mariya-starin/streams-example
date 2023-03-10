package consumer.src.main.java.clients;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

public class PageviewsConsumer {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("*** Starting Consumer ***");

        //******** Configuring the Kafka Consumer
        Properties settings = new Properties();
        settings.put(ConsumerConfig.GROUP_ID_CONFIG, "views-consumer");
        settings.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"broker:29092");
        settings.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        settings.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        settings.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        settings.put("schema.registry.url","http://schema-registry:8081");

        //******** Create a consumer instance using the above configuration setting
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(settings);

        try {
            //******* Subscribing the consumer to the desired topics
            consumer.subscribe(Arrays.asList("pageviews"));

            //******* Polling Kafka for new records (forever!)
            while (true) {
                //******** Get the next batch of records from the broker(s)
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                //******** Output all the records retrieved from the broker to STDOUT
                for (ConsumerRecord<String, String> record : records)
                    System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());

                //******* Make the consumer artificially slow
                TimeUnit.MILLISECONDS.sleep(1000);
            }
        }
        finally {
            System.out.println("*** Ending Consumer ***");
            consumer.close();
        }
    }
}
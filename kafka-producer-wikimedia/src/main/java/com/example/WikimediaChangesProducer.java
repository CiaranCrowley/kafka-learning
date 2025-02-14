package com.example;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    private KafkaTemplate<String, String> KafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        KafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {

        String topic = "wikimedia_change_stream";

        // use event source to get real time stream data from wikimedia
        EventHandler eventHandler = new WikimediaChangesHandler(KafkaTemplate, topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";

        // create an event source builder
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource = builder.build();
        // start it
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}

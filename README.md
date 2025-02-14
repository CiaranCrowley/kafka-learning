# Kafka

## Table of Contents
- [Running the App](#running-the-app)
- [Kafka Listener in Spring Boot](#kafka-listener-in-spring-boot)
  - [What is a Kafka Listener](#what-is-a-kafka-listener)
  - [How Kafka Listener Works](#how-kafka-listener-works)
  - [Example of a Kafka Listener in Spring Boot](#example-of-a-kafka-listener-in-spring-boot)
  - [Breaking it Down](#breaking-it-down)
  - [Key Concepts](#key-concepts)

## Running the App
In two terminal windows/tabs, run the following commands
> ensure you are in the directory of Kafka first
```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
```

In your second terminal, run
```bash
bin/kafka-server-start.sh config/server.properties
```

## Kafka Listener in Spring Boot

### What is a Kafka Listener

A **Kafka Listener in Spring Boot** is a method that listens to messages from a Kafka topic.  It is typically used to consume messages produced by other services.

### How Kafka Listener Works

1. **Producer** sends messages to a kafka topic.
2. **Kafka Broker stores** these messages.
3. **Consumer (Listener)** reads messages from the topic and processes them.

In Spring Boot, you define a Kafka Listener using the `@KafkaListener` annotation.

### Use cases of a Kafka Listener

1. Event-Driven Microservices
    - Listens for events from other microservices and processes them.
    - Example: An order service listens for new payment confirmation events.
2. Real-Time Data Processing
    - Consumes and processes streaming data.
    - Example: A log processing service listens for log messages from various applications.
3. Message Queue Handling
    - Acts as a queue system to handle large volumes of messages.
    - Example: A notification service listens for user events and sends emails or SMS.
4. Decoupled System Communication
    - Services communicate indirectly via Kafka without direct dependencies.
    - Example: A user service listens for profile updates from an authentication service.

### Example of a Kafka Listener in Spring Boot

Here's a basic example:

```java
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void listen(String message) {
        System.out.println("Received Message: " + message);
    }
}
```

### Breaking it Down

- `@KafkaListener(topics = "my-topic", groupId = "my-group")`
  - Listens to messages from `my-topic` Kafka topic.
  - The `groupId` is used to manage consumer grouops (multiple consumers can work together)

- `listen(String message)`
  - This method gets executed whenever a message is received.
  - The message content is passed as a parameter.

********

### Key Concepts

- Kafka Producer: Sends messages to Kafka.
- Kafka Consumer (Listener): Reads messages from Kafka.
- Kafka Broker: Manages the topics and message storage.
- Consumer Groups: Ensures load balancing across multiple consumers.

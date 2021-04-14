# stream-processing-workshop

## Getting Started

1. Connect to your kafka container
```
  > docker ps
  > docker exec -it kafka /bin/bash

```
2. You are now in the shell of your docker container

```
  > cd /
  > ls
```

## Create a topic

Create a simple topic:
```
  > ./kafka-topics --create --topic test-topic --bootstrap-server localhost:9092
  
```

Verify the status of that topic:

```
  > ./kafka-topics --describe --topic test-topic --bootstrap-server localhost:9092
  
```

You can also retrieve a list from kafka-topics from zookeeper:
```
  > ./kafka-topics --list --bootstrap-server localhost:9092

```


## Produce messages to Kafka
```
  > ./kafka-console-producer --broker-list localhost:9092 --topic test-topic
This is a message
This is another message
```


## Consume messages
Open a new shell to start the consumer :
```
  > docker ps
  > docker exec -it kafka /bin/bash

  > cd /
  > ls
```

First, lets consume the message you produced just know.


```
  > ./kafka-console-consumer --topic test-topic --from-beginning --bootstrap-server localhost:9092

```

From a given point.
First we will retrieve the comitted offsets of every partition.

```
  > ./kafka-run-class kafka.tools.GetOffsetShell --broker-list localhost:9092 --topic test-topic --time -1
  
```
Now you can see the various offsets per partition.


Then we will pass along the offset:
```
  > ./kafka-console-consumer --topic test-topic --bootstrap-server localhost:9092 --partition 0 --offset 1
  
```
This will consume all messages, except the first one as we start from the offset with index 1.


Keep this consumer open and produce some extra messages and see the effect, your consumer should show you that result.

## Create a topic with multiple partions / replicas
Lets create a new topic with multiple partitions and replicas.

```
  > ./kafka-topics --create --topic topic-to-replicate-and-partition --bootstrap-server localhost:9092 --replication-factor 2 --partitions 10
  
```

This will fail, because we only have 1 broker running.

```
  > ./kafka-topics --create --topic topic-to-replicate-and-partition --bootstrap-server localhost:9092 --replication-factor 1 --partitions 10
  
```

See the status of that topic:

```
  > ./kafka-topics --describe --topic topic-to-replicate-and-partition --bootstrap-server localhost:9092
  
```
You can also see that we have much more partitions each with their own offset:
```
  > ./kafka-run-class kafka.tools.GetOffsetShell --broker-list localhost:9092 --topic topic-to-replicate-and-partition --time -1
  
```

If you now start to produce messages to that topic you will see that these will be divided over multiple partitions.

## See the status of the various consumer-groups using your kafka.

See the status of the consumers - now this will not show you much, however as soon as you start using the Spring Boot projects it will show you more info:

```
  > ./kafka-consumer-groups --bootstrap-server localhost:9092 --all-groups --describe
  
```




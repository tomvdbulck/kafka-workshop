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

What do we see?


## Produce messages to Kafka

## Consume messages
open a new shell

from beginning 

from a given point 

produce a 2nd message


## Create a topic with multiple partions / replicas







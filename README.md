# kafka-basic
It is a simple **Kafka** example and explication  how **Kafka** works.

This example has two parts:
    1) **doc**: A explication about the main concepts for start with **Kafka**.
    2) **source**: Sprig-boot project with an basic example.

### 1. Main Kafka command

  **Start Zookeeper**
      bin/zookeeper-server-start.sh config/zookeeper.properties
  
  **Start Brokers** (three instances)
     bin/kafka-server-start.sh config/server-0.properties
 	   bin/kafka-server-start.sh config/server-1.properties
	   bin/kafka-server-start.sh config/server-2.properties 
  
  **Create Topic**
      bin/kafka-topics.sh --create --zookeeper localhost:2181 --partitions 1 --replication-factor 1 --topic demo
  
  **Delete Topic**
      bin/kafka-topics.sh --delete --zookeeper localhost:2181  --topic demo
       
  **Inspect Topic**
      bin/kafka-topics.sh --describe --zookeeper localhost:2181  --topic demo
  
  **Start Consummer from console**
      bin/kafka-console-consumer.sh --bootstrap-server localhost:9092,localhost:9093,localhost:9094 --topic demo --group topic_group
  
  **Inspect Consummers Group**
      bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092,localhost:9093,localhost:9094 --group topic_group --describe
  
  **Start Producer**
       bin/kafka-console-producer.sh --broker-list localhost:9092,localhost:9093,localhost:9094  --topic demo



### 2. ...

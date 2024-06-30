 # Kafka Temperature Analytics
Alternate approach to Kstreams using spark. The temperature status is calculated using predefined values.

Architecture:

[![CodeQL](https://github.com/appuv/KafkaTemperatureAnalyticsSpark/actions/workflows/github-code-scanning/codeql/badge.svg)](https://github.com/appuv/KafkaTemperatureAnalyticsSpark/actions/workflows/github-code-scanning/codeql) [![Java CI with Maven](https://github.com/appuv/KafkaTemperatureAnalyticsSpark/actions/workflows/maven.yml/badge.svg)](https://github.com/appuv/KafkaTemperatureAnalyticsSpark/actions/workflows/maven.yml) [![License](https://img.shields.io/github/license/appuv/KafkaTemperatureAnalyticsSpark)](https://github.com/appuv/KafkaTemperatureAnalyticsSpark/blob/main/LICENSE) [![GitHub top language](https://img.shields.io/github/languages/top/appuv/KafkaTemperatureAnalyticsSpark)]([https://github.com/appuv/KafkaTemperatureAnalyticsSpark](https://img.shields.io/github/languages/top/appuv/KafkaTemperatureAnalyticsSpark))

## Prerequisite
1. [Java 17](https://www.azul.com/downloads/?version=java-17-lts&package=jdk)
2. [Docker](https://www.docker.com/)   
3. [Confluent Kafka 6.2.0](https://docs.confluent.io/platform/current/quickstart/ce-docker-quickstart.html)
4. [Maven](https://maven.apache.org/)


## Getting Started
To run the application,
Build the maven project

```
mvn clean install  
```

## Run the project


Create dummy data by [Datagen](https://github.com/appuv/KafkaDataGen) or by console producer as below

```
kafka-console-producer.bat --bootstrap-server localhost:9092 --topic spark_source --property "parse.key=true" --property "key.separator=|"
{"serial" : "1"}|{"serial":"1","owner":"appu","temp":"25","location":"earth"}  //normal
{"serial" : "1"}|{"serial":"1","owner":"appu","temp":"66","location":"earth"}  //hot
{"serial" : "1"}|{"serial":"1","owner":"appu","temp":"-4","location":"earth"} //cold
{"serial" : "1"}|{"serial":"1","owner":"appu","temp":"99","location":"earth"} //invalid
```

The output can be observed by consuming the destination topic
```
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic spark_destination --property print.key=true
```


intellij (After enabling vm options)

--add-exports java.base/sun.nio.ch=ALL-UNNAMED"











#!/bin/bash

mvn clean compile
mvn package

mode=$1

echo "mode $mode"

if [ "$mode" = "generic" ]
then
  java -cp target/example-1.0-SNAPSHOT-jar-with-dependencies.jar src/main/java/org/example/AvroGenericExample.java avro-generic
fi

if [ "$mode" = "specific" ]
then
  java -cp target/example-1.0-SNAPSHOT-jar-with-dependencies.jar src/main/java/org/example/SpecificAvroExample.java avro-generic
fi

if [ "$mode" = "joined" ]
then
  java -cp target/example-1.0-SNAPSHOT-jar-with-dependencies.jar src/main/java/org/example/SpecificAvroJoinedExample.java avro-joined
fi

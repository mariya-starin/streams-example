#!/bin/bash

mvn clean compile
mvn package

java -cp target/example-1.0-SNAPSHOT-jar-with-dependencies.jar src/main/java/org/example/Example.java

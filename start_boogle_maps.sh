#!/bin/bash
clear
cd boogle-maps
mvn clean package
java -jar target/boogle-maps-0.0.1-SNAPSHOT.jar


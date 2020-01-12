#!/bin/bash
clear
cd pricing-service
mvn clean package
java -jar target/pricing-service-0.0.1-SNAPSHOT.jar


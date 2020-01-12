#!/bin/bash
clear
cd eureka
mvn clean package
java -jar target/eureka-0.0.1-SNAPSHOT.jar


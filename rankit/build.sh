#!/bin/bash
docker run -it --rm --name my-maven-project -v "$PWD":/usr/src/mymaven -w /usr/src/mymaven maven:3.3.3-jdk-8 mvn clean install

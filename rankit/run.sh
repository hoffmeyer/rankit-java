#!/bin/bash
docker run -it -v $(pwd)/target/:/usr/local/tomcat/webapps/target --rm -p 8888:8080 tomcat:8.5-jre8

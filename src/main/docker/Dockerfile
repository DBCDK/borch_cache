FROM docker.dbc.dk/payara5-micro:latest
MAINTAINER Team Iscrum, iscrum@dbc.dk

# Next three is for debugging.
# USER root
# RUN apt-get update && DEBIAN_FRONTEND=noninteractive apt-get -qyy install sudo dnsutils iputils-ping telnet
# USER gfish


ADD target/borchk-java-1.0.0-SNAPSHOT.war target/docker/config.json deployments/

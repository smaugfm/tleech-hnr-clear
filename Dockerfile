FROM maven:3-eclipse-temurin-17 as build
ENV HOME=/home/usr
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
RUN mvn dependency:copy-dependencies
ADD src $HOME/src
RUN mvn package

FROM mcr.microsoft.com/playwright/java:v1.43.0-jammy as cron
ARG tleech_user
ARG tleech_password
COPY --from=build /home/usr/target/*.jar /
#Install Cron
RUN apt-get update
RUN apt-get -y install cron
# Add crontab file in the cron directory
ADD crontab /etc/cron.d/tleech-cron
# Give execution rights on the cron job
RUN chmod 0644 /etc/cron.d/tleech-cron
# Create the log file to be able to run tail
RUN touch /var/log/cron.log
# Run the command on container startup
RUN echo "TLEECH_USER=${tleech_user}" >> /etc/environment
RUN echo "TLEECH_PASSWORD=${tleech_password}" >> /etc/environment
CMD java -jar /tleech-hnr-clear.jar && cron && tail -f /var/log/cron.log

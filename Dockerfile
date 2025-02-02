FROM openjdk:8
EXPOSE 8081
ADD target/backstage-jenkins.jar backstage-jenkins
ENTRYPOINT ["java", "-jar", "/backstage-jenkins.ja"]
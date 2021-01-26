FROM openjdk:8
EXPOSE 8080
ADD app/build/libs/recipe-app.war recipe-app.war
ENTRYPOINT ["java", "-jar","recipe-app.war"]
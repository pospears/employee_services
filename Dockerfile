FROM java:8
ADD target/exercise-0.0.1.war spears-exercise-1.war
ENTRYPOINT ["java","-jar","spears-exercise-1.war"]
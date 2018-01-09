SET BASE_URL=http://localhost:8090

::java -jar ../dropwizard/target/dropwizard-1.0-SNAPSHOT.jar server ../dropwizard/config.yml | .\mvnw gatling:execute -Dgatling.resultsFolder=target/gatling/dropwizard_results

::java -jar ../springboot/target/springboot-1.0-SNAPSHOT.jar | .\mvnw gatling:execute

call start_java_program.bat ../springboot/target/springboot-1.0-SNAPSHOT.jar > nul
.\mvnw gatling:execute
exit
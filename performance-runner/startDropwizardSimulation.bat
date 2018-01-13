SET BASE_URL=http://localhost:8090

start java -jar ../dropwizard/target/dropwizard-1.0-SNAPSHOT.jar server ../dropwizard/config.yml
timeout /t 5
start .\mvnw gatling:execute -DresultsFolder=dropwizard_results
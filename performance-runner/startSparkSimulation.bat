SET BASE_URL=http://localhost:8090

start java -jar ../springboot/target/springboot-1.0-SNAPSHOT.jar
timeout /t 5
start .\mvnw gatling:execute -DresultsFolder=springboot_results
SET BASE_URL=http://localhost:8090

start java -jar ../restlet/target/restlet-1.0-SNAPSHOT-jar-with-dependencies.jar
timeout /t 5
start .\mvnw gatling:execute -DresultsFolder=restlet_results
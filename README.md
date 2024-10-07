# CT5106 2024 Group 15 Project

# How to run
1. Clone the repository
2. Make sure your IDE is using the correct Java SDK as per the `build.gradle` file
3. Run gradle build
4. Access the web service at `localhost:8080`

# How to access the in-memory DB (H2)
As per the `application.properties` file, you can access the H2 console at
`localhost:8080/h2-console`

1. Set the "JDBC URL" to: `jdbc:h2:mem:testdb`
2. Leave the user name and password as default for now
3. Click connect


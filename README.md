# Weather Metrics Rest API
REST API developed in Java with Spring Boot in order to provide information on weather information.

## Summary

This API was all developed in Java 8 and Spring Boot in order to demonstrate an application example where, from the name of a city look for weather's information regarding that city based on the following metrics:
- Average of daily (06:00 - 18:00) and nightly (18:00 - 06:00) temperatures in Celsius for the next 3 days from today's date.
- Average of pressure for the next 3 days from today's date.

## API documentation and Test of user
Url :http://localhost:8080/weather-metrics/weather/data?city=london

Response 
```
{
    "city": "london",
    "duration": "3 days",
    "averageTemperatureDaily": 8.83,
    "averageTemperatureNightly": 8.78,
    "averagePressure": 1009.96
}
```
# Run and Test
mvn spring-boot:run

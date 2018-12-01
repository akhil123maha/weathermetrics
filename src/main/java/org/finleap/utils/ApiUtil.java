package org.finleap.utils;

import org.finleap.exception.ApiException;
import org.finleap.exception.ValidationException;
import org.finleap.model.WeatherEntry;
import org.finleap.model.WeatherForecast;
import org.finleap.model.WeatherSummary;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by AKHIL-JARVIS on 01-12-2018.
 */
public class ApiUtil {

    private static final String URI = "http://api.openweathermap.org/data/2.5/forecast";
    private static final String API_ID = "c08651e39ce939969a051093d0ccaeef";

    public static String url(String city) {
        return String.format(URI.concat("?q=%s").concat("&appid=%s").concat("&units=metric"), city, API_ID);
    }

    public static void validateCity(String city) {
        if (StringUtils.isEmpty(city)) throw new ValidationException(ApiException.VALIDATION_EMPTY_REQUEST_BODY);
        if (!city.matches("([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)")) throw new ValidationException(ApiException.VALIDATION_INVALID_CITY);
    }

    public static WeatherSummary getWeatherAverage(WeatherForecast weatherForecast) {

        //average for 3 days
        double pressureAvg = getPressureAverage(weatherForecast);
        double dailyTempAvg = getDailyTemperatureAverage(weatherForecast);
        double nightlyTempAvg = getNightlyTemperatureAverage(weatherForecast);

        return new WeatherSummary(dailyTempAvg, nightlyTempAvg, pressureAvg);
    }

    private static double getNightlyTemperatureAverage(WeatherForecast weatherForecast) {
        double nightlyTemperatureAverage = weatherForecast.getEntries().stream()
                .filter(weatherEntry -> weatherEntry.getTimestamp().isBefore(Instant.now().plus(3, ChronoUnit.DAYS)))
                .filter(weatherEntry -> {
                    LocalTime start = LocalTime.of(18, 0);  // 6 PM.
                    LocalTime stop = LocalTime.of(6, 0);  // 6 AM.
                    LocalTime localTime = weatherEntry.getTimestamp().atZone(ZoneId.systemDefault()).toLocalTime();
                    if (localTime.isAfter(start) || localTime.isBefore(stop)) {
                        return true;
                    }
                    return false;
                })
                .mapToDouble(WeatherEntry::getTemperature).average().getAsDouble();

        return roundedValue(nightlyTemperatureAverage);
    }

    private static double getDailyTemperatureAverage(WeatherForecast weatherForecast) {
        double dailyTemperatureAverage = weatherForecast.getEntries().stream()
                .filter(weatherEntry -> weatherEntry.getTimestamp().isBefore(Instant.now().plus(3, ChronoUnit.DAYS)))
                .filter(weatherEntry -> {
                    LocalTime start = LocalTime.of(6, 0);  // 6 AM.
                    LocalTime stop = LocalTime.of(18, 0);  // 6 AM.
                    LocalTime localTime = weatherEntry.getTimestamp().atZone(ZoneId.systemDefault()).toLocalTime();
                    if (localTime.isAfter(start) && localTime.isBefore(stop)) {
                        return true;
                    }
                    return false;
                })
                .mapToDouble(WeatherEntry::getTemperature).average().getAsDouble();

        return roundedValue(dailyTemperatureAverage);
    }

    private static double getPressureAverage(WeatherForecast weatherForecast) {
        double pressureAverage = weatherForecast.getEntries().stream()
                .filter(weatherEntry -> weatherEntry.getTimestamp().isBefore(Instant.now().plus(3, ChronoUnit.DAYS)))
                .mapToDouble(WeatherEntry::getPressure).average().getAsDouble();

        return roundedValue(pressureAverage);
    }

    private static double roundedValue(double pressureAverage) {
        BigDecimal bd = new BigDecimal(pressureAverage);
        bd = bd.setScale(2, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }
}

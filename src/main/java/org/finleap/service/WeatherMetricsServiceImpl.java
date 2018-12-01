package org.finleap.service;

import org.finleap.model.WeatherForecast;
import org.finleap.model.WeatherSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.finleap.utils.ApiUtil.getWeatherAverage;
import static org.finleap.utils.ApiUtil.url;
import static org.finleap.utils.ApiUtil.validateCity;

@Service
public class WeatherMetricsServiceImpl implements WeatherMetricsService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherMetricsServiceImpl.class);

    private final RestTemplate restTemplate;

    public WeatherMetricsServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public WeatherSummary getWeatherMetrics(String city) {
        try{
            validateCity(city);
            WeatherForecast weatherForecast = this.restTemplate.getForObject(url(city), WeatherForecast.class);
            WeatherSummary weatherAverage = getWeatherAverage(weatherForecast);
            weatherAverage.setCity(city);
            weatherAverage.setDuration("3 days");
            return weatherAverage;
        } catch (HttpClientErrorException exception){
            logger.error("Exception is thrown while fetching the data from OpenWeatherAPI : " + url(city));
            throw exception;
        }
    }
}

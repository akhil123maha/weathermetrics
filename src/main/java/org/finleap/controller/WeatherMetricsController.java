package org.finleap.controller;

import org.finleap.model.WeatherSummary;
import org.finleap.service.WeatherMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("weather")
public class WeatherMetricsController {

    @Autowired
    private WeatherMetricsService weatherMetricsService;

    @GetMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMetrics(@RequestParam(required = true) String city){
        WeatherSummary weatherSummary = weatherMetricsService.getWeatherMetrics(city);
        return new ResponseEntity<>(weatherSummary, HttpStatus.OK);
    }
}

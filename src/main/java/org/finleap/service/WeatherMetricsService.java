package org.finleap.service;

import org.finleap.model.WeatherSummary;

import java.util.List;

public interface WeatherMetricsService {

    WeatherSummary getWeatherMetrics(String city);
}

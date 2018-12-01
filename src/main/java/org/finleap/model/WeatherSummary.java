package org.finleap.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class WeatherSummary implements Serializable {

	private static final long serialVersionUID = 5763148931413501367L;

	private String city;

	private String duration;

	private double averageTemperatureDaily;

	private double averageTemperatureNightly;

    private double averagePressure;

    public WeatherSummary() {
    }

    public WeatherSummary(double averageTemperatureDaily, double averageTemperatureNightly, double averagePressure) {
        this.averageTemperatureDaily = averageTemperatureDaily;
        this.averageTemperatureNightly = averageTemperatureNightly;
        this.averagePressure = averagePressure;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getAveragePressure() {
        return averagePressure;
    }

    public double getAverageTemperatureDaily() {
        return averageTemperatureDaily;
    }

    public double getAverageTemperatureNightly() {
        return averageTemperatureNightly;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}

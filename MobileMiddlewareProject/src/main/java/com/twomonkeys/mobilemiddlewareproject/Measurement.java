package com.twomonkeys.mobilemiddlewareproject;

import java.util.Calendar;

/**
 * Created by Simo on 3/20/14.
 */
public class Measurement {

    private double temperature;
    private double humidity;

    private double latitude;
    private double longitude;

    private Calendar measurementTime;

    public Measurement(double temperature, double humidity, double latitude, double longitude, Calendar measurementTime) {
        this.setTemperature(temperature);
        this.setHumidity(humidity);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setMeasurementTime(measurementTime);
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Calendar getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(Calendar measurementTime) {
        this.measurementTime = measurementTime;
    }
}

package com.romanenko.lew.progforcetask.entity;


public class WeatherObject {

    private String dayOfWeek;

    private String weatherIcon;

    private String weatherResult;

    private String weatherResultSmall;

    public WeatherObject(String dayOfWeek, String weatherIcon, String weatherResult, String weatherResultSmall) {
        this.dayOfWeek = dayOfWeek;
        this.weatherIcon = weatherIcon;
        this.weatherResult = weatherResult;
        this.weatherResultSmall = weatherResultSmall;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public String getWeatherResult() {
        return weatherResult;
    }

    public String getWeatherResultSmall() {
        return weatherResultSmall;
    }
}

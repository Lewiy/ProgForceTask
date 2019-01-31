package com.romanenko.lew.progforcetask.base;

import com.romanenko.lew.progforcetask.entity.WeatherObject;

import java.util.List;

public interface WeatherScreenContract {

    interface ViewWeather extends IView{
           void  setCity(String city);
           void setWindValue(double windValue);
           void setHumidityValue(int humidityValue);
           void setWeatherDescription(String weather);
           void setClouds(int number);
           void setWeatherList(List<WeatherObject> weatherWeatherList);
    }

    interface PresenterWeather extends IPresenter<ViewWeather>{

        void loadWeatherForecast(double lat, double lng);
    }

}

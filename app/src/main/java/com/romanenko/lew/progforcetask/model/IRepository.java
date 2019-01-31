package com.romanenko.lew.progforcetask.model;

import com.romanenko.lew.progforcetask.entity.WeatherObject;
import com.romanenko.lew.progforcetask.model.POJO.Weather;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface IRepository {

    Single<Weather> loadWeatherForecast5Days(double lat, double lon);
}

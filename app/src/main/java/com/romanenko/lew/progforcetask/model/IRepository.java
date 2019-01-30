package com.romanenko.lew.progforcetask.model;

import com.romanenko.lew.progforcetask.model.POJO.Weather;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface IRepository {

    Single<Weather> loadWeatherForecast5Days(float lat, float lon);
}

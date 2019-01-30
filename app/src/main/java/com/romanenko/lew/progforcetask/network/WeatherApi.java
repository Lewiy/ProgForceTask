package com.romanenko.lew.progforcetask.network;

import com.romanenko.lew.progforcetask.model.POJO.Weather;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("/forecast")
    Single<Weather> getWeather(@Query("lat") float lat, @Query("lon") float lon, @Query("appid") String appid);

   // api.openweathermap.org/data/2.5/forecast?lat=35&lon=139
  //  http://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139&appid=53f680b691582dccb8054897d21dedce
}

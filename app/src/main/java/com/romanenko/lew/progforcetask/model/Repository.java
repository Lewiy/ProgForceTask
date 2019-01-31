package com.romanenko.lew.progforcetask.model;

import android.content.Context;

import com.romanenko.lew.progforcetask.R;
import com.romanenko.lew.progforcetask.entity.WeatherObject;
import com.romanenko.lew.progforcetask.helpers.Pair;
import com.romanenko.lew.progforcetask.model.POJO.Weather;
import com.romanenko.lew.progforcetask.network.WeatherApi;

import java.util.Calendar;
import java.util.Locale;


import io.reactivex.Observable;
import io.reactivex.Single;

public class Repository implements IRepository {

    private Context mContext;
    private WeatherApi mWeatherApi;

    public Repository(Context context, WeatherApi weatherApi) {
        this.mContext = context;
        this.mWeatherApi = weatherApi;
    }

    @Override
    public Single<Weather> loadWeatherForecast5Days(double lat, double lon) {
        return mWeatherApi
                .getWeather(lat, lon, mContext.getResources().getString(R.string.api_key_weather_forecast))
                .cache();
    }





}

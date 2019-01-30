package com.romanenko.lew.progforcetask.model;

import android.content.Context;

import com.romanenko.lew.progforcetask.network.WeatherApi;

public class Repository {

    private Context mContext;
    private WeatherApi mWeatherApi;

    public Repository(Context context, WeatherApi weatherApi) {
        this.mContext = context;
        this.mWeatherApi = weatherApi;
    }
}

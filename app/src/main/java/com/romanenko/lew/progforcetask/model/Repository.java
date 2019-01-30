package com.romanenko.lew.progforcetask.model;

import android.content.Context;

import com.romanenko.lew.progforcetask.R;
import com.romanenko.lew.progforcetask.model.POJO.Weather;
import com.romanenko.lew.progforcetask.network.WeatherApi;

import javax.inject.Singleton;

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
    public Single<Weather> loadWeatherForecast5Days(float lat, float lon) {
       /* return randomUsersListFull
                //.flatMapIterable(list -> Observable.fromIterable(list))
                .flatMap(list -> Observable.fromIterable(list))
                .filter(user -> user.getEmail().equals(email));*/

      return mWeatherApi
              .getWeather(lat,lon,mContext.getResources().getString(R.string.api_key_weather_forecast));
    }

}

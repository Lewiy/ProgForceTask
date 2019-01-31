package com.romanenko.lew.progforcetask.weatherScreen;

import android.content.Context;

import com.romanenko.lew.progforcetask.App;
import com.romanenko.lew.progforcetask.base.BasePresenter;
import com.romanenko.lew.progforcetask.base.WeatherScreenContract;
import com.romanenko.lew.progforcetask.entity.WeatherObject;
import com.romanenko.lew.progforcetask.helpers.Pair;
import com.romanenko.lew.progforcetask.model.IRepository;
import com.romanenko.lew.progforcetask.model.POJO.List;
import com.romanenko.lew.progforcetask.model.POJO.Weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherPresenter extends BasePresenter<WeatherScreenContract.ViewWeather> implements WeatherScreenContract.PresenterWeather {


    private IRepository repository;
    private Single<Weather> weatherSingle;

    WeatherPresenter(Context context) {
        repository = App.get(context).getAppComponent().getUserRepository();
    }

    @Override
    public void viewIsReady() {

    }

    @Override
    public void loadWeatherForecast(double lat, double lng) {

        Observable<Integer> values = Observable.range(1, 40);
        weatherSingle = repository.loadWeatherForecast5Days(lat, lng);

        weatherSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather -> {
                            getView().setCity(weather.getCity().getName());
                            getView().setClouds(weather.getList().get(0).getClouds().getAll());
                            getView().setHumidityValue(weather.getList().get(0).getMain().getHumidity());
                            getView().setWindValue(weather.getList().get(0).getWind().getSpeed());
                            getView().setWeatherDescription(weather.getList().get(0).getWeather().get(0).getDescription());
                        }
                        , error -> getView().showError(error.getMessage().toString()));

        weatherSingle
                .flattenAsObservable(weather -> weather.getList())
                .zipWith(values, (i, n) -> new Pair(n, i))
                .filter(i -> i.getLeft() % 8 == 0)
                .map(weather ->
                        new WeatherObject(getDayOfWeek(weather.getRight().getDtTxt())
                                , weather.getRight().getWeather().get(0).getIcon()
                                , weather.getRight().getWeather().get(0).getDescription()
                                , String.valueOf(weather.getRight().getMain().getTemp()))
                )
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather ->
                                getView().setWeatherList(weather)
                        , error -> getView().showError(error.getMessage().toString()));
    }

    private String getDayOfWeek(String str) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        try {
            cal.setTime(sdf.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String string = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.ALL_STYLES, Locale.getDefault());
        return string;
    }

}

package com.romanenko.lew.progforcetask.weatherScreen;

import android.content.Context;

import com.romanenko.lew.progforcetask.App;
import com.romanenko.lew.progforcetask.base.BasePresenter;
import com.romanenko.lew.progforcetask.base.WeatherScreenContract;
import com.romanenko.lew.progforcetask.entity.WeatherObject;
import com.romanenko.lew.progforcetask.helpers.Pair;
import com.romanenko.lew.progforcetask.model.IRepository;
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


    private IRepository mRepository;
    private Single<Weather> mWeatherSingle;
    private static final int RANGE_START = 1,RANGE_END= 40, FIRST_ELEMENT = 0,NEEDE_POSITION_ELEMENT = 8;
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    WeatherPresenter(Context context) {
        mRepository = App.get(context).getAppComponent().getUserRepository();
    }

    @Override
    public void viewIsReady() {

    }

    @Override
    public void loadWeatherForecast(double lat, double lng) {

        Observable<Integer> values = Observable.range(RANGE_START, RANGE_END);
        mWeatherSingle = mRepository.loadWeatherForecast5Days(lat, lng);

        mWeatherSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather -> {
                            getView().setCity(weather.getCity().getName());
                            getView().setClouds(weather.getList().get(FIRST_ELEMENT).getClouds().getAll());
                            getView().setHumidityValue(weather.getList().get(FIRST_ELEMENT).getMain().getHumidity());
                            getView().setWindValue(weather.getList().get(FIRST_ELEMENT).getWind().getSpeed());
                            getView().setWeatherDescription(weather.getList().get(FIRST_ELEMENT).getWeather().get(FIRST_ELEMENT).getDescription());
                        }
                        , error -> getView().showError(error.getMessage().toString()));

        mWeatherSingle
                .flattenAsObservable(weather -> weather.getList())
                .zipWith(values, (i, n) -> new Pair(n, i))
                .filter(i -> i.getLeft() % NEEDE_POSITION_ELEMENT == 0)
                .map(weather ->
                        new WeatherObject(getDayOfWeek(weather.getRight().getDtTxt())
                                , weather.getRight().getWeather().get(FIRST_ELEMENT).getIcon()
                                , weather.getRight().getWeather().get(FIRST_ELEMENT).getDescription()
                                , String.valueOf(weather.getRight().getMain().getTemp()))
                )
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather -> {
                            getView().setWeatherList(weather);
                            getView().disableProgresBar();
                        }

                        , error -> getView().showError(error.getMessage().toString()));
    }

    private String getDayOfWeek(String str) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN, Locale.ENGLISH);
        try {
            cal.setTime(sdf.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String string = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.ALL_STYLES, Locale.getDefault());
        return string;
    }

}

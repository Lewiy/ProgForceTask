package com.romanenko.lew.progforcetask.weatherScreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.pavlospt.CircleView;
import com.romanenko.lew.progforcetask.R;
import com.romanenko.lew.progforcetask.adapters.RecyclerViewAdapter;
import com.romanenko.lew.progforcetask.base.BaseFragment;
import com.romanenko.lew.progforcetask.base.WeatherScreenContract;
import com.romanenko.lew.progforcetask.entity.WeatherObject;
import com.romanenko.lew.progforcetask.weatherScreen.di.DaggerWeatherComponent;
import com.romanenko.lew.progforcetask.weatherScreen.di.WeatherModule;


import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherFragment extends BaseFragment implements WeatherScreenContract.ViewWeather {

    private static final String ARG_WEATHER_PSTN = "pstn_marker";
    private double mLat, mLng;
    @BindView(R.id.city_country)
    TextView mCityCountry;
    @BindView(R.id.wind_result)
    TextView mWindResalt;
    @BindView(R.id.humidity_result)
    TextView mHumidity;
    @BindView(R.id.weather_result)
    CircleView mWeatherResalt;
    @BindView(R.id.current_date)
    TextView mCurrentDay;
    @BindView(R.id.weather_daily_list)
    RecyclerView mWeatherList;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.weather_page)
    LinearLayout mWeatherPage;
    @BindView(R.id.weather_forecast)
    LinearLayout mWeatherForecast;


    @Inject
    public WeatherScreenContract.PresenterWeather mPresenterWeather;
    @Inject
    public RecyclerViewAdapter mWeatherListAdapter;
    @Inject
    public LinearLayoutManager mLayoutManager;

    public static WeatherFragment WeatherFragment(double lat, double lng) {
        Bundle args = new Bundle();
        args.putDoubleArray(ARG_WEATHER_PSTN, new double[]{lat, lng});
        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        double[] position = getArguments().getDoubleArray(ARG_WEATHER_PSTN);
        mLat = position[0];
        mLng = position[1];



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_fragment, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        DaggerWeatherComponent
                .builder()
                .weatherModule(new WeatherModule(this, new WeatherPresenter(getContext()),getContext()))
                .build()
                .inject(this);
        mPresenterWeather.attachView(this);
        mPresenterWeather.viewIsReady();

        if(!isConnectedToNetwork(getContext()))
            showError(getResources().getString(R.string.no_internet_connection));

        mPresenterWeather.loadWeatherForecast(mLat, mLng);

        mWeatherList.setLayoutManager(mLayoutManager);
        mWeatherList.setAdapter(mWeatherListAdapter);
        mWeatherList.setItemAnimator(new DefaultItemAnimator());
        mWeatherList.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        setTime();
    }

    private void setTime() {
        String days;
        Calendar calendar = Calendar.getInstance();
        days = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())
                + "," + calendar.get(Calendar.DAY_OF_MONTH)
                + " " + calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
        mCurrentDay.setText(days);
    }


    @Override
    public String toString() {
        return WeatherFragment.class.toString();
    }

    @Override
    public void setCity(String city) {
        mCityCountry.setText(city);
    }

    @Override
    public void setWindValue(double windValue) {
        mWindResalt.setText(String.valueOf(windValue));
    }

    @Override
    public void setHumidityValue(int humidityValue) {
        mHumidity.setText(String.valueOf(humidityValue));
    }

    @Override
    public void setWeatherDescription(String weather) {
        mWeatherResalt.setSubtitleText(weather);
    }

    @Override
    public void setClouds(int number) {
        mWeatherResalt.setTitleText(String.valueOf(number));
    }

    @Override
    public void setWeatherList(List<WeatherObject> weatherWeatherList) {
        mWeatherListAdapter.setList(weatherWeatherList);
    }

    @Override
    public void disableProgresBar() {
        mProgressBar.setVisibility(View.GONE);
        mWeatherPage.setVisibility(View.VISIBLE);
        mWeatherForecast.setVisibility(View.VISIBLE);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mPresenterWeather.detachView();
    }
}

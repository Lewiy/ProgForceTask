package com.romanenko.lew.progforcetask.weatherScreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.romanenko.lew.progforcetask.R;
import com.romanenko.lew.progforcetask.base.BaseFragment;
import com.romanenko.lew.progforcetask.base.WeatherScreenContract;
import com.romanenko.lew.progforcetask.mapScreen.MapFragment;

public class WeatherFragment extends BaseFragment implements WeatherScreenContract.ViewWeather{

    public static WeatherFragment WeatherFragment() {
        return new WeatherFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.weather_fragment, container, false);


        return rootView;
    }

    @Override
    public void showError(String error) {
        //Todo: show error
    }

    @Override
    public String toString() {
        return WeatherFragment.class.toString();
    }
}

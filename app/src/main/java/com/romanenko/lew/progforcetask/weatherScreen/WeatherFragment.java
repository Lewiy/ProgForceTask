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
import com.romanenko.lew.progforcetask.model.POJO.Weather;

public class WeatherFragment extends BaseFragment implements WeatherScreenContract.ViewWeather {

    private static final String ARG_WEATHER_PSTN = "pstn_marker";
    private double mLat, mLng;

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

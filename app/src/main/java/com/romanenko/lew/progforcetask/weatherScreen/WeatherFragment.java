package com.romanenko.lew.progforcetask.weatherScreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.romanenko.lew.progforcetask.R;
import com.romanenko.lew.progforcetask.mapScreen.MapFragment;

public class WeatherFragment extends Fragment {

    public static WeatherFragment MapFragment() {
        return new WeatherFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_fragment, container, false);


        return rootView;
    }
}

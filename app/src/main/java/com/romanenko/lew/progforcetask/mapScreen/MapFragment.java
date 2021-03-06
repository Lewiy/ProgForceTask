package com.romanenko.lew.progforcetask.mapScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.romanenko.lew.progforcetask.R;
import com.romanenko.lew.progforcetask.base.BaseFragment;
import com.romanenko.lew.progforcetask.base.MapScreenContract;
import com.romanenko.lew.progforcetask.weatherScreen.WeatherFragment;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.constraint.Constraints.TAG;

public class MapFragment extends BaseFragment implements MapScreenContract.ViewWeather, OnMapReadyCallback {

    SupportMapFragment mMapFragment;
    GoogleMap mGoogleMap;
    double mLat, mLng;

    @BindView(R.id.weather_forecast)
    Button mWeatherForecast;

    public static MapFragment MapFragment() {
        return new MapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!isConnectedToNetwork(getContext()))
            showError(getResources().getString(R.string.no_internet_connection));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);
        mMapFragment.getMapAsync(this);
    }

    @OnClick(R.id.weather_forecast)
    public void onClickWeatherForecast() {
        Fragment fr = WeatherFragment.WeatherFragment(mLat,mLng);
        BaseFragment.FragmentChangeListener fc = (BaseFragment.FragmentChangeListener) getActivity();
        fc.replaceFragment(fr);
    }

    @Override
    public String toString() {
        return MapFragment.class.toString();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(37.4219999, -122.0862462))
                .zoom(10)
                .bearing(0)
                .tilt(45)
                .build();

        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);
        mGoogleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                mLat = mGoogleMap.getCameraPosition().target.latitude;
                mLng = mGoogleMap.getCameraPosition().target.longitude;
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

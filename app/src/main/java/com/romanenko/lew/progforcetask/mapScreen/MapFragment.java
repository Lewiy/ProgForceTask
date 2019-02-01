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


      /*  AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getFragmentManager().findFragmentById(R.id.autocomplete_fragment);

// Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

// Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });*/
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

        //  mMap.clear(); //clear old markers

        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(37.4219999, -122.0862462))
                .zoom(10)
                .bearing(0)
                .tilt(45)
                .build();

        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);

        mGoogleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.4219999, -122.0862462))
                .title("Spider Man"));
        // .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.spider)));
        mGoogleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                // Cleaning all the markers.

              /*  mPosition = mGoogleMap.getCameraPosition().target;
                mZoom = mGoogleMap.getCameraPosition().zoom;*/
                mLat = mGoogleMap.getCameraPosition().target.latitude;
                mLng = mGoogleMap.getCameraPosition().target.longitude;
            }
        });
    }
}

package com.romanenko.lew.progforcetask.mapScreen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.romanenko.lew.progforcetask.R;

public class MapFragment extends Fragment {

    SupportMapFragment mMapFragment;

    public static MapFragment MapFragment() {
        // Required empty public constructor
        return new MapFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_fragment, container, false);
        mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);
        onMapReady();
        return rootView;
    }

     private void onMapReady(){
         mMapFragment.getMapAsync(new OnMapReadyCallback() {
             @Override
             public void onMapReady(GoogleMap mMap) {
                 mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

               //  mMap.clear(); //clear old markers

                 CameraPosition googlePlex = CameraPosition.builder()
                         .target(new LatLng(37.4219999,-122.0862462))
                         .zoom(10)
                         .bearing(0)
                         .tilt(45)
                         .build();

                 mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);

                 mMap.addMarker(new MarkerOptions()
                         .position(new LatLng(37.4219999, -122.0862462))
                         .title("Spider Man"));
                 // .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.spider)));

             }
         });
     }
}

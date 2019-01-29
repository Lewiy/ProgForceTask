package com.romanenko.lew.progforcetask;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.romanenko.lew.progforcetask.base.BaseActivity;
import com.romanenko.lew.progforcetask.mapScreen.MapFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected Fragment createFragment() {
        return MapFragment.MapFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



}

package com.romanenko.lew.progforcetask;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.romanenko.lew.progforcetask.base.FragmentActivity;
import com.romanenko.lew.progforcetask.mapScreen.MapFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected Fragment createFragment() {
        return MapFragment.MapFragment();
    }

    @Override
    protected int getFragmentContainer() {
        return R.id.fragment_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}

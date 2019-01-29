package com.romanenko.lew.progforcetask.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.romanenko.lew.progforcetask.R;

public abstract class BaseActivity extends AppCompatActivity {

    FragmentManager mFragmentManager;

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setFragmentOnScreen();
    }

    private void init() {
        mFragmentManager = getSupportFragmentManager();
    }

    private void setFragmentOnScreen() {
        Fragment fragment = createFragment();
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}

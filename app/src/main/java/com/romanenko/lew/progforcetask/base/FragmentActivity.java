package com.romanenko.lew.progforcetask.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.romanenko.lew.progforcetask.R;

public abstract class FragmentActivity extends AppCompatActivity implements BaseFragment.FragmentChangeListener {

    FragmentManager mFragmentManager;

    protected abstract Fragment createFragment();
    protected abstract int getFragmentContainer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setFragmentOnScreen();
    }

    private void init() {
        mFragmentManager = getSupportFragmentManager();
    }

    private void setFragmentOnScreen() {
        Fragment fragment = createFragment();
        mFragmentManager.beginTransaction()
                .replace(getFragmentContainer(), fragment)
                .commit();
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction =  mFragmentManager.beginTransaction();
        fragmentTransaction.replace(getFragmentContainer(), fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }

}

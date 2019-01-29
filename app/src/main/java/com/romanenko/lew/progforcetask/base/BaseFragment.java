package com.romanenko.lew.progforcetask.base;


import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
     public interface FragmentChangeListener {
         void replaceFragment(Fragment fragment);
    }
}

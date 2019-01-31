package com.romanenko.lew.progforcetask.base;


import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.romanenko.lew.progforcetask.R;

public class BaseFragment extends Fragment {
     public interface FragmentChangeListener {
         void replaceFragment(Fragment fragment);
    }

    public void showError(String error) {
        if(error != null)
            Toast.makeText(getContext(),
                    error, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getContext(),
                    R.string.common_error, Toast.LENGTH_SHORT).show();
    }
}

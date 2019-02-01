package com.romanenko.lew.progforcetask.base;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

    public boolean isConnectedToNetwork(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean isConnected = false;
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            isConnected = (activeNetwork != null) && (activeNetwork.isConnectedOrConnecting());
        }

        return isConnected;
    }
}

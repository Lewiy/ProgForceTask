package com.romanenko.lew.progforcetask;

import android.app.Application;
import android.content.Context;

import com.romanenko.lew.progforcetask.base.di.components.AppComponent;
import com.romanenko.lew.progforcetask.base.di.components.DaggerAppComponent;
import com.romanenko.lew.progforcetask.base.di.modules.ContextModule;

public class App extends Application {

    private AppComponent appComponent;

    public static App get(Context context){
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

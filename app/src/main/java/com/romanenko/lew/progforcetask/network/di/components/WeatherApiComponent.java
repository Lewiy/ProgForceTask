package com.romanenko.lew.progforcetask.network.di.components;

import android.content.Context;

import com.romanenko.lew.progforcetask.network.WeatherApi;
import com.romanenko.lew.progforcetask.network.di.models.WeatherAPIModule;
import com.romanenko.lew.progforcetask.network.di.scopes.ApplicationScope;


import dagger.Component;

@ApplicationScope
@Component(modules = {WeatherAPIModule.class})
public interface WeatherApiComponent {
    WeatherApi weatherAPI();
    Context context();
}

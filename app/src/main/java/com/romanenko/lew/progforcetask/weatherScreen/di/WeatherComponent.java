package com.romanenko.lew.progforcetask.weatherScreen.di;

import com.romanenko.lew.progforcetask.base.di.scopes.ActivityScope;
import com.romanenko.lew.progforcetask.weatherScreen.WeatherFragment;

import dagger.Component;

@ActivityScope
@Component(modules = WeatherModule.class)
public interface WeatherComponent {
    void inject(WeatherFragment weatherFragment);
}

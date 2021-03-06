package com.romanenko.lew.progforcetask.base.di.modules;

import android.content.Context;

import com.romanenko.lew.progforcetask.model.IRepository;
import com.romanenko.lew.progforcetask.model.Repository;
import com.romanenko.lew.progforcetask.network.WeatherApi;
import com.romanenko.lew.progforcetask.network.di.models.WeatherAPIModule;
import com.romanenko.lew.progforcetask.network.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module(includes = WeatherAPIModule.class)
public class RepositoryModule {

    @Provides
    @ApplicationScope
    IRepository provideRepo(Context context, WeatherApi weatherApi){
        return new Repository(context,weatherApi);
    }
}

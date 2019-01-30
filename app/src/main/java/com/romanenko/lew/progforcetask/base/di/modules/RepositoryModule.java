package com.romanenko.lew.progforcetask.base.di.modules;

import android.content.Context;

import com.romanenko.lew.progforcetask.model.Repository;
import com.romanenko.lew.progforcetask.network.WeatherApi;
import com.romanenko.lew.progforcetask.network.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module()
public class RepositoryModule {

    @Provides
    @ApplicationScope
    Repository provideRepo(Context context, WeatherApi weatherApi){
        return new Repository(context,weatherApi);
    }
}

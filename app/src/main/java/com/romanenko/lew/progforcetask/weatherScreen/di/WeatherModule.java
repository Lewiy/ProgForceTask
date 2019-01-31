package com.romanenko.lew.progforcetask.weatherScreen.di;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.romanenko.lew.progforcetask.adapters.RecyclerViewAdapter;
import com.romanenko.lew.progforcetask.base.WeatherScreenContract;
import com.romanenko.lew.progforcetask.base.di.modules.ContextModule;
import com.romanenko.lew.progforcetask.base.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module()
public class WeatherModule {
    WeatherScreenContract.ViewWeather view;
    WeatherScreenContract.PresenterWeather presenter;
    Context context;

    public WeatherModule(WeatherScreenContract.ViewWeather view, WeatherScreenContract.PresenterWeather presenter, Context context) {
        this.view = view;
        this.presenter = presenter;
        this.context = context;
    }

    @ActivityScope
    @Provides
    public WeatherScreenContract.ViewWeather provideView() {
        return view;
    }

    @ActivityScope
    @Provides
    public WeatherScreenContract.PresenterWeather providePresenter() {
        return presenter;
    }

    @ActivityScope
    @Provides
    RecyclerViewAdapter provideListAdapter(Context context) {
        return new RecyclerViewAdapter(context);
    }

    @ActivityScope
    @Provides
    LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
    }

    @ActivityScope
    @Provides
    Context provideContext(){
        return context;
    }

}

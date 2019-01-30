package com.romanenko.lew.progforcetask.base.di.modules;

import android.content.Context;


import com.romanenko.lew.progforcetask.network.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @ApplicationScope
    @Provides
    public Context context(){ return context.getApplicationContext(); }
}

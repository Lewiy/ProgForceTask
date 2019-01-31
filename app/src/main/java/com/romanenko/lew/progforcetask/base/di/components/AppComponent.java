package com.romanenko.lew.progforcetask.base.di.components;

import com.romanenko.lew.progforcetask.base.di.modules.RepositoryModule;
import com.romanenko.lew.progforcetask.model.IRepository;
import com.romanenko.lew.progforcetask.model.Repository;
import com.romanenko.lew.progforcetask.network.di.components.WeatherApiComponent;
import com.romanenko.lew.progforcetask.network.di.scopes.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = RepositoryModule.class)
public interface AppComponent {
   IRepository getUserRepository();
}

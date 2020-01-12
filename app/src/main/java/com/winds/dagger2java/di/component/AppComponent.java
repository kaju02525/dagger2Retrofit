package com.winds.dagger2java.di.component;

import android.content.Context;

import com.google.gson.Gson;
import com.winds.dagger2java.App;
import com.winds.dagger2java.di.module.AppModule;
import com.winds.dagger2java.di.module.NetworkModule;
import com.winds.dagger2java.network.ApiService;
import com.winds.dagger2java.ui.MainActivity;
import com.winds.dagger2java.utils.PrefUtils;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(App app);

    Context provideContext();

    void inject(MainActivity mainActivity);

    ApiService provideApiService();

    Gson provideGson();

    PrefUtils providePrefUtils();

}

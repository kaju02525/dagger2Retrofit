package com.winds.dagger2java.di.module;


import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.winds.dagger2java.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private App mApp;

    public AppModule(App mApp) {
        this.mApp = mApp;
    }

    @Provides
    @Singleton
    App providerApp() {
        return mApp;
    }

    @Provides
    @Singleton
    Application provideAppLication() {
        return mApp;
    }


    @Provides
    @Singleton
    Context provideContext() {
        return mApp.getApplicationContext();
    }


    @Singleton
    @Provides
    Gson provideGson() {
        return new Gson();
    }
}

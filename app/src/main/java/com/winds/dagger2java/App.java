package com.winds.dagger2java;

import android.app.Application;
import android.content.SharedPreferences;

import com.securepreferences.SecurePreferences;
import com.winds.dagger2java.di.component.AppComponent;
import com.winds.dagger2java.di.component.DaggerAppComponent;
import com.winds.dagger2java.di.module.AppModule;
import com.winds.dagger2java.di.module.NetworkModule;

import static com.winds.dagger2java.utils.Utils.generateSecureKey;

public class App extends Application {

    private static App sInstance;
    private AppComponent mAppComponent;
    private static SharedPreferences prefs;

    public static App getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        mAppComponent= DaggerAppComponent.builder()
            .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
            .build();
        mAppComponent.inject(this);


        String secureKey = generateSecureKey(this);
        prefs = new SecurePreferences(this, secureKey, "default_shared_prefs.xml");

    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    public static SharedPreferences getSharedPreference() {
        return prefs;
    }
}

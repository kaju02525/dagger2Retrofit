package com.winds.dagger2java.di.module;

import android.app.Application;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.winds.dagger2java.BuildConfig;
import com.winds.dagger2java.network.ApiConfig;
import com.winds.dagger2java.network.ApiService;
import com.winds.dagger2java.network.HeaderInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class NetworkModule {


    @Provides
    @Singleton
    ApiService provideAPI(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }


    @Provides
    @Singleton
    OkHttpClient.Builder provideHttpClientBuilder(Application application) {
        File cacheDir = new File(application.getCacheDir(), "okhttp_cache");
        Cache cache = new Cache(cacheDir, ApiConfig.MAX_HTTP_CACHE_SIZE);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(ApiConfig.Timeouts.CONNECT, TimeUnit.SECONDS)
                .readTimeout(ApiConfig.Timeouts.READ, TimeUnit.SECONDS)
                .writeTimeout(ApiConfig.Timeouts.WRITE, TimeUnit.SECONDS)
                //.addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(new HeaderInterceptor()) //add headers in HeaderInterceptor if needed and uncomment this. It will pass headers along with all outgoing requests
                .cache(cache);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            builder.addInterceptor(loggingInterceptor);

        }
        return builder;
    }


    @Provides
    @Singleton
    OkHttpClient provideHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient, Converter.Factory converterFactory, CallAdapter.Factory adapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(ApiConfig.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(adapterFactory)
                .addConverterFactory(converterFactory);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Retrofit.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    CallAdapter.Factory provideCalLAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }
}

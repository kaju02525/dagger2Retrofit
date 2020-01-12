package com.winds.dagger2java.network;


import androidx.annotation.NonNull;

import com.winds.dagger2java.App;
import com.winds.dagger2java.utils.PrefUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;



public class HeaderInterceptor implements Interceptor {
    PrefUtils prefUtils  = App.getInstance().getAppComponent().providePrefUtils();

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Request.Builder builder = chain.request().newBuilder();

        //put your headers key values here. for ex:
      //  if(!prefUtils.getString(Const.TOKEN).equals(""))
     //   builder.header("Authorization", prefUtils.getString(Const.TOKEN));
        builder.header("Accept", "application/json");
     //   builder.header("Accept-Language", prefUtils.getusername(Const.LANGUAGE));
        builder.header("App-Version", Const.APP_VERSION);
        builder.header("Device-OS", Const.DEVICE_OS);
        builder.header("Device-Build", Const.DEVICE_Build);

        return chain.proceed(builder.build());
    }

}


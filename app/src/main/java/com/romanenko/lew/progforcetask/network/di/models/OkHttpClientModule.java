package com.romanenko.lew.progforcetask.network.di.models;

import android.content.Context;


import com.romanenko.lew.progforcetask.base.di.modules.ContextModule;
import com.romanenko.lew.progforcetask.network.NetworkState;
import com.romanenko.lew.progforcetask.network.di.scopes.ApplicationScope;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Module(includes = ContextModule.class)
public class OkHttpClientModule {

    private static final String CACHE_CONTROL = "Cache-Control";
    @ApplicationScope
    @Provides
    public OkHttpClient okHttpClient(Cache cache,@Named("interceptor") Interceptor Interceptor, @Named("offlineInterceptor")Interceptor offlineInterceptor) {
        return new OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(offlineInterceptor)
                .addNetworkInterceptor(Interceptor)
                .build();
    }

    @Provides
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000); //10 MB
    }

    @Provides
    public File file(Context context) {
        File file = new File(context.getCacheDir(), "HttpCache");
        file.mkdirs();
        return file;
    }

    @Named("interceptor")
    @Provides
    public Interceptor Interceptor(Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed( chain.request() );
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge( 1, TimeUnit.MINUTES )
                        .build();

                return response.newBuilder()
                        .header( CACHE_CONTROL, cacheControl.toString() )
                        .build();
            }
        };
    }
    @Named("offlineInterceptor")
    @Provides
    public Interceptor offlineInterceptor(Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if(!NetworkState.isConnectedToNetwork(context)){
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale( 7, TimeUnit.DAYS )
                            .build();

                    request = request.newBuilder()
                            .cacheControl( cacheControl )
                            .build();
                }
                return chain.proceed(request);
            }
        };
    }
}

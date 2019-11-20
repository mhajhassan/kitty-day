package com.nalovma.kittyday.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.nalovma.kittyday.data.rest.CatService;
import com.nalovma.kittyday.di.module.ViewModelModule;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.nalovma.kittyday.utils.Constants.*;

@Module(includes = ViewModelModule.class)
public class ApplicationModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader(KEY_HEADER, API_KEY)
                                .build();
                        return chain.proceed(request);
                    }
                }).addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Singleton
    @Provides
    static CatService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(CatService.class);
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

}


package com.lifeistech.android.network.api;

import com.lifeistech.android.network.entity.WeatherEntity;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by rild on 16/02/15.
 */
public interface WeatherApi {

    @GET("/data/2.5/{name}")
    public Observable<WeatherEntity> get(@Path("name") String name, @Query("q") String q);

}
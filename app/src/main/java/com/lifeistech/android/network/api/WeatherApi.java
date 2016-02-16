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
    @GET("/forecast/webservice/json/v1")
    public Observable<WeatherEntity> getWeather(@Query("city") final String city);

}
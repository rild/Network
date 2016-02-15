package com.lifeistech.android.network;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.cookpad.android.rxt4a.schedulers.AndroidSchedulers;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.lifeistech.android.network.api.ApiClient;
import com.lifeistech.android.network.api.WeatherApi;
import com.lifeistech.android.network.entity.WeatherEntity;
import com.lifeistech.android.network.response.ApiResponse;
import com.lifeistech.android.network.tmp.Entries;
import com.lifeistech.android.network.tmp.Entry;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    ApiClient mApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getWeather();
//        Log.d("Start:", "Succeeded");
        // JSONのパーサー
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        // RestAdapterの生成
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://api.openweathermap.org")
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("=NETWORK="))
                .build();

        // 非同期処理の実行
        adapter.create(WeatherApi.class).get("weather", "Tokyo,jp")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherEntity>() {
                    @Override
                    public void onCompleted() {
                        Log.d("MainActivity", "onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("MainActivity", "Error : " + e.toString());
                    }

                    @Override
                    public void onNext(WeatherEntity weather) {
                        Log.d("MainActivity", "onNext()");
                        if (weather != null) {
                            ((TextView) findViewById(R.id.text)).setText(weather.weather.get(0).main);
                        }
                    }
                });
    }



    private void getWeather() {
        Request request = new Request.Builder()
                .url("http://weather.livedoor.com/forecast/webservice/json/v1?city=130010")
                .get()
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            //onResponse in Callback interface
            //Failir
            @Override
            public void onResponse(Response response) throws IOException {
                Log.d("onResponse", response.toString());
                parseJson(response.body().toString());
            }
        });
    }

    private void parseJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray forecastsArray = jsonObject.getJSONArray("forecasts");
            JSONObject todayWeatherJson = forecastsArray.getJSONObject(0);

            String date = todayWeatherJson.getString("date");
            Log.d("Date:", date);
            String telop = todayWeatherJson.getString("telop");
            Log.d("Telop:", telop);
            String dataLabel = todayWeatherJson.getString("dataLabel");
            Log.d("DataLabel:", dataLabel);

            JSONObject temperatureJson = todayWeatherJson.getJSONObject("temperature");
            JSONObject minJson = temperatureJson.get("min") != null ? temperatureJson.getJSONObject("min") : null;
            String min = "";
            if (minJson != null) {
                min = minJson.getString("celsius");
            }
            JSONObject maxJson = temperatureJson.get("max") != null ? temperatureJson.getJSONObject("max") : null;
            String max = "";
            if (maxJson != null) {
                max = maxJson.getString("celsius");
            }
            Log.d("Min ~ MAx:", min + "~" + max);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void Call() {
        mApiClient.getEntries(new ApiClient.ResponseListener<Entries>() {
            @Override
            public void onComplete(ApiResponse<Entries> r) {
                final int count = r.content.entryIds.size();
                final List<Entry> entries = new ArrayList<>();

                for (String id : r.content.entryIds) {
                    mApiClient.getEntry(id, new ApiClient.ResponseListener<Entry>() {
                        @Override
                        public void onComplete(ApiResponse<Entry> response) {
                            synchronized (entries) {
                                entries.add(response.content);
                                if (entries.size() == count) {
                                    //entries を viewに反映させる
                                    Timber.d("XXX", TextUtils.join(", ", entries));
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    private void Call2() {
        mApiClient.entries().subscribe(new Action1<ApiResponse<Entries>>() {
            @Override
            public void call(ApiResponse<Entries> r) {
                Timber.d("XXX", "entry Ids:" + TextUtils.join(", ", r.content.entryIds));
            }
        });
    }

//    public Observable<ApiResponse<Entries>> entries() {
//        // Observable.OnSubscribeのインスタンスからObservableを生成する
//        return Observable.create(
//                new Observable.OnSubscribe<ApiResponse<Entries>>() {
//                    @Override
//                    public void call(final Subscriber
//                            <? super ApiResponse<Entries>> subscriber) {
//                        // ApiClient#getEntries()を呼び出す
//                        getEntries(new ApiClient.ResponseListener<Entries>() {
//                            @Override
//                            public void onComplete(ApiResponse<Entries> response) {
//                                // getEntries()のコールバックが戻ったらsubscriberに通知する
//                                subscriber.onNext(response);
//                                subscriber.onCompleted();
//                            }
//                        });
//                    }
//                });
//    }
//
//    public Observable<Entries> entries2() {
//        return entries().map(new Func1<ApiResponse<Entries>, Entries>() {
//            @Override
//            public Entries call(ApiResponse<Entries> r) {
//                return r.content;
//            }
//        });
//    }
}

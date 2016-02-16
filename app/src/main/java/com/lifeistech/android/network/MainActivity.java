package com.lifeistech.android.network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.cookpad.android.rxt4a.schedulers.AndroidSchedulers;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.lifeistech.android.network.controller.service.WeatherApi;
import com.lifeistech.android.network.entity.WeatherEntity;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;
import rx.Observer;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String END_POINT = "http://weather.livedoor.com";

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();


        // RestAdapterを作成する
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(END_POINT)
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("=NETWORK="))
                .build();

        // 天気予報情報を取得する
        //http://weather.livedoor.com/area/forecast/200010
        WeatherApi api =  adapter.create(WeatherApi.class);

        Observer observer = new Observer<WeatherEntity>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted()");
                //必要な情報を取り出して画面に表示してください。
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "Error : " + e.toString());
            }

            @Override
            public void onNext(WeatherEntity weather) {
                Log.d(TAG, "onNext()");
                ((TextView)findViewById(R.id.text)).setText(weather.getPinpointLocations().get(0).getName());
            }
        };

        api.getWeather("200010")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

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
}

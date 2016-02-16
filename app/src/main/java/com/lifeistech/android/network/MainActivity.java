package com.lifeistech.android.network;

import android.app.DownloadManager;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.cookpad.android.rxt4a.schedulers.AndroidSchedulers;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.lifeistech.android.network.api.ApiClient;
import com.lifeistech.android.network.api.EmployeesAPI;
import com.lifeistech.android.network.api.WeatherApi;
import com.lifeistech.android.network.client.JSONWeatherTask;
import com.lifeistech.android.network.entity.Employees;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
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
    private static final String ENDPOINT = "XXX";
    ApiClient mApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //Request method
    public void requestEmployeeData(String uri){
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(ENDPOINT).build();
        EmployeesAPI employeesAPI =adapter.create(EmployeesAPI.class);
        employeesAPI.getEmployees(new Callback<Employees>() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

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

}

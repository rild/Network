package com.lifeistech.android.network;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.lifeistech.android.network.api.ApiClient;
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
import java.util.List;

import rx.functions.Action1;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    ApiClient mApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWeather();
        Log.d("Start:", "Succeeded");
    }

    public void plus(int x, int y) {
        System.out.println(x + " + " + y + " = " + (x + y));
    }
    public int plus0(int x, int y) {
        //戻り値の有無では同名メソッドは許されない(まぁ当然か…)
        return x + y;
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
            Log.d("Min ~ MAx:", min + "~" + max );
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

                for (String id: r.content.entryIds) {
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

package com.lifeistech.android.network;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWeather();
        Log.d("Start:", "Succeeded");
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

            //Failir
            @Override
            public void onResponse(Response response) throws IOException {
                Log.d("onResponse", response.toString());
            }
        });
    }
}

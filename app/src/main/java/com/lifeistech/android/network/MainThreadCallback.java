package com.lifeistech.android.network;

import android.os.Looper;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.spdy.FrameReader;

import java.io.IOException;

/**
 * Created by rild on 16/02/12.
 */
public abstract class MainThreadCallback implements Callback {
    private static final String TAG = MainThreadCallback.class.getSimpleName();

    abstract public void onFail(final Exception error);

//    abstract public void onSuccess(final JsonObject responseBody);

    @Override
    public void onFailure(final Request request, final IOException e) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                e.printStackTrace();
//                onFail(e);
//            }
//        });
    }

//    @Override
//    public void onResponse(final Response response) throws IOException {
//        if (!response.isSuccessful() || response.body() == null) {
//            onFailure(response.request(), new IOException("Failed"));
//            return;
//        }
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    JsonObject responseBody = new Gson().fromJson(response.body().string(), JsonObject.class);
//                    onSuccess(responseBody);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    onFailure(response.request(), new IOException("Failed"));
//                }
//            }
//        });
//    }
//
//    private void runOnUiThread(Runnable task) {
//        new FrameReader.Handler(Looper.getMainLooper()).post(task);
//    }
}

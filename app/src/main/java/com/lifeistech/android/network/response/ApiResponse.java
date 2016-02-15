package com.lifeistech.android.network.response;

import com.lifeistech.android.network.api.ApiClient;
import com.lifeistech.android.network.tmp.Entries;
import com.lifeistech.android.network.tmp.Entry;

import java.util.ArrayList;

/**
 * Created by rild on 16/02/15.
 */
public class ApiResponse<T> {
    public T content;

    public T getEntries(ApiClient.ResponseListener<Entries> listener) {
        return content;
    }
}
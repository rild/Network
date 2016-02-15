package com.lifeistech.android.network.api;

import com.lifeistech.android.network.response.ApiResponse;
import com.lifeistech.android.network.tmp.Entries;
import com.lifeistech.android.network.tmp.Entry;

import rx.Observable;

/**
 * Created by rild on 16/02/15.
 */
public interface ApiClient {

        //コールバックベースのインターフェース？
    //entries を呼んでエントリのIDリストを得る
    void getEntries(ResponseListener<Entries> listener);

    //entries/:id を呼んで特定のエントリの内容を得る
    void getEntry(String id, ResponseListener<Entry> listener);

    //API呼び出しが完了したことを知らせるコールバック
    interface ResponseListener<T> {
        void onComplete(ApiResponse<T> response);
    }

        //Observableベースのインターフェース？
    //entries を呼ぶRxJava対応メソッド
    Observable<ApiResponse<Entries>> entries();

    //entry/:id　を呼ぶRxJava対応メソッド
    Observable<ApiResponse<Entry>> entry(String id);
}

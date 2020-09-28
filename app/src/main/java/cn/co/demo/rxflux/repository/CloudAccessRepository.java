package cn.co.demo.rxflux.repository;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.gson.GsonBuilder;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/* package */ abstract class CloudAccessRepository {

    protected static final String API_ACCESS_URL = "https://r.ymsl.com.cn/sccuapi/";

    protected abstract String getUrl();
    protected abstract Interceptor getInterceptor(@NonNull final Context context);

    /**
     * Retrofitは貰うサービスクラスためにサービスオブジェクトを作成する
     *
     * @param <T> サービスインターフェースクラス
     * @return サービスオブジェクト
     */
    protected <T> T createService(@NonNull final Context context, final Class<T> createClass) {
        return new Retrofit.Builder()
                .baseUrl(getUrl())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .create()))
                .client(createOkHttpClient(context))
                .build().create(createClass);
    }

    /**
     * AWSのAPIのリクエスト用のヘーダを作る関数。
     *
     * @return ヘーダ情報を持っているOkHttpClientオブジェクト。
     */
    private OkHttpClient createOkHttpClient(@NonNull final Context context) {
        final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(getInterceptor(context))
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

}

package com.example.sketch2;

import android.os.Build;

import androidx.annotation.RequiresApi;


import com.example.sketch2.Pojo.MyPOJO;
import com.example.sketch2.Pojo.Row;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DataService implements Serializable {

    private String BASE_URL = "http://openapi.foodsafetykorea.go.kr/";

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build();

    Retrofit retrofitClient =
            new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    SelectAPI select = retrofitClient.create(SelectAPI.class);
}

interface SelectAPI {
    @GET("api/{apikey}/I0490/json/1/50")
    Call<MyPOJO> selectAll(@Path("apikey") String apikey);

    @GET("api/{apikey}/I0490/json/1/50/{attr}={value}")
    Call<Row> selectOne(@Path("attr") String attr, @Path("value") String value);

}
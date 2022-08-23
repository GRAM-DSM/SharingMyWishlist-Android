package com.example.retrofitloginex.retrofit;

/* 여러 개의 Activity에서 동일한 코드의 재사용을 위해 정의된 클래스이다. */

import android.util.JsonReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    // TAG
    private static final String TAG = "RetrofitClient";

    // BASE_URL
    private static final String BASE_URL = "http://13.209.22.117:8087";

    // Retrofit
    private static Retrofit retrofit;

    // getClient
    public static Retrofit getClient() {

        // GsonBuilder
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        Gson gson = gsonBuilder.setLenient().create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // BASE_URL 설정
                    .addConverterFactory(GsonConverterFactory.create(gson)) // Json 파싱을 위한 Gson Converter 설정
                    .build();
        }

        return retrofit;
    }


}

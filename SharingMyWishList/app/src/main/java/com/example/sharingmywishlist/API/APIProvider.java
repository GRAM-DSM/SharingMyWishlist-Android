package com.example.sharingmywishlist.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIProvider {

    // Retrofit
    private static Retrofit retrofit;
    // BaseURL
    private static String BASE_URL = "http://10.156.147.133:8087";

    // Retrofit의 인스턴스 반환 메서드
    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

}

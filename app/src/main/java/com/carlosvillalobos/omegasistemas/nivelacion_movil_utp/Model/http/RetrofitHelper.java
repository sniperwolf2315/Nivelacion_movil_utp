package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.http;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public final class RetrofitHelper {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://cesarstore.herokuapp.com/api/")
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static ProductApi productApi;

    public static ProductApi getProductApi() {
        if(productApi == null){
            productApi = getRetrofit().create(ProductApi.class);
        }
        return productApi;
    }
}

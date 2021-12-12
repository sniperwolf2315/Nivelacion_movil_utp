package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.http;

import java.util.List;

import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.http.dto.ProductRequest;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.http.dto.ProductResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductApi {

    @GET("products")
    Call<List<ProductResponse>> getAll();

    @GET("products/{code}")
    Call<ProductResponse> getByCode(@Path("code") String code);

    @POST("products")
    Call<ProductResponse> createProduct(@Body ProductRequest product);
}

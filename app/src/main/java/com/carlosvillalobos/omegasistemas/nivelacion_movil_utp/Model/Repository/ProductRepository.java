package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.Repository;

import java.io.IOException;
import java.util.List;

import co.com.cesardiaz.misiontic.ventasdomiciliog1.model.http.ProductApi;
import co.com.cesardiaz.misiontic.ventasdomiciliog1.model.http.RetrofitHelper;
import co.com.cesardiaz.misiontic.ventasdomiciliog1.model.http.dto.ProductRequest;
import co.com.cesardiaz.misiontic.ventasdomiciliog1.model.http.dto.ProductResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    private final ProductApi productApi;

    public ProductRepository() {
        productApi = RetrofitHelper.getProductApi();
    }

    public void getAll(ProductCallback<List<ProductResponse>> callback) {
        productApi.getAll()
                .enqueue(new Callback<List<ProductResponse>>() {
                    @Override
                    public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                        if(response.isSuccessful()){
                            callback.onSuccess(response.body());
                        } else {
                            try {
                                callback.onFailure(response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                        callback.onFailure(t.getMessage());
                    }
                });
    }

    public void getByCode(String code, ProductCallback<ProductResponse> callback) {
        productApi.getByCode(code)
                .enqueue(new Callback<ProductResponse>() {
                    @Override
                    public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                        if(response.isSuccessful()){
                            callback.onSuccess(response.body());
                        } else {
                            try {
                                callback.onFailure(response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductResponse> call, Throwable t) {
                        callback.onFailure(t.getMessage());
                    }
                });
    }

    public void create(ProductRequest product, ProductCallback<ProductResponse> callback) {
        productApi.createProduct(product)
                .enqueue(new Callback<ProductResponse>() {
                    @Override
                    public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                        if(response.isSuccessful()){
                            callback.onSuccess(response.body());
                        } else {
                            try {
                                callback.onFailure(response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductResponse> call, Throwable t) {
                        callback.onFailure(t.getMessage());
                    }
                });
    }

    public interface ProductCallback<T> {
        void onSuccess(T data);

        void onFailure(String error);
    }
}

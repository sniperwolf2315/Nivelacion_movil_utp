package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.database.entities.User;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.http.dto.ProductRequest;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.http.dto.ProductResponse;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.Repository.FirebaseAuthRepository;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.Repository.GmailAuthRepository;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.Repository.ProductRepository;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.Repository.UserRepository;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.mvp.LoginMVP;

public class LoginInteractor implements LoginMVP.Model {

    private UserRepository userRepository;
    private ProductRepository productRepository;
    private FirebaseAuthRepository firebaseAuthRepository;
    private GmailAuthRepository gmailAuthRepository;

    public LoginInteractor(Context context) {
        firebaseAuthRepository = FirebaseAuthRepository.getInstance(context);
        gmailAuthRepository = GmailAuthRepository.getInstance(context);
        userRepository = UserRepository.getInstance(context);
        productRepository = new ProductRepository();
    }

    @Override
    public void validateCredentials(String email, String password, ValidateCredentialsCallback callback) {
        firebaseAuthRepository.authenticate(email, password,
                new FirebaseAuthRepository.FirebaseAuthCallback() {
                    @Override
                    public void onSuccess() {
                        callback.onSuccess();
                    }

                    @Override
                    public void onFailure() {
                        callback.onFailure("Credenciales inválidas");
                    }
                });

        /*
        userRepository.getUserByEmail(email, new UserRepository.UserCallback<User>() {
            @Override
            public void onSuccess(User user) {
                if (user == null) {
                    callback.onFailure("Usuario no existe");
                } else if (!user.getPassword().equals(password)) {
                    callback.onFailure("Contraseña incorrecta");
                } else {
                    callback.onSuccess();
                }
            }

            @Override
            public void onFailure() {
                callback.onFailure("Error accediendo a fuente de datos");
            }
        });

        productRepository.getByCode("1002",
                new ProductRepository.ProductCallback<ProductResponse>() {
                    @Override
                    public void onSuccess(ProductResponse data) {
                        Log.i("Product-getByCode", data.toString());
                    }

                    @Override
                    public void onFailure(String error) {
                        Log.e("Product-getByCode", error);
                    }
                });

        productRepository.getAll(new ProductRepository.ProductCallback<List<ProductResponse>>() {
                    @Override
                    public void onSuccess(List<ProductResponse> data) {
                        Log.i("Product-getAll", data.toString());
                    }

                    @Override
                    public void onFailure(String error) {
                        Log.e("Product-getAll", error);
                    }
                });

        ProductRequest productRequest = new ProductRequest("99998", "Lapicero naranja", 1900,
                Arrays.asList("papeleria", "tinta", "naranja"));
        productRepository.create(productRequest,
                new ProductRepository.ProductCallback<ProductResponse>() {
                    @Override
                    public void onSuccess(ProductResponse data) {
                        Log.i("Product-create", data.toString());
                    }

                    @Override
                    public void onFailure(String error) {
                        Log.e("Product-create", error);
                    }
                });
         */
    }

    @Override
    public boolean isAuthenticated() {
        return firebaseAuthRepository.isAuthenticated();
    }

    @Override
    public Intent getGoogleSignInIntent() {
        return gmailAuthRepository.getSignInIntent();
    }

    @Override
    public void setGoogleData(Intent data, ValidateCredentialsCallback callback) {
        gmailAuthRepository.setLoginData(data, new GmailAuthRepository.GoogleAuthCallback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }

            @Override
            public void onFailure() {
                callback.onFailure("Autenticación no finalizada");
            }
        });
    }
}

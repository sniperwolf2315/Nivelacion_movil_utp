package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.presenter;

import android.content.Intent;


import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.LoginInteractor;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.mvp.LoginMVP;

public class LoginPresenter implements LoginMVP.Presenter {

    private LoginMVP.View view;
    private LoginMVP.Model model;

    public LoginPresenter(LoginMVP.View view) {
        this.view = view;
        this.model = new LoginInteractor(view.getActivity());
    }

    @Override
    public void loginWithEmail() {
        boolean error = false;

        view.showEmailError("");
        view.showPasswordError("");

        LoginMVP.LoginInfo loginInfo = view.getLoginInfo();
        // Validate filds
        if (loginInfo.getEmail().trim().isEmpty()) {
            view.showEmailError("Correo electrónico es obligatorio");
            error = true;
        } else if (!isEmailValid(loginInfo.getEmail().trim())) {
            view.showEmailError("Correo electrónico no es válido");
            error = true;
        }

        if (loginInfo.getPassword().trim().isEmpty()) {
            view.showPasswordError("Contraseña es obligatoria");
            error = true;
        } else if (!isPasswordValid(loginInfo.getPassword().trim())) {
            view.showPasswordError("Contraseña no cumple criterios de seguridad");
            error = true;
        }

        if (!error) {
            view.startWaiting();
            // validar que el usuario/contraseña sean correctos
            new Thread(() -> {
                model.validateCredentials(loginInfo.getEmail().trim(),
                        loginInfo.getPassword().trim(),
                        new LoginMVP.Model.ValidateCredentialsCallback() {
                            @Override
                            public void onSuccess() {
                                view.getActivity().runOnUiThread(() -> {
                                    view.stopWaiting();
                                    view.MainActivity();
                                });
                            }

                            @Override
                            public void onFailure(String error) {
                                view.getActivity().runOnUiThread(() -> {
                                    view.stopWaiting();
                                    view.showGeneralError(error);
                                });
                            }
                        });
            }).start();
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@")
                && email.endsWith(".com");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    @Override
    public void loginWithFacebook() {

    }

    @Override
    public void loginWithGoogle() {
        Intent intent = model.getGoogleSignInIntent();
        view.openGoogleSignInActivity(intent);
    }

    @Override
    public void isAuthenticated() {
        boolean isAuthenticated = model.isAuthenticated();
        if (isAuthenticated) {
            view.MainActivity();
        }
    }

    @Override
    public void setGoogleData(Intent data) {
        view.startWaiting();
        // validar que el usuario/contraseña sean correctos
        model.setGoogleData(data, new LoginMVP.Model.ValidateCredentialsCallback() {
            @Override
            public void onSuccess() {
                view.getActivity().runOnUiThread(() -> {
                    view.stopWaiting();
                    view.MainActivity();
                });
            }

            @Override
            public void onFailure(String error) {
                view.getActivity().runOnUiThread(() -> {
                    view.stopWaiting();
                    view.showGeneralError(error);
                });
            }
        });
    }
}

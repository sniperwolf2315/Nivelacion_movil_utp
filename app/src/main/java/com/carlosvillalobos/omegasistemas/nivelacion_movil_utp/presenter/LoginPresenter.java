package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.presenter;

import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.LoginInteractor;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.mvp.LoginMVP;

public class LoginPresenter implements LoginMVP.Presenter {
    private LoginMVP.View view;
    private LoginMVP.Model model;

    public LoginPresenter(LoginMVP.View view){
        this.view = view;
        this.model = new LoginInteractor();

    }


    @Override
    public void loginWithEmail() {
        LoginMVP.LoginInfo loginInfo = view.getLoginInfo();
        if (loginInfo.getEmail().trim().isEmpty()){
            view.showEmailError("ingreso de correo electronico obligatorio");
        }
        if (loginInfo.getPassword().trim().isEmpty()){
            view.showPasswordError("ingreso de contraseña obligatorio");
        }

    }
}

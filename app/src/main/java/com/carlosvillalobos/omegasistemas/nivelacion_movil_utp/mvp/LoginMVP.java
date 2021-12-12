package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.mvp;

import android.content.Intent;

public interface LoginMVP {

    interface Model{

    }

    interface Presenter{
        void loginWithEmail();

    }

    interface View{
        LoginInfo loginInfo();
        void showEmailError(String error);
        void showPasswordError(String error);
        void showGeneralMesage(String error);

        void clearData();

        void openPaymentActivity();


        LoginInfo getLoginInfo();
    }

    class LoginInfo{
        private String email;
        private String password;


        public LoginInfo(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }



}

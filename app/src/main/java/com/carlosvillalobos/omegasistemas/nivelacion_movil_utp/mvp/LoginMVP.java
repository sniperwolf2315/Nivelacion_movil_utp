package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public interface LoginMVP {

    interface Model {
        void validateCredentials(String email, String password, ValidateCredentialsCallback callback);

        boolean isAuthenticated();

        Intent getGoogleSignInIntent();

        void setGoogleData(Intent data, ValidateCredentialsCallback callback);

        interface ValidateCredentialsCallback {
            void onSuccess();

            void onFailure(String error);
        }

    }

    interface Presenter {
        void loginWithEmail();

        void loginWithFacebook();

        void loginWithGoogle();

        void isAuthenticated();

        void setGoogleData(Intent data);
    }

    interface View {
        Activity getActivity();

        LoginInfo getLoginInfo();

        void showEmailError(String error);

        void showPasswordError(String error);

        void showGeneralError(String error);

        void clearData();

        void openPaymentsActivity();

        void startWaiting();

        void stopWaiting();

        void openGoogleSignInActivity(Intent intent);
    }

    class LoginInfo {
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

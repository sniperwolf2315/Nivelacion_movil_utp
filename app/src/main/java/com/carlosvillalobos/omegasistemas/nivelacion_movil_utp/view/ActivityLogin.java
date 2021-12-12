package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.R;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.mvp.LoginMVP;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.presenter.LoginPresenter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class ActivityLogin extends AppCompatActivity implements LoginMVP.View {
    private TextInputLayout tilEmail;
    private TextInputEditText etEmail;
    private TextInputLayout tilPassword;
    private TextInputEditText etPassword;

    private LoginMVP.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);
        initUi();

    }

    private void initUi() {
        tilEmail = findViewById(R.id.et_email);
        etEmail = findViewById(R.id.et_email);

        tilPassword = findViewById(R.id.et_password);
        etPassword = findViewById(R.id.et_password);

        AppCompatButton btnlogin = findViewById(R.id.btn_login);
        btnlogin.setOnClickListener((evt) -> presenter.loginWithEmail());
    }


    @Override
    public LoginMVP.LoginInfo loginInfo() {
        return new LoginMVP.LoginInfo(Objects.requireNonNull(etEmail.getText()).toString(), Objects.requireNonNull(etPassword.getText()).toString());
    }

    @Override
    public void showEmailError(String error) {
        tilEmail.setError(error);

    }

    @Override
    public void showPasswordError(String error) {
        tilPassword.setError(error);

    }

    @Override
    public void showGeneralMesage(String error) {
        Toast.makeText( ActivityLogin.this, error ,Toast.LENGTH_SHORT).show();


    }

    @Override
    public void clearData() {
        tilEmail.setError("");
        etEmail.setText("");
        tilPassword.setError("");
        etPassword.setText("");

    }

    @Override
    public void openPaymentActivity() {
        Intent intent = new Intent(this, RegistroActivity.class );
        startActivity(intent);

    }

    @Override
    public LoginMVP.LoginInfo getLoginInfo() {
        return null;
    }
}
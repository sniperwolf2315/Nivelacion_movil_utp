package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.view;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.R;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.mvp.LoginMVP;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.presenter.LoginPresenter;


public class LoginActivity extends AppCompatActivity implements LoginMVP.View {

    private LinearProgressIndicator piWaiting;
    private ImageView ivLogo;
    private TextInputLayout tilEmail;
    private TextInputEditText etEmail;
    private TextInputLayout tilPassword;
    private TextInputEditText etPassword;

    private AppCompatButton btnLogin;
    private AppCompatButton btnFacebook;
    private SignInButton btnGoogle;

    private LoginMVP.Presenter presenter;

    private ActivityResultLauncher<Intent> googleLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);
        presenter.isAuthenticated();

        initUI();
    }

    private void initUI() {
        piWaiting = findViewById(R.id.pi_waiting);

        ivLogo = findViewById(R.id.iv_logo);

        tilEmail = findViewById(R.id.til_email);
        etEmail = findViewById(R.id.et_email);

        tilPassword = findViewById(R.id.til_password);
        etPassword = findViewById(R.id.et_password);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(evt -> presenter.loginWithEmail());

        btnFacebook = findViewById(R.id.btn_facebook);
        btnFacebook.setOnClickListener(evt -> presenter.loginWithFacebook());

        btnGoogle = findViewById(R.id.btn_google);
        btnGoogle.setOnClickListener(evt -> presenter.loginWithGoogle());
        googleLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        presenter.setGoogleData(data);
                    }
                });
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public LoginMVP.LoginInfo getLoginInfo() {
        return new LoginMVP.LoginInfo(etEmail.getText().toString(), etPassword.getText().toString());
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
    public void showGeneralError(String error) {
        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearData() {
        tilEmail.setError("");
        etEmail.setText("");
        tilPassword.setError("");
        etPassword.setText("");
    }





    @Override
    public void startWaiting() {
        piWaiting.setVisibility(View.VISIBLE);
        btnLogin.setEnabled(false);
        btnFacebook.setEnabled(false);
        btnGoogle.setEnabled(false);
    }

    @Override
    public void stopWaiting() {
        piWaiting.setVisibility(View.GONE);
        btnLogin.setEnabled(true);
        btnFacebook.setEnabled(true);
        btnGoogle.setEnabled(true);
    }

    @Override
    public void openGoogleSignInActivity(Intent intent) {
        googleLauncher.launch(intent);
    }

    @Override
    public void MainActivity() {

    }

}
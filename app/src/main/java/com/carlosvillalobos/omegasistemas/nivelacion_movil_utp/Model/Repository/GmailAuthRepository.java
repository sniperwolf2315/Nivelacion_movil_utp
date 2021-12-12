package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.Repository;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;


import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.R;

public class GmailAuthRepository {
    private static GmailAuthRepository instance;

    public static GmailAuthRepository getInstance(Context context) {
        if (instance == null) {
            instance = new GmailAuthRepository(context);
        }
        return instance;
    }

    private Context context;
    private GoogleSignInClient googleSignInClient;
    private GoogleSignInAccount account;
    private com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.Repository.FirebaseAuthRepository firebaseAuthRepository;

    private GmailAuthRepository(Context context) {
        this.context = context;
        this.firebaseAuthRepository = com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.Repository.FirebaseAuthRepository.getInstance(context);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    public boolean isAuthenticated() {
        return getAccount() != null;
    }

    public GoogleSignInAccount getAccount() {
        if (account == null) {
            account = GoogleSignIn.getLastSignedInAccount(context);
        }
        return account;
    }

    public Intent getSignInIntent() {
        return googleSignInClient.getSignInIntent();
    }

    public void setLoginData(Intent data, GoogleAuthCallback callback) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            account = task.getResult(ApiException.class);
            AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
            firebaseAuthRepository.signInWithCredentials(credential,
                    new com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.Repository.FirebaseAuthRepository.FirebaseAuthCallback() {

                        @Override
                        public void onSuccess() {
                            callback.onSuccess();
                        }

                        @Override
                        public void onFailure() {
                            callback.onFailure();
                        }
                    });

        } catch (ApiException e) {
            callback.onFailure();
        }
    }

    public interface GoogleAuthCallback {
        void onSuccess();

        void onFailure();
    }
}

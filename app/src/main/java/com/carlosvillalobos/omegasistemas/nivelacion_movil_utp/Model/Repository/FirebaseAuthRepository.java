package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.Repository;

import android.content.Context;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.com.cesardiaz.misiontic.ventasdomiciliog1.model.database.entities.User;

public class FirebaseAuthRepository {

    private static FirebaseAuthRepository instance;

    public static FirebaseAuthRepository getInstance(Context context) {
        if (instance == null) {
            instance = new FirebaseAuthRepository(context);
        }
        return instance;
    }

    private co.com.cesardiaz.misiontic.ventasdomiciliog1.model.repository.UserRepository userRepository;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;

    private FirebaseAuthRepository(Context context) {
        this.userRepository = co.com.cesardiaz.misiontic.ventasdomiciliog1.model.repository.UserRepository.getInstance(context);
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public boolean isAuthenticated() {
        return getCurrentUser() != null;
    }

    public FirebaseUser getCurrentUser() {
        if (currentUser == null) {
            currentUser = firebaseAuth.getCurrentUser();
        }
        return currentUser;
    }

    public void registerNewUser(User user, FirebaseAuthCallback callback) {
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        currentUser = firebaseAuth.getCurrentUser();
                        //TODO Guardar datos adicionales en base de datos
                        //userRepository.save(user);

                        callback.onSuccess();
                    } else {
                        callback.onFailure();
                    }
                });
    }

    public void authenticate(String email, String password, FirebaseAuthCallback callback) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        currentUser = firebaseAuth.getCurrentUser();
                        callback.onSuccess();
                    } else {
                        callback.onFailure();
                    }
                });
    }

    public void logout(FirebaseAuthCallback callback) {
        if (currentUser != null) {
            firebaseAuth.signOut();
            currentUser = null;
            callback.onSuccess();
        } else {
            callback.onFailure();
        }
    }

    public void signInWithCredentials(AuthCredential credential, FirebaseAuthCallback callback){
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        currentUser = firebaseAuth.getCurrentUser();
                        callback.onSuccess();
                    } else {
                        callback.onFailure();
                    }
                });
    }

    public interface FirebaseAuthCallback {
        void onSuccess();

        void onFailure();
    }

}

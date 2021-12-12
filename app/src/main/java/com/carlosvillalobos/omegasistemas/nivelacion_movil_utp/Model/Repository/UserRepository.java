package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.Repository;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import co.com.cesardiaz.misiontic.ventasdomiciliog1.model.database.SalesDatabase;
import co.com.cesardiaz.misiontic.ventasdomiciliog1.model.database.dao.UserDao;
import co.com.cesardiaz.misiontic.ventasdomiciliog1.model.database.entities.User;

public class UserRepository {

    private static UserRepository instance;

    public static UserRepository getInstance(Context context) {
        if (instance == null) {
            instance = new UserRepository(context);
        }
        return instance;
    }

    private final static Boolean USE_DATABASE = Boolean.FALSE;

    private UserDao userDao;

    private DatabaseReference userRef;

    private UserRepository(Context context) {
        userDao = SalesDatabase.getDatabase(context).getUserDao();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        userRef = database.getReference("user");

        loadInitalDatabase();
    }

    private void loadInitalDatabase() {
        if (USE_DATABASE) {
            userDao.insert(
                    new User("Cesar Diaz", "cdiaz@email.com", "12345678"),
                    new User("Usuario de Pruebas", "test@email.com", "87654321")
            );
        } else {
            String username = "cdiaz@email.com".replace('@', '_').replace('.', '_');

            userRef.child(username).child("name").setValue("Cesar Diaz");
            userRef.child(username).child("email").setValue("cdiaz@email.com");
            userRef.child(username).child("password").setValue("12345678");

            username = "test@email.com".replace('@', '_').replace('.', '_');
            userRef.child(username)
                    .setValue(new User("Usuario de Pruebas", "test@email.com", "87654321"));
        }
    }

    public void getUserByEmail(String email, UserCallback<User> callback) {
        if (USE_DATABASE) {
            callback.onSuccess(userDao.getUserByEmail(email));
        } else {
            // Usar Firebase
            String username = email.replace('@', '_').replace('.', '_');
            userRef.child(username).get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            User value = task.getResult().getValue(User.class);
                            if (value != null) {
                                callback.onSuccess(value);
                            } else {
                                callback.onFailure();
                            }
                        } else {
                            callback.onFailure();
                        }
                    });
        }
    }

    public void getAll(UserCallback<List<User>> callback) {
        userRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot dataSnapshot = task.getResult();
                if (dataSnapshot.hasChildren()) {
                    List<User> users = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        snapshot.child("name").getValue(String.class);

                        User user = snapshot.getValue(User.class);
                        Log.d(UserRepository.class.getSimpleName(), user.toString());
                        users.add(user);
                    }
                    callback.onSuccess(users);
                } else {
                    callback.onSuccess(new ArrayList<>());
                }
            } else {
                callback.onFailure();
            }
        });
    }

    public interface UserCallback<T> {
        void onSuccess(T data);

        void onFailure();
    }
}

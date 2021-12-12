package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.database.entities.User;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE email = :email")
    User getUserByEmail(String email);

    @Insert
    void insert(User... users);

    @Delete
    void delete(User user);

}

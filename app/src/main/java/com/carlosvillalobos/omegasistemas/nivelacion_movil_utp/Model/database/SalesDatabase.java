package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.database.dao.UserDao;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.database.entities.Role;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.database.entities.User;

@Database(entities = {User.class, Role.class}, version = 1)
public abstract class SalesDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();

    private static volatile SalesDatabase INSTANCE;

    public static SalesDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room
                    .databaseBuilder(context.getApplicationContext(), SalesDatabase.class, "database-name")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }


}

package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model;

import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.mvp.MainMVP;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.view.adapter.dto.TaskItem;

import java.util.ArrayList;
import java.util.List;

public class MainInteractor implements MainMVP.Model {

    private List<TaskItem> tempItems;

    public MainInteractor() {
        tempItems = new ArrayList<>();

    }


    @Override
    public List<TaskItem> getTasks() {
        return new ArrayList<>(tempItems);

    }


    @Override
    public void saveTask(TaskItem task) {
        tempItems.add(task);

    }

    @Override
    public void updateTask(TaskItem item) {

    }

    @Override
    public void deleteTask(TaskItem task) {

    }
}

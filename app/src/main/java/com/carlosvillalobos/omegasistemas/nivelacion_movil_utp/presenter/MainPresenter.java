package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.presenter;
import android.util.Log;

import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.MainInteractor;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.mvp.MainMVP;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.view.adapter.dto.TaskItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainPresenter implements MainMVP.Presenter {

    private MainMVP.View view;
    private MainMVP.Model model;

    public MainPresenter(MainMVP.View view){

        this.view = view;
        this.model = new MainInteractor();
    }
    @Override
    public void loadTasks() {
        List<TaskItem> items = model.getTasks ();

        view.showTaskList(items);


    }

    @Override
    public void addNewTask() {
        Log.i(MainPresenter.class.getSimpleName(), "add new task");
        String description =view.getTaskDescription();
        String date = SimpleDateFormat.getDateTimeInstance().format(new Date());

        TaskItem task = new TaskItem(description, date);
        model.saveTask(task);
        view.addTaskToList(task);

    }
}

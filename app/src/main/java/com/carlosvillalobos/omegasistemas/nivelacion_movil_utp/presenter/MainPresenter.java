package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.presenter;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.MainInteractor;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.mvp.MainMVP;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.view.adapter.dto.TaskItem;

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

    }
}

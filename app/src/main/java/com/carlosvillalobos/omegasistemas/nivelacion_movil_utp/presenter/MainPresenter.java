package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.presenter;
import android.util.Log;

import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.Model.MainInteractor;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.mvp.MainMVP;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.view.adapter.dto.TaskItem;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.view.adapter.dto.TaskState;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainPresenter implements MainMVP.Presenter {

    private final MainMVP.View view;
    private final MainMVP.Model model;

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

    @Override
    public void taskItemClicked(TaskItem task) {
        String mesagge = task.getState() == TaskState.PENDING
                ? "Desea marcar como terminada esta tarea ?"
                : "Desea marcar como pendiente esta tarea ?" ;
        view.showConfirmDialog(mesagge, task);


    }

    @Override
    public void updateTask(TaskItem task) {
        task.setState(task.getState() == TaskState.PENDING ? TaskState.DONE: TaskState.PENDING);
        model.updateTask(task);
        view.updateTask(task);

    }

    @Override
    public void taskItemLongClicked(TaskItem task) {
        if (task.getState() == TaskState.DONE) {
            view.showDeleteDialog("would you like Remove this task ?", task);
        }

    }

    @Override
    public void deleteTask(TaskItem task) {
        model.deleteTask(task);
        view.deleteTask(task);

    }

}

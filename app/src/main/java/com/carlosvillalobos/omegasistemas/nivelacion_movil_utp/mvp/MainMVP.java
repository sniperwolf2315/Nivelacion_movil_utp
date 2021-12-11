
package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.mvp;

import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.view.adapter.dto.TaskItem;

import java.util.List;

public interface MainMVP {

    interface Model {

        List<TaskItem> getTasks();

        void saveTask(TaskItem task);

        void updateTask(TaskItem item);
    }
    interface Presenter {
        void loadTasks ();

        void addNewTask ();

        void taskItemClicked(TaskItem item);
    }
    interface View {

        void showTaskList(List<TaskItem> items);

        String getTaskDescription();

        void addTaskToList(TaskItem task);

        void updateTask(TaskItem item);
    }
}

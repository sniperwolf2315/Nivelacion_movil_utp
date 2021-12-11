
package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.mvp;

import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.view.adapter.dto.TaskItem;

import java.util.List;

public interface MainMVP {

    interface Model {

        List<TaskItem> getTasks();

        void saveTask(TaskItem task);

        void updateTask(TaskItem item);

        void deleteTask(TaskItem task);
    }
    interface Presenter {
        void loadTasks ();

        void addNewTask ();

        void taskItemClicked(TaskItem item);

        void updateTask(TaskItem task);

        void taskItemLongClicked(TaskItem task);

        void deleteTask(TaskItem task);
    }
    interface View {

        void showTaskList(List<TaskItem> items);

        String getTaskDescription();

        void addTaskToList(TaskItem task);

        void updateTask(TaskItem task);

        void showConfirmDialog(String mesagge, TaskItem task);

        void showDeleteDialog(String mesagge, TaskItem task);

        void deleteTask(TaskItem task);
    }
}

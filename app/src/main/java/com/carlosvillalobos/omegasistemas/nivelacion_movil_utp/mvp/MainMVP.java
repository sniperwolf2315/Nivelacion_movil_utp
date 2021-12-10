
package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.mvp;

import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.view.adapter.dto.TaskItem;

import java.util.List;

public interface MainMVP {

    interface Model {

        List<TaskItem> getTasks();
    }
    interface Presenter {
        void loadTasks ();

        void addNewTask ();

    }
    interface View {

        void showTaskList(List<TaskItem> items);
    }
}

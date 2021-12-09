package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.R;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.mvp.MainMVP;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.view.adapter.TaskAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements MainMVP.View {

    private TextInputLayout tilNewTask;
    private TextInputEditText etNewTask;
    private RecyclerView rvTasks;

    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI() {
        tilNewTask = findViewById(R.id.til_new_task);
        tilNewTask.setEndIconOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Add new task to list", Toast.LENGTH_SHORT)
                    .show();
        });

        etNewTask = findViewById(R.id.et_new_task);

        taskAdapter = new TaskAdapter();
        rvTasks = findViewById(R.id.rv_tasks);
        rvTasks.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvTasks.setAdapter(taskAdapter);
    }
}
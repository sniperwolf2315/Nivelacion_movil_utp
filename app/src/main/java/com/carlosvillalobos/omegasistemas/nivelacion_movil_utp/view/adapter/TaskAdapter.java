package com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.R;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.presenter.MainPresenter;
import com.carlosvillalobos.omegasistemas.nivelacion_movil_utp.view.adapter.dto.TaskItem;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<TaskItem> data;

    public TaskAdapter() {
        data = new ArrayList<>();
    }

    public void setData(List<TaskItem> data) {
        Log.i(MainPresenter.class.getSimpleName(), "set data");
        this.data = data;
        notifyDataSetChanged();
    }

    public void addItem(TaskItem item) {
        Log.i(MainPresenter.class.getSimpleName(), "add new item");
        data.add(item);
        notifyDataSetChanged();
        //notifyItemInserted(data.size() - 1);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskItem item = data.get(position);
        holder.tvDescription.setText(item.getDescription());
        holder.tvDate.setText(item.getDate());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivIcon;
        private final TextView tvDescription;
        private final TextView tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvDate = itemView.findViewById(R.id.tv_date);
        }
    }
}

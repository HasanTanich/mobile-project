package com.example.hasantanich;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TaskListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Tasks> tasksList;

    public TaskListViewAdapter(Context context, List<Tasks> tasks){
        this.context = context;
        this.tasksList = tasks;
    }

    @Override
    public int getCount() {
        return tasksList.size();
    }

    @Override
    public Object getItem(int position) {
        return tasksList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tasksList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.task_list_item, parent, false);
        Tasks tasks = tasksList.get(position);

        TextView title = convertView.findViewById(R.id.title_textView);
        TextView date = convertView.findViewById(R.id.date_textView);

        title.setText(tasks.getTitle());
        date.setText(tasks.getDate());

        return convertView;
    }
}

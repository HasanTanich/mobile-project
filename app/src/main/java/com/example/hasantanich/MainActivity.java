package com.example.hasantanich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TASK_ID = "";
    private ListView listView;
    Button delete_btn;
    Button update_btn;
    Integer indexVal;
    Tasks task;
    TaskListViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.task_list_view);
        delete_btn = findViewById(R.id.delete_task_btn);
        update_btn = findViewById(R.id.update_task_btn);
        // get all tasks from database
        List<Tasks> tasks = MyDatabase.getDatabase(this).myDAO().getAllTasks();

        //initialize Custom list View Adapter
        adapter = new TaskListViewAdapter(this, tasks);
        listView.setAdapter(adapter);

        // select item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                task = (Tasks) parent.getItemAtPosition(position);
                indexVal = position;
            }
        });

        //update item
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create new intent
                if(task != null) {
                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    intent.putExtra(TASK_ID, task.getId());
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "select an item first to update", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //delete item
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (task != null) {
                    task = MyDatabase.getDatabase(getApplicationContext()).myDAO().getTask(task.getId());
                    MyDatabase.getDatabase(getApplicationContext()).myDAO().deleteTask(task);
                    adapter.notifyDataSetChanged();
                    adapter.notify();
                }
            }
        });
    }

    // when user clicks on add Task button
    public void onAddClick(View view){
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        startActivity(intent);
    }

}
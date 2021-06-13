package com.example.hasantanich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    EditText title;
    EditText date;
    Tasks task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Bind UI Components
        title = findViewById(R.id.taskTitle);
        date = findViewById(R.id.taskDate);

        // initialize task object
        task = null;

        //get Intent that started activity
        Intent intent = getIntent();
        // Get Extras
        Bundle bundle = intent.getExtras();

        // check if there are extras
        if(bundle != null){
            // update
            // get task id from intent
            int id = bundle.getInt(MainActivity.TASK_ID);
            // get task by id from database
            task = MyDatabase.getDatabase(this).myDAO().getTask(id);
            // set UI components
            title.setText(task.getTitle());
            date.setText(task.getDate());
        }
    }

    public void onButtonClick(View view){
        Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
        switch(view.getId()){
            case R.id.cancel_btn:
                startActivity(intent);
                break;
            case R.id.save_btn:
                if(title.getText().toString().equals("") || date.getText().toString().equals("")){
                    Toast.makeText(this, "Please fill the fields above to save", Toast.LENGTH_SHORT).show();
                }else{
                    if (task != null) {
                        // update
                        Tasks updated_task = new Tasks(task.getId(), title.getText().toString(), date.getText().toString());
                        MyDatabase.getDatabase(this).myDAO().updateTask(updated_task);
                        startActivity(intent);
                        break;
                    } else {
                        // add new
                        Tasks new_task = new Tasks(title.getText().toString(), date.getText().toString());
                        MyDatabase.getDatabase(this).myDAO().addTask(new_task);
                        startActivity(intent);
                        break;
                    }
                }
            default:
                Toast.makeText(getApplicationContext(), "Invalid Button", Toast.LENGTH_LONG);
        }
    }
}
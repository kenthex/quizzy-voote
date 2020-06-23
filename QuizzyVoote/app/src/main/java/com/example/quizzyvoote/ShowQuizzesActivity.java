package com.example.quizzyvoote;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quizzyvoote.classes.c_Quiz;
import com.example.quizzyvoote.classes.c_Quiz_List_Adapter;
import java.util.ArrayList;

public class ShowQuizzesActivity extends AppCompatActivity {

    RecyclerView rv_quiz_list;
    Button       button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_quiz_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Голосовать");


        ArrayList<c_Quiz> quizzes = new ArrayList<>();
        quizzes.add(new c_Quiz(0,"name0", "creator_name0"));
        quizzes.add(new c_Quiz(1,"name1", "creator_name1"));

        rv_quiz_list = findViewById(R.id.rv_quiz_list);
        c_Quiz_List_Adapter quizAdapter = new c_Quiz_List_Adapter(quizzes);
        rv_quiz_list.setAdapter(quizAdapter);
        rv_quiz_list.setLayoutManager(new LinearLayoutManager(this));

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}

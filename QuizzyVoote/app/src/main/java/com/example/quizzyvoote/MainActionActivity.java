package com.example.quizzyvoote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActionActivity extends AppCompatActivity {

    Button btn_create_quiz, btn_pass_quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_action_screen);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Главное меню");


        btn_create_quiz = (Button) findViewById(R.id.btn_create_quiz);
        btn_create_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActionActivity.this, CreateQuizActivity.class);
                startActivity(intent);
            }
        });

        btn_pass_quiz = (Button) findViewById(R.id.btn_pass_quiz);
        btn_pass_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActionActivity.this, ShowQuizzesActivity.class);
                startActivity(intent);
            }
        });
    }

}

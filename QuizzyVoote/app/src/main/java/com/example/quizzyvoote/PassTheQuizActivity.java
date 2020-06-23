package com.example.quizzyvoote;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PassTheQuizActivity extends AppCompatActivity {

    public final static String ID = "ID";
    public final static String CREATOR = "CREATOR";
    public final static String NAME = "NAME";
    public TextView id, creator, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_the_quiz);

        id = findViewById(R.id.tv_quiz_id);
        creator = findViewById(R.id.tv_quiz_creator);
        name = findViewById(R.id.tv_label_name);

        id.setText(getIntent().getStringExtra("ID"));
        creator.setText(getIntent().getStringExtra("CREATOR"));
        name.setText(getIntent().getStringExtra("NAME"));

    }

}

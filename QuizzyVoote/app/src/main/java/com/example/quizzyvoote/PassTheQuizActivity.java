package com.example.quizzyvoote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzyvoote.classes.Storage;
import com.example.quizzyvoote.classes.api_Answers;
import com.example.quizzyvoote.classes.c_Answers;
import com.example.quizzyvoote.classes.c_PassTheQuiz_Adapter;
import com.example.quizzyvoote.classes.c_Quiz;
import com.example.quizzyvoote.classes.c_Quiz_List_Adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PassTheQuizActivity extends AppCompatActivity {

    public final static String ID = "ID";
    public final static String CREATOR = "CREATOR";
    public final static String NAME = "NAME";
    public final static String ANSWER = "ANSWER";

    public TextView id, creator, name, title;
    public RecyclerView rv_answer_list;
    List<api_Answers> answers = new ArrayList<>();

    Button btnSave;

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

        Storage.addProperty("CURRENT_QUESTION_ID", getIntent().getStringExtra("ID"));
        String answersText = getIntent().getStringExtra("ANSWER");

        for (String retval : answersText.split("~~", 0)) {
            answers.add(new api_Answers(retval));
        }
        answers.remove(0);

        Toast.makeText(PassTheQuizActivity.this, getIntent().getStringExtra("ANSWER"), Toast.LENGTH_SHORT).show();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_answers);
        c_PassTheQuiz_Adapter adapter = new c_PassTheQuiz_Adapter(this, answers);
        recyclerView.setAdapter(adapter);

        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PassTheQuizActivity.this, getIntent().getStringExtra("SELECTED_ANSWER"), Toast.LENGTH_SHORT).show();
            }
        });

    }

}

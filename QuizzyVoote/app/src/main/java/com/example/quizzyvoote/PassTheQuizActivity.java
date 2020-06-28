package com.example.quizzyvoote;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.quizzyvoote.classes.api_NetworkService;
import com.example.quizzyvoote.classes.api_Votes;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassTheQuizActivity extends AppCompatActivity {

    public final static String ID = "ID";
    public final static String CREATOR = "CREATOR";
    public final static String NAME = "NAME";
    public final static String ANSWER = "ANSWER";

    public TextView id, creator, name, answer, someText;
    public RecyclerView rv_answer_list;
    List<api_Answers> answers = new ArrayList<>();

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_the_quiz);

        id = findViewById(R.id.tv_quiz_id);
        creator = findViewById(R.id.tv_quiz_creator);
        answer = findViewById(R.id.tv_quiz_answer);
        name = findViewById(R.id.tv_label_name);
        someText = findViewById(R.id.sdhfj);

        id.setText(getIntent().getStringExtra("ID"));
        creator.setText(getIntent().getStringExtra("CREATOR"));
        name.setText(getIntent().getStringExtra("NAME"));

        if(!Storage.getProperty("TITLE").equals("")) {
            answer.setText(Storage.getProperty("TITLE"));
        } else {
            answer.setText("");
            someText.setText("");
        }

        Storage.addProperty("CURRENT_QUESTION_ID", getIntent().getStringExtra("ID"));
        String answersText = getIntent().getStringExtra("ANSWER");

        for (String retval : answersText.split("~~", 0)) {
            answers.add(new api_Answers(retval));
        }
        answers.remove(0);

        //Toast.makeText(PassTheQuizActivity.this, getIntent().getStringExtra("ANSWER"), Toast.LENGTH_SHORT).show();


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_answers);
        c_PassTheQuiz_Adapter adapter = new c_PassTheQuiz_Adapter(this, answers);
        recyclerView.setAdapter(adapter);

        btnSave = findViewById(R.id.btn_save);

        if(Storage.getProperty("VOTED").equals("TRUE")) {
            btnSave.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(!Storage.getProperty("CURRENT_ANSWER").equals(null)) {


                    api_Votes vote = new api_Votes(Integer.parseInt(Storage.getProperty("USER_ID")), Integer.parseInt(Storage.getProperty("CURRENT_QUESTION_ID")), Storage.getProperty("CURRENT_ANSWER"));
                    api_NetworkService.getInstance()
                            .getJSONApi()
                            .createVote(vote)
                            .enqueue(new Callback<api_Votes>() {
                                @Override
                                public void onResponse(@NonNull Call<api_Votes> call, @NonNull Response<api_Votes> response) {
                                    api_Votes votes = response.body();
                                    Toast.makeText(PassTheQuizActivity.this, "SEND: " + Storage.getProperty("CURRENT_ANSWER"), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(PassTheQuizActivity.this, MainActionActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(@NonNull Call<api_Votes> call, @NonNull Throwable t) {
                                    Log.d("RES", "ERROR");
                                    t.printStackTrace();
                                }
                            });
            }

        });

    }

}

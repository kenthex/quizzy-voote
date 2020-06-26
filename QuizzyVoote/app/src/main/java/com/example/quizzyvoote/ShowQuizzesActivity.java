package com.example.quizzyvoote;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzyvoote.classes.api_Answers;
import com.example.quizzyvoote.classes.api_NetworkService;
import com.example.quizzyvoote.classes.api_Questions;
import com.example.quizzyvoote.classes.c_Quiz;
import com.example.quizzyvoote.classes.c_Quiz_List_Adapter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowQuizzesActivity extends AppCompatActivity {

    RecyclerView rv_quiz_list;
    Button       button;
    List<api_Questions> questions;
    List<String> userdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_quiz_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Голосовать");

        final ArrayList<c_Quiz> quizzes = new ArrayList<>();
//        quizzes.add(new c_Quiz(0,"name0", "creator_name0"));
//        quizzes.add(new c_Quiz(1,"name1", "creator_name1"));

        api_NetworkService.getInstance()
                .getJSONApi()
                .getQuestions()
                .enqueue(new Callback<api_Questions>() {
                    @Override
                    public void onResponse(@NonNull Call<api_Questions> call, @NonNull Response<api_Questions> response) {
                        //api_Questions post = response.body();
                        questions = response.body().getQuestions();
                        //userdata = response.body().getUserdata();
                        //Toast.makeText(ShowQuizzesActivity.this, questions.get(0).getUserdata(), Toast.LENGTH_SHORT).show();
                        for(api_Questions question : questions)
                            quizzes.add(new c_Quiz(Integer.parseInt(question.getID()), question.getTitle(), question.getCreator(), question.getExpiredAt()));


                        //  quizzes.add(new c_Quiz(Integer.parseInt(question.getID()),question.getTitle(), "creator_name1", question.getExpiredAt()));
                        rv_quiz_list = findViewById(R.id.rv_quiz_list);
                        c_Quiz_List_Adapter quizAdapter = new c_Quiz_List_Adapter(quizzes);
                        rv_quiz_list.setAdapter(quizAdapter);
                        rv_quiz_list.setLayoutManager(new LinearLayoutManager(ShowQuizzesActivity.this));

                        //Toast.makeText(ShowQuizzesActivity.this, questions., Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(@NonNull Call<api_Questions> call, @NonNull Throwable t) {
                        Log.d("RES", "ERROR");
                        t.printStackTrace();
                    }
                });



        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}

package com.example.quizzyvoote;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quizzyvoote.classes.c_Question_Adapter;

import java.util.ArrayList;


public class CreateQuizActivity extends AppCompatActivity {

    RecyclerView rv_questions;
    EditText     answerText, quizName;
    Button       addAnswer, deleteAllAnswers, saveQuiz;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Создание голосования");

        rv_questions = (RecyclerView) findViewById(R.id.rv_questions);
        rv_questions.setHasFixedSize(true);
        final ArrayList<String> questions = new ArrayList<String>();
        //questions.add(0, "epep");
        answerText = (EditText) findViewById(R.id.et_text_answer);
        quizName = (EditText) findViewById(R.id.et_quiz_name);
        addAnswer = (Button) findViewById(R.id.btn_add_answer);
        deleteAllAnswers = (Button) findViewById(R.id.btn_delete_all_answers);
        saveQuiz = (Button) findViewById(R.id.btn_save_quiz);

        final c_Question_Adapter questionAdapter = new c_Question_Adapter(questions);
        rv_questions.setAdapter(questionAdapter);
        rv_questions.setLayoutManager(new LinearLayoutManager(this));

        addAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answerText.getText().toString().trim().length() != 0) {
                    questions.add(answerText.getText().toString());
                    questionAdapter.notifyItemInserted(questionAdapter.getItemCount());
                    answerText.setText("");
                } else Toast.makeText(CreateQuizActivity.this, "Пустое поле (Вариант ответа)", Toast.LENGTH_SHORT).show();
            }
        });

        deleteAllAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = v.getContext();
                if(questions.size() != 0) {
                    new AlertDialog.Builder(context)
                            .setTitle("Подтверждение")
                            .setMessage("Вы действительно хотите все удалить?")
                            .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    questionAdapter.notifyItemRangeRemoved(0, questionAdapter.getItemCount());
                                    questionAdapter.notifyItemRangeChanged(0, 0);
                                    questions.removeAll(questions);
                                    quizName.setText("");
                                    Toast.makeText(context, "Все удалено, ничего не оставлено ;(", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Нет", null)
                            .show();
                } else Toast.makeText(context, "Нечего удалять ;)", Toast.LENGTH_SHORT).show();
            }
        });

        saveQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Integer quizNameLength = quizName.getText().toString().trim().length();
                Integer answersCount = questions.size();

                if(quizNameLength != 0) {
                    if(answersCount != 0) {
                        /// Сохранение вопроса в бд
                    } else Toast.makeText(context, "Нет вариантов ответов на голосование!", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(context, "Пустое поле (Название голосования)", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

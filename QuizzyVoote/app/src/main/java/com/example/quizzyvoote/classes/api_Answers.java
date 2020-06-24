package com.example.quizzyvoote.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class api_Answers {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("question_id")
    @Expose
    private int question_id;

    @SerializedName("title")
    @Expose
    private ArrayList<String> title;

    @SerializedName("result")
    @Expose
    private String result;

    public api_Answers(int question_id, ArrayList<String> title) {
        this.question_id = question_id;
        this.title = title;
    }

    public int getID() { return this.id; }

    public int getQuestionID() { return this.question_id; }

    public ArrayList<String> getTitles() { return this.title; }

    public String getResult() { return this.result; }

}

package com.example.quizzyvoote.classes;

import java.util.List;

public class c_Quiz {

    private Integer quiz_ID;
    private String quiz_name;
    private String quiz_creator;

    public void setQuizName(String quiz_name) {
        this.quiz_name = quiz_name;
    }

    public String getQuizName() {
        return this.quiz_name;
    }

    public void setQuizID(Integer quiz_ID) {
        this.quiz_ID = quiz_ID;
    }

    public Integer getQuizID() {
        return this.quiz_ID;
    }

    public void setQuizCreator(String quiz_creator) {
        this.quiz_creator = quiz_creator;
    }

    public String getQuizCreator() {
        return this.quiz_creator;
    }

    public c_Quiz(Integer id, String name, String creator) {
        this.quiz_ID = id;
        this.quiz_name = name;
        this.quiz_creator = creator;
    }
}

package com.example.quizzyvoote.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class api_Questions {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("expired_at")
    @Expose
    private long expired_at;

    @SerializedName("verified")
    @Expose
    private int verified;

    @SerializedName("error")
    @Expose
    private String error;

    public api_Questions(String title, long expired_at, int verified) {
        this.title = title;
        this.expired_at = expired_at;
        this.verified = verified;
    }

    public api_Questions(String id, String title, long expired_at, int verified) {
        this.id = id;
        this.title = title;
        this.expired_at = expired_at;
        this.verified = verified;
    }

    public String getID() { return this.id; };

    public String getTitle() { return this.title; };

    public long getExpiredAt() { return this.expired_at; };

    public int getVerified() { return this.verified; };

    public String getError() { return this.error; };

}

package com.example.hackerton;

import java.io.Serializable;

public class Hurdle_Record_Box implements Serializable {

    String id;
    String score;
    int ranking;

    public int getRanking() {
        return this.ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

//    public Hurdle_Record_Box(String id, String score) {
//        this.id = id;
//        this.score = score;
//    }
}

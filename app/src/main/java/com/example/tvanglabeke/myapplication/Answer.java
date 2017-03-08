package com.example.tvanglabeke.myapplication;

import java.io.Serializable;

public class Answer implements Serializable {
    public String text;
    public Boolean goodAnswer;

    public Answer(String text, Boolean goodAnswer) {
        this.text = text;
        this.goodAnswer = goodAnswer;
    }
}

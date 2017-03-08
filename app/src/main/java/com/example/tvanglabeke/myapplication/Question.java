package com.example.tvanglabeke.myapplication;

import android.util.Log;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Question  {
    public ArrayList<Answer> answers;
    public String text;
    public String difficulty;
    public String category;

    public Question(String text, String difficulty, String category, ArrayList<Answer> answers) {
        this.text = text;
        this.difficulty = difficulty;
        this.category = category;
        this.answers = answers;

    }


}

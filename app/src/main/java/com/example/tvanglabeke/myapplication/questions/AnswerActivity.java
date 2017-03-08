package com.example.tvanglabeke.myapplication.questions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tvanglabeke.myapplication.Answer;
import com.example.tvanglabeke.myapplication.Question;
import com.example.tvanglabeke.myapplication.R;

import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity {
    private String text;
    private String difficulty;
    private ArrayList<Answer> answers;
    private String goodResponse;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_answers);

        Bundle extras = getIntent().getExtras();

        text = extras.getString("text");

        TextView textView = (TextView) this.findViewById(R.id.text);
        textView.setText(text);

//        Toast.makeText(this,text, Toast.LENGTH_LONG).show();

        difficulty = extras.getString("difficulty");
        TextView difficultyView = (TextView) this.findViewById(R.id.difficulty);
        difficultyView.setText("Difficulty : " + difficulty);

        goodResponse = extras.getString("good_response");
        TextView good_response = (TextView) this.findViewById(R.id.good_response);
        good_response.setText(goodResponse);
    }
}

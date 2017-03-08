package com.example.tvanglabeke.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.tvanglabeke.myapplication.questions.QuestionsActivity;

public class MainActivity extends AppCompatActivity {

    TextView myTextView;
    SeekBar actionSeekBar;
    Button boutton;
    String numb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        myTextView = (TextView) this.findViewById(R.id.numberView);
        actionSeekBar = (SeekBar) this.findViewById(R.id.numberAction);
        boutton = (Button) this.findViewById(R.id.button);

        actionSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                myTextView.setText(String.valueOf(progress));
                numb = String.valueOf(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void changeActivity(View view) {

        Intent intent = new Intent(this, QuestionsActivity.class);
        intent.putExtra("numb", numb);
        startActivity(intent);
    }
}
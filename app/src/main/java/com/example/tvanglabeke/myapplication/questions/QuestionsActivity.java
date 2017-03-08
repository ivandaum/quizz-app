package com.example.tvanglabeke.myapplication.questions;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tvanglabeke.myapplication.Answer;
import com.example.tvanglabeke.myapplication.Question;
import com.example.tvanglabeke.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    final ArrayList<Question> questions = new ArrayList<>();

    String numb;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_questions);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) this.findViewById(R.id.questions_list);
        recyclerView.setLayoutManager(layoutManager);

        Bundle extras = getIntent().getExtras();

        numb = extras.getString("numb");

        fetchQuestions();

    }
    public void showAnwser(View view) {
        int viewId = view.getId();
        Question question = questions.get(viewId);

//        Toast.makeText(recyclerView.getContext(),"id : " + viewId, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, AnswerActivity.class);

        intent.putExtra("good_response", question.answers.get(0).text);
        intent.putExtra("text", question.text);
        intent.putExtra("difficulty", question.difficulty);
        startActivity(intent);
    }

    void fetchQuestions(){
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, "https://opentdb.com/api.php?amount=" + numb, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");

//                    Toast.makeText(recyclerView.getContext(),results.length() + " questions loaded", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < results.length(); i++){
                        JSONObject jsonObj = results.getJSONObject(i);

                        ArrayList<Answer> answers = new ArrayList<Answer>();

                        JSONArray incorrects =  jsonObj.getJSONArray("incorrect_answers");

                        if ( incorrects != null) {
                            for (int a=0; a<incorrects.length(); a++){
                                answers.add(new Answer(incorrects.getString(a),false));
                            }

                            answers.add(new Answer(jsonObj.getString("correct_answer"),true));
                        }

                        questions.add(new Question(
                                jsonObj.getString("question"),
                                jsonObj.getString("difficulty"),
                                jsonObj.getString("category"),
                                answers
                        ));
                        Log.d("question : ", jsonObj.toString());
                    }
                    recyclerView.setAdapter(new QuestionAdapter(questions));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(recyclerView.getContext(),"Error while loading question list", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(recyclerView.getContext(),"APP ERROR : " + error, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonRequest);
    }
}
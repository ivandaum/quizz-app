package com.example.tvanglabeke.myapplication.questions;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tvanglabeke.myapplication.Question;
import com.example.tvanglabeke.myapplication.R;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private ArrayList<Question> questions;

    public QuestionAdapter(ArrayList<Question> questions) {
        this.questions = questions;
    }

    @Override

    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context ctx = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View questionItemView = inflater.inflate(R.layout.questions_item, parent,false);

        return new QuestionViewHolder(questionItemView);
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, int position) {
        Question question = questions.get(position);

        String color = "#ff714d";
        if("easy".equals(question.difficulty)) {
            color = "#72da77";
        } else if ("medium".equals(question.difficulty)) {
            color = "#f1e977";
        }

        holder.questionText.setText(question.text);
        holder.questionText.setId(position);
        holder.questionText.setBackgroundDrawable(new ColorDrawable(Color.parseColor(color)));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView questionText;
        public QuestionViewHolder(View itemView) {
            super(itemView);
            this.questionText = (TextView) itemView.findViewById(R.id.questions_list);
        }
    }
}

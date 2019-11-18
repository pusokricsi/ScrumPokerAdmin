package com.example.scrumpokeradmin;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scrumpokeradmin.Object.Question;

import java.util.ArrayList;

public class MyQuestionItemRecyclerViewAdapter extends RecyclerView.Adapter<MyQuestionItemRecyclerViewAdapter.ViewHolder> {

    private View itemView;
    private ArrayList<Question> mListQuestion;

    public MyQuestionItemRecyclerViewAdapter(ArrayList<Question> listQuestion) {
        this.mListQuestion = listQuestion;
        Log.i("FBDB",mListQuestion.toString());
    }

    @NonNull
    @Override
    public MyQuestionItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyQuestionItemRecyclerViewAdapter.ViewHolder holder, int position) {
        if (holder!=null){
            holder.timeTextView.setText(mListQuestion.get(position).getHour()+":"+mListQuestion.get(position).getMinute());
            holder.questionTextView.setText(mListQuestion.get(position).getQuestion());
            
        }else{
            Log.i("FBDB","Adapter problem: Holder empty!");
        }
    }

    @Override
    public int getItemCount() {
        return mListQuestion.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView timeTextView,questionTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            questionTextView = itemView.findViewById(R.id.questionTextView);
        }
    }
}
